package com.knockoutticket.backend.domain.responses;

import com.knockoutticket.backend.domain.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetAppUserResponse {
    private AppUser user;
}