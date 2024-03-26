package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.impl.UserConverter;
import com.knockoutticket.backend.business.GetAppUserUseCase;
import com.knockoutticket.backend.domain.responses.GetAppUserResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAppUserUseCaseImpl implements GetAppUserUseCase {

    private final AppUserRepository appUserRepository;
    private final UserConverter userConverter;

    @Transactional
    @Override
    public GetAppUserResponse getAppUser(Long id) {
        AppUserEntity foundUser = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return GetAppUserResponse.builder()
                .user(userConverter.toModel(foundUser))
                .build();
    }
}
