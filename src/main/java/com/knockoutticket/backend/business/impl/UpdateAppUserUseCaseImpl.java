package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.UpdateAppUserUseCase;
import com.knockoutticket.backend.business.exception.BlankEmailException;
import com.knockoutticket.backend.business.exception.BlankPasswordException;
import com.knockoutticket.backend.business.exception.BlankUsernameException;
import com.knockoutticket.backend.domain.requests.UpdateAppUserRequest;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.UserTypeEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class UpdateAppUserUseCaseImpl implements UpdateAppUserUseCase {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public void updateAppUser(Long id, UpdateAppUserRequest request) {
        AppUserEntity existingUser = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        updateUserFields(existingUser, request);
        appUserRepository.save(existingUser);
    }

    private void updateUserFields(AppUserEntity user, UpdateAppUserRequest request) {
        if(request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new BlankEmailException();
        }
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()){
            throw new BlankUsernameException();
        }
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new BlankPasswordException();
        }

        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

//        user.setUserRoles(Set.of(UserTypeEntity.builder()
//                .type(request.getUserType())
//                .user(user)
//                .build()));

        user.getUserRoles().clear();
        user.getUserRoles().add(UserTypeEntity.builder()
                .type(request.getUserType())
                .user(user)
                .build());
    }

}
