package com.knockoutticket.backend.domain.requests;

import com.knockoutticket.backend.domain.models.UserType;
import lombok.Data;

@Data
public class UpdateAppUserRequest {
    private Long id;
    private String username;
    private String email;
    private String password;
    private UserType userType;
}
