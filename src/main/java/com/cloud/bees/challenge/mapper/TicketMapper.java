package com.cloud.bees.challenge.mapper;

import com.cloud.bees.challenge.entity.TicketEntity;
import com.cloud.bees.challenge.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper extends AbstractMapper<TicketEntity, Ticket> {

    TicketEntity toEntity(Ticket ticket);

    @Mapping(target = "user.tickets", ignore = true)
    Ticket toResource(TicketEntity entity);
}
