package com.cloud.bees.challenge.repository;

import com.cloud.bees.challenge.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    TicketEntity findTicketEntityByTicketNo(@Param("ticketNo") String ticketNo);

    TicketEntity findTicketEntityBySeat_Id(@Param("seatId") Long seatId);
}
