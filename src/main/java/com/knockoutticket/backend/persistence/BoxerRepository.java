package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoxerRepository extends JpaRepository<BoxerEntity, Long> {
}
