package com.knockoutticket.backend.domain.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    private Long id;
    private String username;
    private String email;
    private String password;
    private UserType userType;
}
