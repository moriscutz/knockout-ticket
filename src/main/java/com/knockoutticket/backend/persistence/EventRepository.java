package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.domain.responses.GetEventsCountByOrganizerResponse;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity,Long> {
    List<EventEntity> findByBoxer1IdOrBoxer2Id(Long boxer1Id, Long boxer2Id);
    EventEntity findById(long id);

    @Query("SELECT new com.knockoutticket.backend.domain.responses.GetEventsCountByOrganizerResponse(e.organizer, COUNT(e)) " +
            "FROM EventEntity e " +
            "GROUP BY e.organizer " +
            "ORDER BY COUNT(e) DESC")
    List<GetEventsCountByOrganizerResponse> countEventsByOrganizer(); //Aggregated use case

    @Modifying
    @Transactional
    @Query("DELETE FROM EventEntity e WHERE e.organizer.id = :organizerId")
    void deleteEventsForOrganizer(@Param("organizerId") Long organizerId);
}
