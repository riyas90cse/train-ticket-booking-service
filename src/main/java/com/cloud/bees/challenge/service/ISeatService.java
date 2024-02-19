package com.cloud.bees.challenge.service;

import com.cloud.bees.challenge.model.Seat;

import java.util.List;

public interface ISeatService {

    Seat saveSeat(Seat seat);

    Seat getSeat(Long id);

    List<Seat> getSeats();

    void deleteSeat(Long id);

    Seat unAssignSeat(String seatNo, boolean occupied);

    Seat assignSeat(boolean occupied, String trainNo);

    Seat changeSeat(Long sectionId, boolean occupied, String preferredSeatNo);

    List<Seat> getAllSeatsBySectionName(String name);
}
