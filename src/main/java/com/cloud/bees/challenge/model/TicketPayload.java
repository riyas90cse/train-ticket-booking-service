package com.cloud.bees.challenge.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketPayload {

    @NotNull
    private User userDetail;

    @NotNull
    private String from;
    @NotNull
    private String to;

    private String trainNo;
    private String preferredSection;
    private String preferredSeat;

}
