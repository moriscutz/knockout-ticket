package com.knockoutticket.backend.persistence.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_users")
@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<UserTypeEntity> userRoles = new HashSet<>();


    // Add a user role
    public void addUserRole(UserTypeEntity userRole) {
        userRoles.clear();
        userRoles.add(userRole);
        userRole.setUser(this);
    }

    // Remove a user role
    public void removeUserRole(UserTypeEntity userRole) {
        userRoles.remove(userRole);
        userRole.setUser(null);
    }


}
