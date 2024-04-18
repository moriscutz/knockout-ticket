package com.knockoutticket.backend.persistence.entity;

import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.persistence.entity.UserTypeEntity;
import lombok.*;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserTypeEntity> userRoles = new HashSet<>();

    public void addUserRole(UserType userType) {
        UserTypeEntity userTypeEntity = new UserTypeEntity(userType);
        userTypeEntity.setUser(this);
        userRoles.add(userTypeEntity);
    }

    public void removeUserRole(UserType userType) {
        userRoles.removeIf(userTypeEntity -> userTypeEntity.getType() == userType);
    }
}
