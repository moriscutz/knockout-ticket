package com.knockoutticket.backend.service;

import com.knockoutticket.backend.domain.user.AppUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    AppUser saveUser(AppUser appUser);
    Optional<AppUser> getUserById(Long id);
    List<AppUser> getAllUsers();
    AppUser updateUser(Long id, AppUser appUser);
    void deleteUser(Long id);
}
