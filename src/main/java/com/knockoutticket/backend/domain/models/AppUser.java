package com.knockoutticket.backend.domain.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AppUser {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Set<UserType> userType = new HashSet<>();
}
