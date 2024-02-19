package com.cloud.bees.challenge.service;

import com.cloud.bees.challenge.entity.*;
import com.cloud.bees.challenge.mapper.*;
import com.cloud.bees.challenge.model.*;
import com.cloud.bees.challenge.repository.*;
import com.cloud.bees.challenge.service.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@Disabled
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private TrainService trainService;
    @Mock
    private SectionService sectionService;
    @Mock
    private SeatService seatService;
    @Mock
    private TicketMapper ticketMapper;
    @Mock
    private SeatMapper seatMapper;
    @Mock
    private SeatRepository seatRepository;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private TicketService ticketService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private TrainRepository trainRepository;
    @Mock
    private TrainMapper trainMapper;
    @Mock
    private SectionRepository sectionRepository;
    @Mock
    private SectionMapper sectionMapper;
    @Mock
    private User user;
    @Mock
    private Train train;
    @Mock
    private Section section;
    @Mock
    private Seat seat;
    @Mock
    TicketPayload payload;
    @Mock
    private Ticket ticket;
    @Mock
    TicketEntity ticketEntity;
    @Mock
    SeatEntity seatEntity;
    @Mock
    SectionEntity sectionEntity;
    @Mock
    private UserEntity userEntity;
    @Mock
    private TrainEntity trainEntity;


    @BeforeEach
    void setup() {
        userService = new UserService(userRepository, userMapper);
        trainService = new TrainService(trainRepository, trainMapper);
        sectionService = new SectionService(sectionRepository, sectionMapper, trainRepository);
        seatService = new SeatService(seatRepository, sectionRepository, seatMapper);
        ticketService = new TicketService(userService, trainService, sectionService, seatService, ticketMapper
                , seatMapper, seatRepository, ticketRepository);

        user = new User();
        train = new Train();
        payload = new TicketPayload();
        payload.setUserDetail(user);
        section = new Section();
        seat = new Seat();
        ticket = new Ticket();

        ticketEntity = new TicketEntity();
        sectionEntity = new SectionEntity();
        seatEntity = new SeatEntity();
        userEntity = new UserEntity();
        trainEntity = new TrainEntity();

        //Train service Mocks
        when(trainRepository.findTrainEntitiesByOriginLocationAndDestinationLocation(anyString(), anyString()))
                .thenReturn(List.of(trainEntity));
        when(trainService.getAvailableTrainsByFromAndToLocation(anyString(), anyString()))
                .thenReturn(List.of(train));
        when(trainMapper.toResources(List.of(trainEntity))).thenReturn(List.of(train));

        //Section Service Mocks
        when(sectionRepository.findSectionEntitiesByTrain_Id(anyLong())).thenReturn(List.of(sectionEntity));
        when(sectionRepository.findSectionEntitiesByTrain_TrainNo(anyString())).thenReturn(List.of(sectionEntity));
        when(sectionService.getSections(List.of(sectionEntity))).thenReturn(List.of(section));
        when(sectionMapper.toResources(List.of(sectionEntity))).thenReturn(List.of(section));
//        when(sectionService.getAvailableSections(anyLong(), anyString())).thenReturn(List.of(section));

        //Seat Service Mocks
        when(seatRepository.findSeatEntityBySeatNo(anyString())).thenReturn(seatEntity);
        when(seatRepository.saveAndFlush(seatEntity)).thenReturn(seatEntity);
        when(seatMapper.toResource(seatEntity)).thenReturn(seat);

        //User Service Mocks
        when(userRepository.findUserEntityByEmail(anyString())).thenReturn(Optional.of(userEntity));
        when(userMapper.toResource(userEntity)).thenReturn(user);
    }

    @Test
    void testPurchaseTicket() {

        when(userService.getUserByEmail(anyString())).thenReturn(user);
        when(trainService.selectTrain(anyString(), anyString())).thenReturn(train);
        when(sectionService.selectSection(anyLong(), anyString())).thenReturn(section);
        when(seatService.assignSeat(anyBoolean(), anyString())).thenReturn(seat);
        when(ticketService.buildTicket(train, user, seat)).thenReturn(ticket);
        when(ticketMapper.toEntity(ticket)).thenReturn(ticketEntity);
        when(ticketRepository.saveAndFlush(ticketEntity)).thenReturn(ticketEntity);
        when(ticketMapper.toResource(ticketEntity)).thenReturn(ticket);

        ticket = ticketService.purchaseTicket(payload);
        assertNotNull(ticket);

        verify(userService, times(1)).getUserByEmail(anyString());
        verify(trainService, times(1)).selectTrain(anyString(), anyString());
        verify(sectionService, times(1)).selectSection(anyLong(), anyString());
        verify(userService, times(1)).getUserByEmail(anyString());
    }

}
