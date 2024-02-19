package com.cloud.bees.challenge.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SEATS")
public class SeatEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SEAT_NO", nullable = false)
    private String seatNo;

    @ManyToOne
    @JoinColumn(name = "SECTION_ID", nullable = false)
    private SectionEntity section;

    @Column(name = "IS_OCCUPIED", nullable = false)
    private boolean isOccupied;
}
