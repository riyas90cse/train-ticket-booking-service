package com.cloud.bees.challenge.mapper;

import com.cloud.bees.challenge.entity.SeatEntity;
import com.cloud.bees.challenge.model.Seat;
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
class SeatMapperTest {

    @Mock
    private SeatMapper mapper;
    @Mock
    private Seat seat;
    @Mock
    private SeatEntity seatEntity;


    @BeforeEach
    void setup() {
        seat = new Seat();
        seatEntity = new SeatEntity();
    }

    @Test
    void resourceToEntityTest() {
        when(mapper.toEntity(seat)).thenReturn(seatEntity);
        assertNotNull(seat);
    }

    @Test
    void entityToResourceTest() {
        when(mapper.toResource(seatEntity)).thenReturn(seat);
        assertNotNull(seat);
    }
}
