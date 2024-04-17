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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Builder.Default
    private Set<UserTypeEntity> userRoles = new HashSet<>();

    public void setUserType(UserType userType) {
        this.userRoles.clear();

        UserTypeEntity userTypeEntity = new UserTypeEntity();
        userTypeEntity.setType(userType);
        userTypeEntity.setUser(this);

        this.userRoles.add(userTypeEntity);
    }
}
