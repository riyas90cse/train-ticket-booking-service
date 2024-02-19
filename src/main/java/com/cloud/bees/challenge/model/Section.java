package com.cloud.bees.challenge.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"seats"})
@ToString(exclude = {"seats"})
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    private Long id;
    private String trainNo;
    private String trainName;

    @NotNull
    private String name;

    private String description;

    private List<Seat> seats;
}
