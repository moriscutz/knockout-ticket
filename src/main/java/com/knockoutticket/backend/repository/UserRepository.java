package com.knockoutticket.backend.repository;

import com.knockoutticket.backend.domain.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}
