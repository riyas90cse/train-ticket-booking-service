package com.cloud.bees.challenge.mapper;

import com.cloud.bees.challenge.entity.SeatEntity;
import com.cloud.bees.challenge.model.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SeatMapper extends AbstractMapper<SeatEntity, Seat> {

    SeatEntity toEntity(Seat seat);

    @Mapping(target = "seatDetail", ignore = true)
    Seat toResource(SeatEntity entity);
}
