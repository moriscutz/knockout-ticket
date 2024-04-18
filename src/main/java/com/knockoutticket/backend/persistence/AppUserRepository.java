package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUserEntity, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    AppUserEntity findByUsername(String username);
}
