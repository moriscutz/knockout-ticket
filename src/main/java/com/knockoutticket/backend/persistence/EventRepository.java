package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity,Long> {
    List<EventEntity> findByBoxer1IdOrBoxer2Id(Long boxer1Id, Long boxer2Id);
    EventEntity findById(long id);
}
