package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.CreateAppUserUseCase;
import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.domain.requests.CreateAppUserRequest;
import com.knockoutticket.backend.domain.responses.CreateAppUserResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class CreateAppUserUseCaseImpl implements CreateAppUserUseCase {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public CreateAppUserResponse createAppUser(CreateAppUserRequest request) {
        if (appUserRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("User with username already exists");
        }
        else if (appUserRepository.existsByEmail(request.getEmail()))
        {
            throw new RuntimeException("User with email already exists");
        }
        else {
            AppUserEntity newAppUserEntity = saveNewAppUser(request);
            return CreateAppUserResponse.builder()
                    .id(newAppUserEntity.getId())
                    .build();
        }
    }

    private AppUserEntity saveNewAppUser(CreateAppUserRequest request) {
        String encodedPassword =passwordEncoder.encode(request.getPassword());
        AppUserEntity newAppUser = AppUserEntity.builder()
                .username(request.getUsername())
                .password(encodedPassword)
                .email(request.getEmail())
                .userType(UserType.NORMAL_USER)
                .build();
        return appUserRepository.save(newAppUser);
    }
}
