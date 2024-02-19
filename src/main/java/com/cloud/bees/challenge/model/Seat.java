package com.cloud.bees.challenge.model;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    private Long id;
    private String seatNo;
    private boolean isOccupied;
    private Map<String, String> seatDetail;
}
