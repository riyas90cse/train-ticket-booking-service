package com.cloud.bees.challenge.mapper;

import com.cloud.bees.challenge.entity.SectionEntity;
import com.cloud.bees.challenge.model.Section;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SectionMapper extends AbstractMapper<SectionEntity, Section> {

    @Mapping(target = "train", ignore = true)
    SectionEntity toEntity(Section section);

    Section toResource(SectionEntity entity);
}
