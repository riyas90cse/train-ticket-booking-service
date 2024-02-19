package com.cloud.bees.challenge.repository;

import com.cloud.bees.challenge.entity.TrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<TrainEntity, Long> {

    List<TrainEntity> findTrainEntitiesByOriginLocationAndDestinationLocation(@Param("originLocation")
                                                                              String originLocation,
                                                                              @Param("destinationLocation")
                                                                              String destinationLocation);

    Optional<TrainEntity> findTrainEntityByTrainNoOrTrainName(@Param("trainNo") String trainNo,
                                                              @Param("trainName") String trainName);
}
