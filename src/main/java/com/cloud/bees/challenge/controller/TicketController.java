package com.cloud.bees.challenge.controller;

import com.cloud.bees.challenge.model.Ticket;
import com.cloud.bees.challenge.model.TicketPayload;
import com.cloud.bees.challenge.model.UserResponse;
import com.cloud.bees.challenge.service.impl.TicketingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@Tag(name = "Ticket Controller", description = "Ticket Booking Controller")
public class TicketController {
    private final TicketingService ticketingService;

    @Autowired
    public TicketController(TicketingService ticketingService) {
        this.ticketingService = ticketingService;
    }

    @GetMapping(value = "/tickets")
    public ResponseEntity<Ticket> viewReceipt(@RequestParam(required = false) String ticketNo) {
        Ticket ticket = ticketingService.viewReceiptDetail(ticketNo);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping(value = "/tickets/section/{sectionName}")
    public ResponseEntity<List<UserResponse>> getAllUserBySection(@PathVariable("sectionName") String sectionName) {
        return new ResponseEntity<>(ticketingService.getUsersAndAllocatedSeatsBySection(sectionName), HttpStatus.OK);
    }

    @PostMapping(value = "/tickets")
    public ResponseEntity<Ticket> purchaseTicket(@RequestBody @Valid TicketPayload ticketPayload) {
        return new ResponseEntity<>(ticketingService.purchaseTicket(ticketPayload), HttpStatus.CREATED);
    }

    @PutMapping(value = "/tickets/{ticketNo}")
    public ResponseEntity<Map<String, String>> modifySeat(@PathVariable("ticketNo") String ticketNo,
                                                          @RequestParam String preferredSeatNo) {
        return new ResponseEntity<>(ticketingService.modifyUserSeat(ticketNo, preferredSeatNo), HttpStatus.OK);
    }

    @DeleteMapping(value = "/tickets")
    public ResponseEntity<Map<String, String>> removeUserTicket(@RequestParam String ticketNo) {
        return new ResponseEntity<>(ticketingService.removeUserFromTicket(ticketNo), HttpStatus.OK);
    }

}
