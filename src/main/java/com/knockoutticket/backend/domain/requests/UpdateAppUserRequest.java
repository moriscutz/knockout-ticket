package com.knockoutticket.backend.domain.requests;

import com.knockoutticket.backend.domain.models.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateAppUserRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private UserType userType;
}
