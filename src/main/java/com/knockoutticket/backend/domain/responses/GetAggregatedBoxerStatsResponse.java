package com.knockoutticket.backend.domain.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAggregatedBoxerStatsResponse {
    private Double averageWins;
    private Double averageLosses;
    private Double averageDraws;
}
