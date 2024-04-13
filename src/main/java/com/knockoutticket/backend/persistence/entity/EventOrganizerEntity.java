package com.knockoutticket.backend.persistence.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "event_organizers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventOrganizerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organization_name")
    private String organizationName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id", referencedColumnName = "id")
    private AppUserEntity appUser;
}
