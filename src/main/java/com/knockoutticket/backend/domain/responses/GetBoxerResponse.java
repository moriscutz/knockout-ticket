package com.knockoutticket.backend.domain.responses;

import com.knockoutticket.backend.domain.models.Boxer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBoxerResponse {
    private Boxer boxer;
}
