package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.AppUser;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import org.springframework.stereotype.Component;

@Component
final class UserConverter {

    public AppUserEntity toEntity(AppUser user) {
        return AppUserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .userType(user.getUserType())
                .build();
    }

    public AppUser toModel(AppUserEntity entity) {
        return AppUser.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .userType(entity.getUserType())
                .build();
    }
}
