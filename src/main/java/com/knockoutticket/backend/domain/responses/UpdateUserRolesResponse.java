package com.knockoutticket.backend.domain.responses;

import lombok.Data;

import java.util.Set;

@Data
public class UpdateUserRolesResponse {
    private Long id;
    private String username;
    private String email;
    private Set<String> userRoles;
}