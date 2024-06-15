package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.domain.responses.GetBookingsCountByCustomerResponse;
import com.knockoutticket.backend.persistence.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query("SELECT new com.knockoutticket.backend.domain.responses.GetBookingsCountByCustomerResponse(b.customer_id, COUNT(b)) " +
            "FROM BookingEntity b " +
            "GROUP BY b.customer_id " +
            "ORDER BY COUNT(b) DESC")
    List<GetBookingsCountByCustomerResponse> countBookingsByCustomer();
}
