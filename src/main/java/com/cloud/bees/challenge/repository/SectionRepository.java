package com.cloud.bees.challenge.repository;

import com.cloud.bees.challenge.entity.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

    List<SectionEntity> findSectionEntitiesByTrain_Id(@Param("trainId") Long trainId);

    List<SectionEntity> findSectionEntitiesByTrain_TrainNo(@Param("trainNo") String trainNo);
}
