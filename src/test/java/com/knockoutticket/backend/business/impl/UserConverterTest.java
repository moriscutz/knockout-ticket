package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.AppUser;
import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.UserTypeEntity;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserConverterTest {

    @Test
    void toEntity() {
        // Arrange
        AppUser user = new AppUser();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setEmail("testuser@example.com");
        Set<UserType> userTypes = new HashSet<>();
        userTypes.add(UserType.NORMAL_USER);
        user.setUserType(userTypes);

        UserConverter userConverter = new UserConverter();

        // Act
        AppUserEntity entity = userConverter.toEntity(user);

        // Assert
        assertNotNull(entity);
        assertEquals(user.getId(), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getPassword(), entity.getPassword());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(userTypes.size(), entity.getUserRoles().size());
        assertEquals(UserTypeEntity.class, entity.getUserRoles().iterator().next().getClass());
    }

    @Test
    void toModel() {
        // Arrange
        AppUserEntity entity = new AppUserEntity();
        entity.setId(1L);
        entity.setUsername("testuser");
        entity.setPassword("password123");
        entity.setEmail("testuser@example.com");
        Set<UserTypeEntity> userTypeEntities = new HashSet<>();
        UserTypeEntity userTypeEntity = new UserTypeEntity();
        userTypeEntity.setType(UserType.NORMAL_USER);
        userTypeEntities.add(userTypeEntity);
        entity.setUserRoles(userTypeEntities);

        UserConverter userConverter = new UserConverter();

        // Act
        AppUser user = userConverter.toModel(entity);

        // Assert
        assertNotNull(user);
        assertEquals(entity.getId(), user.getId());
        assertEquals(entity.getUsername(), user.getUsername());
        assertEquals(entity.getPassword(), user.getPassword());
        assertEquals(entity.getEmail(), user.getEmail());
        assertEquals(userTypeEntities.size(), user.getUserType().size());
        assertEquals(UserType.NORMAL_USER, user.getUserType().iterator().next());
    }
}
