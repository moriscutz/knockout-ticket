package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.AppUser;
import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.domain.responses.UpdateUserRolesResponse;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.UserTypeEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
final class UserConverter {

    public AppUserEntity toEntity(AppUser user) {
        AppUserEntity entity = new AppUserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setEmail(user.getEmail());
        Set<UserTypeEntity> userTypeEntities = convertToUserTypeEntities(user.getUserType(), entity);
        entity.setUserRoles(userTypeEntities);
        return entity;
    }

    public AppUser toModel(AppUserEntity entity) {
        AppUser user = new AppUser();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setEmail(entity.getEmail());
        Set<UserType> userTypes = convertToUserTypes(entity.getUserRoles());
        user.setUserType(userTypes);
        return user;
    }

    public Set<UserTypeEntity> convertToUserTypeEntities(Set<UserType> userTypes, AppUserEntity userEntity) {
        if (userTypes != null && !userTypes.isEmpty()) {
            return userTypes.stream()
                    .map(type -> {
                        UserTypeEntity userTypeEntity = new UserTypeEntity();
                        userTypeEntity.setType(type);
                        userTypeEntity.setUser(userEntity);
                        return userTypeEntity;
                    })
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    private Set<UserType> convertToUserTypes(Set<UserTypeEntity> userTypeEntities) {
        if (userTypeEntities != null && !userTypeEntities.isEmpty()) {
            Set<UserType> userTypes = new HashSet<>();
            for (UserTypeEntity userTypeEntity : userTypeEntities) {
                userTypes.add(userTypeEntity.getType());
            }
            return userTypes;
        }
        return Collections.emptySet();
    }

    public UpdateUserRolesResponse toUpdateUserRolesResponse(AppUserEntity entity) {
        UpdateUserRolesResponse response = new UpdateUserRolesResponse();
        response.setId(entity.getId());
        response.setUsername(entity.getUsername());
        response.setEmail(entity.getEmail());
        response.setUserRoles(entity.getUserRoles().stream()
                .map(userTypeEntity -> userTypeEntity.getType().name())
                .collect(Collectors.toSet()));
        return response;
    }
}
