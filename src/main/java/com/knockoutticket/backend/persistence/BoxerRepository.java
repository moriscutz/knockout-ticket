package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.domain.responses.GetAggregatedBoxerStatsResponse;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoxerRepository extends JpaRepository<BoxerEntity, Long> {
    BoxerEntity findByFullNameIgnoreCase(String fullName);

    @Query("SELECT b FROM BoxerEntity b WHERE b.id = :id")
    BoxerEntity findBoxerById(Long id);

    @Query("SELECT b FROM BoxerEntity b WHERE b.id IN (SELECT e.boxer1.id FROM EventEntity e WHERE e.id = :eventId) OR " +
            "b.id IN (SELECT e.boxer2.id FROM EventEntity e WHERE e.id = :eventId)")
    List<BoxerEntity> findBoxersByEventId(@Param("eventId") Long eventId);

    @Query("SELECT new com.knockoutticket.backend.domain.responses.GetAggregatedBoxerStatsResponse(AVG(b.wins), AVG(b.losses), AVG(b.draws)) FROM BoxerEntity b")
    GetAggregatedBoxerStatsResponse getAggregatedBoxerStats();
}
