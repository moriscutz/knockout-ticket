package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.responses.GetAggregatedBoxerStatsResponse;
import com.knockoutticket.backend.persistence.BoxerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetAggregatedBoxerStatsUseCaseImplTest {

    @Mock
    private BoxerRepository boxerRepository;

    @InjectMocks
    private GetAggregatedBoxerStatsUseCaseImpl getAggregatedBoxerStatsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAggregatedBoxerStats_shouldReturnAggregatedStats() {
        // Arrange
        GetAggregatedBoxerStatsResponse expectedResponse = new GetAggregatedBoxerStatsResponse(10.0, 5.0, 2.0);
        when(boxerRepository.getAggregatedBoxerStats()).thenReturn(expectedResponse);

        // Act
        GetAggregatedBoxerStatsResponse actualResponse = getAggregatedBoxerStatsUseCase.getAggregatedBoxerStats();

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }
}
