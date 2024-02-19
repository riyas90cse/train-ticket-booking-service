package com.cloud.bees.challenge.mapper;

import com.cloud.bees.challenge.entity.TrainEntity;
import com.cloud.bees.challenge.model.Train;
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
class TrainMapperTest {

    @Mock
    private TrainMapper mapper;
    @Mock
    private Train train;
    @Mock
    private TrainEntity trainEntity;

    @BeforeEach
    void setup() {
        train = new Train();
        trainEntity = new TrainEntity();
    }

    @Test
    void resourceToEntityTest() {
        when(mapper.toEntity(train)).thenReturn(trainEntity);
        assertNotNull(train);
    }

    @Test
    void entityToResourceTest() {
        when(mapper.toResource(trainEntity)).thenReturn(train);
        assertNotNull(train);
    }
}
