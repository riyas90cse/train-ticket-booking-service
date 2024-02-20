package com.cloud.bees.challenge.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"sections"})
@ToString(exclude = {"sections"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Train {

    private Long id;
    private String trainNo;

    @NotNull
    private String trainName;

    @NotNull
    private String originLocation;

    @NotNull
    private String destinationLocation;

    private List<Section> sections;

}