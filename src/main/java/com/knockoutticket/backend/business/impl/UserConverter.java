package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.AppUser;
import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import org.springframework.stereotype.Component;

@Component
final class UserConverter {

    public AppUserEntity toEntity(AppUser user) {
        AppUserEntity appUserEntity = AppUserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();

        appUserEntity.setUserType(user.getUserType());

        return appUserEntity;
    }

    public AppUser toModel(AppUserEntity entity) {
        UserType userType = entity.getUserRoles()
                .stream()
                .findFirst()
                .map(userRole -> userRole.getType())
                .orElse(UserType.NORMAL_USER);

        return AppUser.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .userType(userType)
                .build();
    }
}
