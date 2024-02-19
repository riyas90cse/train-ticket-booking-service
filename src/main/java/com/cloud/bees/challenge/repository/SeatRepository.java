package com.cloud.bees.challenge.repository;

import com.cloud.bees.challenge.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

    @Query(value = "select e from SeatEntity e where e.isOccupied =:isOccupied and e.section.id=:sectionId")
    List<SeatEntity> findAvailableOrOccupiedSeats(@Param("isOccupied") boolean isOccupied, @Param("sectionId") Long sectionId);

    List<SeatEntity> findSeatEntitiesBySection_Name(@Param("name") String name);

    SeatEntity findSeatEntityBySeatNo(@Param("seatNo") String seatNo);

    @Query(value = "select e from SeatEntity e where e.section.train.trainNo=:trainNo and e.isOccupied=:isOccupied")
    List<SeatEntity> findAvailableSeats(@Param("trainNo") String trainNo, @Param("isOccupied") boolean isOccupied);

}
