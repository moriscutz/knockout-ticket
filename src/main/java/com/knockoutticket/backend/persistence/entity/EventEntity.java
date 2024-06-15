package com.knockoutticket.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.knockoutticket.backend.domain.models.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "boxer_id_1")
    private BoxerEntity boxer1;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boxer_id_2")
    private BoxerEntity boxer2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    @JsonBackReference
    private AppUserEntity organizer;

    @Column(name = "event_date")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_status")
    private EventStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_id")
    private BoxerEntity winner;

    @Column(name = "place")
    private String place;
}
