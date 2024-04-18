package com.knockoutticket.backend.persistence.entity;

import com.knockoutticket.backend.domain.models.UserType;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "user_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_name", nullable = false)
    private UserType type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private AppUserEntity user;

    public UserTypeEntity(UserType type) {
        this.type = type;
    }
}