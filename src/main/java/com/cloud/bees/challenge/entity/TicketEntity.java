package com.cloud.bees.challenge.entity;

import com.cloud.bees.challenge.model.PaymentStatus;
import com.cloud.bees.challenge.model.TicketStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"user", "seat"})
@ToString(exclude = {"user", "seat"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TICKETS")
public class TicketEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TICKET_NO")
    private String ticketNo;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "SEAT_ID")
    private SeatEntity seat;

    @Column(name = "PRICE")
    private float price;

    @Column(name = "CONFIRMATION_CODE")
    private String confirmationCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_STATUS")
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "TICKET_STATUS")
    private TicketStatus ticketStatus;

}

