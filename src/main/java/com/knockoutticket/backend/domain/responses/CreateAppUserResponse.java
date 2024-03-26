package com.knockoutticket.backend.domain.responses;

import com.knockoutticket.backend.domain.models.AppUser;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class CreateAppUserResponse {
    private Long id;
}