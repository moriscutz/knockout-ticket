package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.UpdateUserRolesAsAdminUseCase;
import com.knockoutticket.backend.business.exception.UserNotFoundException;
import com.knockoutticket.backend.domain.requests.UpdateUserRolesRequest;
import com.knockoutticket.backend.domain.responses.UpdateUserRolesResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.UserTypeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UpdateUserRolesAsAdminUseCaseImpl implements UpdateUserRolesAsAdminUseCase {

    private final AppUserRepository appUserRepository;
    private final UserConverter userConverter;

    @Override
    public UpdateUserRolesResponse updateUserRoles(Long userId, UpdateUserRolesRequest request) {
        Optional<AppUserEntity> optionalUserEntity = appUserRepository.findById(userId);
        if (optionalUserEntity.isPresent()) {
            AppUserEntity userEntity = optionalUserEntity.get();

            Set<UserTypeEntity> updatedUserTypes = userConverter.convertToUserTypeEntities(request.getUserTypes(), userEntity);

            userEntity.setUserRoles(updatedUserTypes);

            AppUserEntity updatedEntity = appUserRepository.save(userEntity);

            UpdateUserRolesResponse response = userConverter.toUpdateUserRolesResponse(updatedEntity);

            return response;
        } else {
            throw new UserNotFoundException();
        }
    }
}
