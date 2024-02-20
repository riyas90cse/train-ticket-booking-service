package com.cloud.bees.challenge.util;

import com.cloud.bees.challenge.entity.SeatEntity;
import com.cloud.bees.challenge.entity.SectionEntity;
import com.cloud.bees.challenge.entity.TrainEntity;
import com.cloud.bees.challenge.entity.UserEntity;
import com.cloud.bees.challenge.mapper.SeatMapper;
import com.cloud.bees.challenge.mapper.SectionMapper;
import com.cloud.bees.challenge.mapper.TrainMapper;
import com.cloud.bees.challenge.mapper.UserMapper;
import com.cloud.bees.challenge.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestData {
    private final UserMapper userMapper;
    private final TrainMapper trainMapper;
    private final SectionMapper sectionMapper;
    private final SeatMapper seatMapper;

    public TestData(UserMapper userMapper, TrainMapper trainMapper, SectionMapper sectionMapper,
                    SeatMapper seatMapper) {
        this.userMapper = userMapper;
        this.trainMapper = trainMapper;
        this.sectionMapper = sectionMapper;
        this.seatMapper = seatMapper;
    }

    public User buildUser() {
        return User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@gmail.com")
                .build();
    }

    public UserEntity buildUserEntity() {
        return userMapper.toEntity(buildUser());
    }

    public Train buildTrain() {
        return Train.builder()
                .id(1L)
                .trainNo("TR_1001")
                .trainName("CFR Euro Rail")
                .originLocation("London")
                .destinationLocation("France")
                .build();
    }

    public TrainEntity buildTrainEntity() {
        TrainEntity trainEntity = trainMapper.toEntity(buildTrain());
        trainEntity.setSections(buildSectionEntities());
        return trainEntity;
    }

    public List<Section> buildSections() {

        Section sectionA = Section.builder()
                .id(1L)
                .name("A")
                .description("Section A")
                .trainNo(buildTrain().getTrainNo())
                .trainName(buildTrain().getTrainName())
                .build();

        Section sectionB = Section.builder()
                .id(2L)
                .name("B")
                .description("Section B")
                .trainNo(buildTrain().getTrainNo())
                .trainName(buildTrain().getTrainName())
                .build();

        return Arrays.asList(sectionA, sectionB);
    }

    public List<SectionEntity> buildSectionEntities() {
        List<Section> sections = buildSections();
        List<SectionEntity> entities = new ArrayList<>();
        for (Section section : sections) {
            SectionEntity sectionEntity = sectionMapper.toEntity(section);
            sectionEntity.setTrain(buildTrainEntity());
            entities.add(sectionEntity);
        }
        return entities;
    }

    public List<Seat> buildSeats() {
        Seat seatA01 = Seat.builder()
                .id(1L)
                .seatNo("SC_A_01")
                .isOccupied(false)
                .build();

        Seat seatA02 = Seat.builder()
                .id(2L)
                .seatNo("SC_A_02")
                .isOccupied(false)
                .build();

        Seat seatB01 = Seat.builder()
                .id(3L)
                .seatNo("SC_B_01")
                .isOccupied(false)
                .build();

        Seat seatB02 = Seat.builder()
                .id(4L)
                .seatNo("SC_B_02")
                .isOccupied(false)
                .build();

        return Arrays.asList(seatA01, seatA02, seatB01, seatB02);
    }


    public List<SeatEntity> buildSeatEntities() {
        List<Seat> seats = buildSeats();
        List<SeatEntity> entities = new ArrayList<>();
        for (Seat seat : seats) {
            SeatEntity entity = seatMapper.toEntity(seat);
            entities.add(entity);
        }
        return entities;
    }


    public Ticket buildTicket() {
        return Ticket.builder()
                .id(1L)
                .ticketNo("TK_0001")
                .trainName(buildTrain().getTrainName())
                .from("London")
                .to("France")
                .user(buildUser())
                .seat(buildSeats().getFirst())
                .price(20F)
                .confirmationCode("CNF_0001")
                .paymentStatus(PaymentStatus.Paid)
                .ticketStatus(TicketStatus.Confirmed)
                .build();
    }

}
