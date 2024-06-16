package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.responses.GetBookingsCountByCustomerResponse;
import com.knockoutticket.backend.persistence.BookingRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetBookingsCountByCustomerUseCaseImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private GetBookingsCountByCustomerUseCaseImpl getBookingsCountByCustomerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCountBookingsByCustomer() {
        // Arrange
        AppUserEntity customer1 = new AppUserEntity();
        customer1.setId(1L);
        customer1.setUsername("Customer1");

        AppUserEntity customer2 = new AppUserEntity();
        customer2.setId(2L);
        customer2.setUsername("Customer2");

        GetBookingsCountByCustomerResponse response1 = new GetBookingsCountByCustomerResponse(customer1, 10L);
        GetBookingsCountByCustomerResponse response2 = new GetBookingsCountByCustomerResponse(customer2, 5L);
        List<GetBookingsCountByCustomerResponse> mockResponses = Arrays.asList(response1, response2);

        when(bookingRepository.countBookingsByCustomer()).thenReturn(mockResponses);

        // Act
        List<GetBookingsCountByCustomerResponse> responses = getBookingsCountByCustomerUseCase.getCountBookingsByCustomer();

        // Assert
        assertEquals(2, responses.size());
        assertEquals(customer1, responses.get(0).getCustomer());
        assertEquals(10L, responses.get(0).getBookingCount());
        assertEquals(customer2, responses.get(1).getCustomer());
        assertEquals(5L, responses.get(1).getBookingCount());
    }
}
