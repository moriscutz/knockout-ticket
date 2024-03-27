package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity,Long> {
}
