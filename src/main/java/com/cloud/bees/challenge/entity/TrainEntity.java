package com.cloud.bees.challenge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"sections"})
@ToString(exclude = {"sections"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRAIN")
public class TrainEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TRAIN_NO", nullable = false)
    private String trainNo;

    @Column(name = "NAME", nullable = false)
    private String trainName;

    @Column(name = "ORIGIN_LOCATION")
    private String originLocation;

    @Column(name = "DESTINATION_LOCATION")
    private String destinationLocation;

    @OneToMany(mappedBy = "train", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<SectionEntity> sections;

}
