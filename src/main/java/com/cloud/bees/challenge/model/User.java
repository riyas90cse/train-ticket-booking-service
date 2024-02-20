package com.cloud.bees.challenge.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"tickets"})
@ToString(exclude = {"tickets"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    private List<Ticket> tickets;
}
