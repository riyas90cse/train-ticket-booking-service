package com.cloud.bees.challenge.mapper;

import com.cloud.bees.challenge.entity.TrainEntity;
import com.cloud.bees.challenge.model.Train;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TrainMapper extends AbstractMapper<TrainEntity, Train> {

    TrainEntity toEntity(Train train);
    Train toResource(TrainEntity entity);
    void copyToEntity(Train train, @MappingTarget TrainEntity entity);
}
