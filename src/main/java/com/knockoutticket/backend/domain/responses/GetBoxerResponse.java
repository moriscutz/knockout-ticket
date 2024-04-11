package com.knockoutticket.backend.domain.responses;

import com.knockoutticket.backend.domain.models.Boxer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetBoxerResponse {
    private Boxer boxer;
}
