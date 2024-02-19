package com.cloud.bees.challenge.controller;

import com.cloud.bees.challenge.model.Seat;
import com.cloud.bees.challenge.service.impl.SeatService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Hidden
@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "Seat Controller", description = "Testing Purpose")
public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping(value = "/seats")
    public ResponseEntity<List<Seat>> getAll() {
        return new ResponseEntity<>(seatService.getSeats(), HttpStatus.OK);
    }

    @GetMapping(value = "/seats/{id}")
    public ResponseEntity<Seat> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(seatService.getSeat(id), HttpStatus.OK);
    }

    @PostMapping(value = "/seats")
    public ResponseEntity<Seat> create(@RequestBody @Valid Seat seat) {
        Seat savedSeat = seatService.saveSeat(seat);
        return new ResponseEntity<>(savedSeat, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/seats/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        seatService.deleteSeat(id);
        String message = "Seats Deleted Successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
