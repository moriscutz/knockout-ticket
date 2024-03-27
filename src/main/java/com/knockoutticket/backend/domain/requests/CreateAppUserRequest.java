package com.knockoutticket.backend.domain.requests;

import com.knockoutticket.backend.domain.models.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppUserRequest {

    private String username;
    private String email;
    private String password;

}
