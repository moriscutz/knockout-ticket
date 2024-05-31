package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.GetAggregatedBoxerStatsUseCase;
import com.knockoutticket.backend.domain.responses.GetAggregatedBoxerStatsResponse;
import com.knockoutticket.backend.persistence.BoxerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAggregatedBoxerStatsUseCaseImpl implements GetAggregatedBoxerStatsUseCase {
    private final BoxerRepository boxerRepository;

    @Override
    public GetAggregatedBoxerStatsResponse getAggregatedBoxerStats(){
        return boxerRepository.getAggregatedBoxerStats();
    }
}
