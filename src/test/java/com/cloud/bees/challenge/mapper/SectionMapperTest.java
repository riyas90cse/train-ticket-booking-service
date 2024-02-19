package com.cloud.bees.challenge.mapper;

import com.cloud.bees.challenge.entity.SectionEntity;
import com.cloud.bees.challenge.model.Section;
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
class SectionMapperTest {

    @Mock
    private SectionMapper mapper;
    @Mock
    private Section section;
    @Mock
    private SectionEntity sectionEntity;


    @BeforeEach
    void setup() {
        section = new Section();
        sectionEntity = new SectionEntity();
    }

    @Test
    void resourceToEntityTest() {
        when(mapper.toEntity(section)).thenReturn(sectionEntity);
        assertNotNull(section);
    }

    @Test
    void entityToResourceTest() {
        when(mapper.toResource(sectionEntity)).thenReturn(section);
        assertNotNull(section);
    }
}
