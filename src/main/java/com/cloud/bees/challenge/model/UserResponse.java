package com.cloud.bees.challenge.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private User user;
    private String seatNo;
}
