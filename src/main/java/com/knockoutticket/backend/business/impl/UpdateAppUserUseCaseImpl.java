package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.UpdateAppUserUseCase;
import com.knockoutticket.backend.domain.requests.UpdateAppUserRequest;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateAppUserUseCaseImpl implements UpdateAppUserUseCase {

    private final AppUserRepository appUserRepository;
    private final UserConverter userConverter;

    @Transactional
    @Override
    public void updateAppUser(Long id, UpdateAppUserRequest request) {
        AppUserEntity existingUser = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        updateUserFields(existingUser, request);
        appUserRepository.save(existingUser);
    }

    private void updateUserFields(AppUserEntity user, UpdateAppUserRequest request) {
        // Update user fields based on the request
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getPassword() != null) {
            user.setPassword(request.getPassword());
        }
        // Similarly, update other fields as needed
    }
}