package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.impl.UserConverter;
import com.knockoutticket.backend.business.GetAllAppUsersUseCase;
import com.knockoutticket.backend.domain.responses.GetAppUserResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllAppUsersUseCaseImpl implements GetAllAppUsersUseCase {

    private final AppUserRepository appUserRepository;
    private final UserConverter userConverter;

    @Transactional
    @Override
    public List<GetAppUserResponse> getAllAppUsers() {
        List<AppUserEntity> allUsers = appUserRepository.findAll();
        return allUsers.stream()
                .map(userConverter::toModel)
                .map(GetAppUserResponse::new)
                .collect(Collectors.toList());
    }
}
