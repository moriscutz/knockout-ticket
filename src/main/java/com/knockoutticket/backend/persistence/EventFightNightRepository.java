package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventFightNightRepository extends JpaRepository<EventFightNightEntity, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM EventEntity e WHERE e.id = :fightNightId")
    void deleteAllEventsByFightNightId(@Param("fightNightId") Long fightNightId);
}
