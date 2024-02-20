package com.cloud.bees.challenge.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"user", "seat"})
@ToString(exclude = {"user", "seat"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    private Long id;
    private String ticketNo;
    private String trainName;
    private String from;
    private String to;
    private User user;
    private Seat seat;
    private float price;
    private String confirmationCode;
    private PaymentStatus paymentStatus;
    private TicketStatus ticketStatus;
}
