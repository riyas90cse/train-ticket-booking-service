package com.cloud.bees.challenge.service;

import com.cloud.bees.challenge.model.Ticket;
import com.cloud.bees.challenge.model.TicketPayload;
import com.cloud.bees.challenge.model.UserResponse;

import java.util.List;
import java.util.Map;

public interface ITicketService {

    Ticket purchaseTicket(TicketPayload payload);

    Ticket viewReceiptDetail(String ticketNo);

    List<UserResponse> getUsersAndAllocatedSeatsBySection(String sectionName);

    Map<String, String> removeUserFromTicket(String ticketNo);

    Map<String, String> modifyUserSeat(String ticketNo, String preferredSeatNo);

}
