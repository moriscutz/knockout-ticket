package com.knockoutticket.backend.service.impl;

import com.knockoutticket.backend.domain.user.AppUser;
import com.knockoutticket.backend.repository.UserRepository;
import com.knockoutticket.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository _userRepository) {
        userRepository = _userRepository; }

    @Override
    public AppUser saveUser(AppUser appUser) {
        return userRepository.save(appUser); }

    @Override
    public Optional<AppUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppUser updateUser(Long id, AppUser appUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setEmail(appUser.getEmail());
                    existingUser.setPassword(appUser.getPassword());
                    existingUser.setUserType(appUser.getUserType());
                    return userRepository.save(existingUser);
                }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(Long id) {
    userRepository.deleteById(id);
    }

}
