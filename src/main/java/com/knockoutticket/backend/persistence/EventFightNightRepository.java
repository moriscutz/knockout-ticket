package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventFightNightRepository extends JpaRepository<EventFightNightEntity, Long> {
}
