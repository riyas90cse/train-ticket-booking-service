package com.cloud.bees.challenge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"seats"})
@ToString(exclude = {"seats"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SECTIONS")
public class SectionEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TRAIN_ID", nullable = false)
    private TrainEntity train;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "section", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<SeatEntity> seats;
}
