package com.knockoutticket.backend.persistence.entity;

import com.knockoutticket.backend.domain.models.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "user_type")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "type_name")
    @Enumerated(EnumType.STRING)
    private UserType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AppUserEntity user;
}