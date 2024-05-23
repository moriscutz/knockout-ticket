package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoxerRepository extends JpaRepository<BoxerEntity, Long> {
    BoxerEntity findByFullNameIgnoreCase(String fullName);

    @Query("SELECT b FROM BoxerEntity b WHERE b.id = :id")
    BoxerEntity findBoxerById(Long id);
}
