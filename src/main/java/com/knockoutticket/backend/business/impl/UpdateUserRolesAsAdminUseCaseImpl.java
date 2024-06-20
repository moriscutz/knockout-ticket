package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.UpdateUserRolesAsAdminUseCase;
import com.knockoutticket.backend.business.exception.UserNotFoundException;
import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.domain.requests.UpdateUserRolesRequest;
import com.knockoutticket.backend.domain.responses.UpdateUserRolesResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.UserTypeEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserRolesAsAdminUseCaseImpl implements UpdateUserRolesAsAdminUseCase {

    private final AppUserRepository appUserRepository;
    private final UserConverter userConverter;

    @Override
    @Transactional
    public UpdateUserRolesResponse updateUserRoles(Long userId, UpdateUserRolesRequest request) {
        AppUserEntity userEntity = appUserRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        userEntity.getUserRoles().clear();

        for (UserType userType : request.getUserType()) {
            UserTypeEntity userTypeEntity = new UserTypeEntity();
            userTypeEntity.setType(userType);
            userTypeEntity.setUser(userEntity);
            userEntity.getUserRoles().add(userTypeEntity);
        }

        appUserRepository.save(userEntity);

        return userConverter.toUpdateUserRolesResponse(userEntity);
    }
}
