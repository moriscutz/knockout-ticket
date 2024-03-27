package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.persistence.entity.AdministratorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<AdministratorEntity, Long> {
}
