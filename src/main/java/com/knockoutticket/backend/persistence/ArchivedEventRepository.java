package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.persistence.entity.ArchivedEventEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArchivedEventRepository extends JpaRepository<ArchivedEventEntity, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM ArchivedEventEntity e where e.organizer.id = :organizerId")
    void deleteArchivedEventsByOrganizerId(@Param("organizerId") Long organizerId);
}
