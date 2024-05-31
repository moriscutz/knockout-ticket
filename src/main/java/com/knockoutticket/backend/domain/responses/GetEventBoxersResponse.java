package com.knockoutticket.backend.domain.responses;

import com.knockoutticket.backend.domain.models.Boxer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetEventBoxersResponse {
    private Boxer boxer1;
    private Boxer boxer2;
}
