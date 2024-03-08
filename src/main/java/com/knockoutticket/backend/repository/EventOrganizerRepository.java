package com.knockoutticket.backend.repository;

import com.knockoutticket.backend.domain.user.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventOrganizerRepository extends JpaRepository<EventOrganizer, Long> {
}
