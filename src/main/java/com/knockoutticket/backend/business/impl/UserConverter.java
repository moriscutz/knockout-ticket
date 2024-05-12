package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.AppUser;
import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.UserTypeEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
final class UserConverter {

    public AppUserEntity toEntity(AppUser user) {
        AppUserEntity entity = new AppUserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setEmail(user.getEmail());
        Set<UserTypeEntity> userTypeEntities = convertToUserTypeEntities(user.getUserType());
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

    private Set<UserTypeEntity> convertToUserTypeEntities(Set<UserType> userTypes) {
        if (userTypes != null && !userTypes.isEmpty()) {
            Set<UserTypeEntity> userTypeEntities = new HashSet<>();
            for (UserType userType : userTypes) {
                UserTypeEntity userTypeEntity = new UserTypeEntity();
                userTypeEntity.setType(userType);
                // might be necessary later on
                // userTypeEntity.setUser(userEntity);
                userTypeEntities.add(userTypeEntity);
            }
            return userTypeEntities;
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
}
