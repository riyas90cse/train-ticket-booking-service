package com.cloud.bees.challenge.service.impl;

import com.cloud.bees.challenge.entity.SeatEntity;
import com.cloud.bees.challenge.entity.TicketEntity;
import com.cloud.bees.challenge.mapper.SeatMapper;
import com.cloud.bees.challenge.mapper.TicketMapper;
import com.cloud.bees.challenge.model.*;
import com.cloud.bees.challenge.repository.SeatRepository;
import com.cloud.bees.challenge.repository.TicketRepository;
import com.cloud.bees.challenge.service.ITicketService;
import com.cloud.bees.challenge.service.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cloud.bees.challenge.util.ApplicationUtil.generateConfirmationCode;
import static com.cloud.bees.challenge.util.ApplicationUtil.generateTicketNo;

@Slf4j
@Service
public class TicketingService implements ITicketService {

    private final UserService userService;
    private final TrainService trainService;
    private final SectionService sectionService;
    private final SeatService seatService;
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final SeatMapper seatMapper;
    private final SeatRepository seatRepository;

    @Autowired
    public TicketingService(UserService userService, TrainService trainService, SectionService sectionService,
                            SeatService seatService, TicketRepository ticketRepository,
                            TicketMapper ticketMapper, SeatMapper seatMapper, SeatRepository seatRepository) {
        this.userService = userService;
        this.trainService = trainService;
        this.sectionService = sectionService;
        this.seatService = seatService;
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.seatMapper = seatMapper;
        this.seatRepository = seatRepository;
    }

    @Transactional
    @Override
    public Ticket purchaseTicket(TicketPayload payload) {

        //Find User
        User user = userService.getUserByEmail(payload.getUserDetail().getEmail());

        //Find and Select Train
        Train train = trainService.selectTrain(payload.getFrom(), payload.getTo());

        //Select Section from Selected Train
        Section section = sectionService.selectSection(train.getId(), train.getTrainNo());

        //Select Available Seat from selected Train
        Seat seat = seatService.assignSeat(false, section.getTrainNo());

        //Construct and Persist Ticket
        Ticket ticket = buildTicket(train, user, seat);
        TicketEntity entity = ticketRepository.saveAndFlush(ticketMapper.toEntity(ticket));
        log.info("Saved Ticket: {}", entity);

        Ticket purchasedTicket = ticketMapper.toResource(entity);

        //Set Seat Details
        SeatEntity seatEntity = seatRepository.findSeatEntityBySeatNo(seat.getSeatNo());
        purchasedTicket.getSeat().setSeatDetail(ServiceUtil.setSeatDetails(seatEntity));

        purchasedTicket.setTrainName(ticket.getTrainName());
        purchasedTicket.setFrom(ticket.getFrom());
        purchasedTicket.setTo(ticket.getTo());

        return purchasedTicket;

    }

    @Override
    public Ticket viewReceiptDetail(String ticketNo) {
        TicketEntity entity = findTicketDetail(ticketNo);

        Ticket ticket = ticketMapper.toResource(entity);
        //Set Seat Details
        SeatEntity seatEntity = seatRepository.findSeatEntityBySeatNo(ticket.getSeat().getSeatNo());
        ticket.getSeat().setSeatDetail(ServiceUtil.setSeatDetails(seatEntity));

        return ticket;
    }

    @Override
    public List<UserResponse> getUsersAndAllocatedSeatsBySection(String sectionName) {
        List<Seat> seats = seatService.getAllSeatsBySectionName(sectionName);
        List<Seat> occupiedSeats = seats.stream().filter(Seat::isOccupied).toList();
        List<UserResponse> userResponses = new ArrayList<>();
        UserResponse response = null;
        for (Seat seat : occupiedSeats) {
            TicketEntity entity = ticketRepository.findTicketEntityBySeat_Id(seat.getId());
            Ticket ticket = ticketMapper.toResource(entity);
            if (ticket != null && ticket.getSeat() != null) {
                response = UserResponse.builder()
                        .seatNo(ticket.getSeat().getSeatNo())
                        .build();
                if (ticket.getUser() != null) {
                    response.setUser(ticket.getUser());
                }
            }
            userResponses.add(response);
        }

        return userResponses;
    }

    @Transactional
    @Override
    public Map<String, String> removeUserFromTicket(String ticketNo) {
        TicketEntity entity = findTicketDetail(ticketNo);
        //Remove and Set Available for the selected Seat
        seatService.unAssignSeat(entity.getSeat().getSeatNo(), true);
        ticketRepository.delete(entity);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Ticket Removed Successfully");
        map.put("removedTicketConfirmationCode", entity.getConfirmationCode());

        return map;
    }

    @Transactional
    @Override
    public Map<String, String> modifyUserSeat(String ticketNo, String preferredSeatNo) {
        TicketEntity entity = findTicketDetail(ticketNo);

        //Remove the Current Allocated Seat and Set as available
        seatService.unAssignSeat(entity.getSeat().getSeatNo(), true);

        //Select new Seat
        Seat seat = seatService.changeSeat(entity.getSeat().getSection().getId(), false, preferredSeatNo);

        SeatEntity seatEntity = seatMapper.toEntity(seat);
        entity.setSeat(seatEntity);
        entity = ticketRepository.saveAndFlush(entity);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Seat Changed Successfully");
        map.put("newSeatoNO", entity.getSeat().getSeatNo());

        return map;
    }

    private TicketEntity findTicketDetail(String ticketNo) {
        TicketEntity entity = ticketRepository.findTicketEntityByTicketNo(ticketNo);
        if (entity == null) {
            throw new RuntimeException("Ticket Receipts Not Found for a given confirmation code or ticket no");
        }
        return entity;
    }

    private Ticket buildTicket(Train train, User user, Seat seat) {
        Ticket ticket = new Ticket();
        ticket.setTicketNo(generateTicketNo());
        ticket.setConfirmationCode(generateConfirmationCode());
        ticket.setTrainName(train.getTrainName());
        ticket.setPrice(20F);
        ticket.setUser(user);
        ticket.setSeat(seat);
        ticket.setFrom(train.getOriginLocation());
        ticket.setTo(train.getDestinationLocation());
        ticket.setTicketStatus(TicketStatus.Confirmed);
        ticket.setPaymentStatus(PaymentStatus.Paid);

        return ticket;
    }
}
