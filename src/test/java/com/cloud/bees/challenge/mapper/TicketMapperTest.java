package com.cloud.bees.challenge.mapper;

import com.cloud.bees.challenge.entity.TicketEntity;
import com.cloud.bees.challenge.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketMapperTest {

    @Mock
    private TicketMapper mapper;
    @Mock
    private Ticket ticket;
    @Mock
    private TicketEntity ticketEntity;

    @BeforeEach
    void setup() {
        ticket = new Ticket();
        ticketEntity = new TicketEntity();
    }

    @Test
    void resourceToEntityTest() {
        when(mapper.toEntity(ticket)).thenReturn(ticketEntity);
        assertNotNull(ticket);
    }

    @Test
    void entityToResourceTest() {
        when(mapper.toResource(ticketEntity)).thenReturn(ticket);
        assertNotNull(ticket);
    }
}
