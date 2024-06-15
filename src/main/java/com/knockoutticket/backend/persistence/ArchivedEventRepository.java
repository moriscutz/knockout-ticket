package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.persistence.entity.ArchivedEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivedEventRepository extends JpaRepository<ArchivedEventEntity, Long> {
}
