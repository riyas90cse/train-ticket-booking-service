package com.cloud.bees.challenge.service;

import com.cloud.bees.challenge.entity.*;
import com.cloud.bees.challenge.mapper.*;
import com.cloud.bees.challenge.model.*;
import com.cloud.bees.challenge.repository.*;
import com.cloud.bees.challenge.service.impl.*;
import com.cloud.bees.challenge.util.TestData;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


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

    private TestData testData;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository, userMapper);
        trainService = new TrainService(trainRepository, trainMapper);
        sectionService = new SectionService(sectionRepository, sectionMapper, trainRepository);
        seatService = new SeatService(seatRepository, sectionRepository, seatMapper);
        ticketService = new TicketService(userService, trainService, sectionService, seatService, ticketMapper
                , seatMapper, seatRepository, ticketRepository);

        testData = new TestData(userMapper, trainMapper, sectionMapper, seatMapper);

        user = testData.buildUser();
        train = testData.buildTrain();
        payload = new TicketPayload();
        payload.setUserDetail(user);
        section = testData.buildSections().getFirst();
        seat = testData.buildSeats().getFirst();
        ticket = testData.buildTicket();

        ticketEntity = new TicketEntity();
        sectionEntity = testData.buildSectionEntities().getFirst();
        seatEntity = testData.buildSeatEntities().getFirst();
        userEntity = testData.buildUserEntity();
        trainEntity = testData.buildTrainEntity();

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
        when(sectionService.getAvailableSections(anyLong(), anyString())).thenReturn(List.of(section));

        //Seat Service Mocks
        when(seatRepository.findSeatEntityBySeatNo(anyString())).thenReturn(seatEntity);
        when(seatRepository.saveAndFlush(seatEntity)).thenReturn(seatEntity);
        when(seatMapper.toResource(seatEntity)).thenReturn(seat);

        //User Service Mocks
        when(userRepository.findUserEntityByEmail(anyString())).thenReturn(Optional.of(userEntity));
        when(userMapper.toResource(userEntity)).thenReturn(user);
    }


    @Disabled
    @Test
    void testViewReceiptDetail() {
        when(ticketService.viewReceiptDetail(ticket.getTicketNo())).thenReturn(ticket);
        assertNotNull(ticket);

        verify(ticketService, times(1)).viewReceiptDetail(ticket.getTicketNo());
    }

}
