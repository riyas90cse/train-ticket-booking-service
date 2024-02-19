package com.cloud.bees.challenge.service.impl;

import com.cloud.bees.challenge.entity.SeatEntity;
import com.cloud.bees.challenge.entity.SectionEntity;
import com.cloud.bees.challenge.mapper.SeatMapper;
import com.cloud.bees.challenge.model.Seat;
import com.cloud.bees.challenge.model.Section;
import com.cloud.bees.challenge.repository.SeatRepository;
import com.cloud.bees.challenge.repository.SectionRepository;
import com.cloud.bees.challenge.service.ISeatService;
import com.cloud.bees.challenge.util.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.cloud.bees.challenge.service.util.ServiceUtil.setSeatDetails;


@Slf4j
@Service
public class SeatService implements ISeatService {

    private final SeatRepository seatRepository;
    private final SectionRepository sectionRepository;
    private final SeatMapper seatMapper;

    @Autowired
    public SeatService(SeatRepository seatRepository, SectionRepository sectionRepository, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.sectionRepository = sectionRepository;
        this.seatMapper = seatMapper;
    }

    @Transactional
    @Override
    public Seat saveSeat(Seat seat) {
        Optional<SectionEntity> sectionEntity;
        sectionEntity = sectionRepository.findById(Long.valueOf(seat.getSeatDetail().get("sectionId")));

        // Generate SeatNo
        sectionEntity.ifPresent(entity -> seat.setSeatNo(ApplicationUtil.generateSeatNo(entity.getName())));

        SeatEntity entity = seatMapper.toEntity(seat);
        sectionEntity.ifPresent(entity::setSection);
        SeatEntity savedEntity = seatRepository.saveAndFlush(entity);
        Seat savedSeat = seatMapper.toResource(savedEntity);
        savedSeat.setSeatDetail(setSeatDetails(savedEntity));

        log.info("Saved Seat {}", savedSeat);
        return savedSeat;
    }


    @Override
    public Seat getSeat(Long id) {
        Optional<SeatEntity> entity = seatRepository.findById(id);
        if (entity.isEmpty()) {
            throw new RuntimeException("Entity not Found");
        }
        Seat seat = seatMapper.toResource(entity.get());
        seat.setSeatDetail(setSeatDetails(entity.get()));
        log.info("Existing Seat {}", seat);
        return seat;
    }

    @Override
    public List<Seat> getSeats() {
        List<SeatEntity> entities = seatRepository.findAll();
        if (entities.isEmpty()) {
            return Collections.emptyList();
        }
        List<Seat> seats = seatMapper.toResources(entities);
        entities.forEach(seatEntity -> seats.forEach(seat -> seat.setSeatDetail(setSeatDetails(seatEntity))));
        log.info("Existing Seats {}", seats.toString());
        return seats;
    }

    @Transactional
    @Override
    public void deleteSeat(Long id) {
        Optional<SeatEntity> existingEntity = seatRepository.findById(id);
        if (existingEntity.isEmpty()) {
            throw new RuntimeException("Entity not Found");
        }
        log.info("Existing Seat to be deleted {}", existingEntity.get());
        seatRepository.delete(existingEntity.get());
    }

    @Override
    public Seat unAssignSeat(String seatNo, boolean occupied) {
        List<Seat> seats = getSeats(occupied, null, null, seatNo);
        Optional<Seat> seat = seats.stream().filter(s -> s.getSeatNo().equalsIgnoreCase(seatNo)).findFirst();
        if (seat.isEmpty()) {
            throw new RuntimeException("Seat not found");
        }
        SeatEntity seatEntity = seatRepository.findSeatEntityBySeatNo(seat.get().getSeatNo());
        seatEntity.setOccupied(!occupied);
        SeatEntity savedEntity = seatRepository.saveAndFlush(seatEntity);
        return seatMapper.toResource(savedEntity);
    }

    @Override
    public Seat assignSeat(boolean occupied, String trainNo) {
        List<Seat> seats = getSeats(occupied, null, trainNo, null);
        Seat seat = seats.getFirst();
        SeatEntity seatEntity = seatRepository.findSeatEntityBySeatNo(seat.getSeatNo());
        seatEntity.setOccupied(!occupied);
        SeatEntity savedEntity = seatRepository.saveAndFlush(seatEntity);
        return seatMapper.toResource(savedEntity);
    }

    @Override
    public Seat changeSeat(Long sectionId, boolean occupied, String preferredSeatNo) {
        List<Seat> seats = getSeats(occupied, sectionId, null, null);
        Optional<Seat> seat = seats.stream().filter(s -> s.getSeatNo().equalsIgnoreCase(preferredSeatNo)).filter(s -> !s.isOccupied()).findFirst();
        if (seat.isEmpty()) {
            throw new RuntimeException("Given Preferred Seat is not Available at the moment");
        }
        SeatEntity seatEntity = seatRepository.findSeatEntityBySeatNo(seat.get().getSeatNo());
        seatEntity.setOccupied(!occupied);
        SeatEntity savedEntity = seatRepository.saveAndFlush(seatEntity);
        return seatMapper.toResource(savedEntity);
    }

    @Override
    public List<Seat> getAllSeatsBySectionName(String name) {
        List<SeatEntity> entities = seatRepository.findSeatEntitiesBySection_Name(name);
        if (entities.isEmpty()) {
            throw new RuntimeException("There is no available in the given section");
        }
        List<Seat> seats = seatMapper.toResources(entities);
        entities.forEach(seatEntity -> seats.forEach(seat -> seat.setSeatDetail(setSeatDetails(seatEntity))));
        return seats;
    }

    private List<Seat> getSeats(boolean occupied, Long sectionId, String trainNo, String seatNo) {
        List<SeatEntity> seats;
        if (seatNo != null) {
            seats = List.of(seatRepository.findSeatEntityBySeatNo(seatNo));
        } else if (sectionId != null) {
            seats = seatRepository.findAvailableOrOccupiedSeats(occupied, sectionId);
        } else {
            seats = seatRepository.findAvailableSeats(trainNo, occupied);
        }
        if (seats.isEmpty()) {
            throw new RuntimeException("There is no available or occupied seats on the give section of the train");
        }
        return seatMapper.toResources(seats);
    }
}
