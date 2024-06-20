package com.knockoutticket.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.knockoutticket.backend.domain.models.UserType;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "user_type")
@Data
@Builder
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
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private AppUserEntity user;

    public void updateUserType(UserType type) {
        this.type = type;
    }
}