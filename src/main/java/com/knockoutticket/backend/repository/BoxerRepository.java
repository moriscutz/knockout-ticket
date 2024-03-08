package com.knockoutticket.backend.repository;

import com.knockoutticket.backend.domain.user.Boxer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoxerRepository extends JpaRepository<Boxer, Long> {
}
