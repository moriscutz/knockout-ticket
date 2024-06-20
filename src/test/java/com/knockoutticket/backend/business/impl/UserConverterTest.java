package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.AppUser;
import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.domain.responses.UpdateUserRolesResponse;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.UserTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserConverterTest {

    private UserConverter userConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userConverter = new UserConverter();
    }

    @Test
    void testToEntityConversion() {
        // Arrange
        AppUser user = new AppUser();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setEmail("testuser@example.com");
        Set<UserType> userTypes = new HashSet<>();
        userTypes.add(UserType.NORMAL_USER);
        user.setUserType(userTypes);

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
    void testToModelConversion() {
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

    @Test
    void testConvertToUserTypeEntities() {
        // Arrange
        Set<UserType> userTypes = new HashSet<>();
        userTypes.add(UserType.NORMAL_USER);
        AppUserEntity userEntity = mock(AppUserEntity.class);

        // Act
        Set<UserTypeEntity> userTypeEntities = userConverter.convertToUserTypeEntities(userTypes, userEntity);

        // Assert
        assertNotNull(userTypeEntities);
        assertEquals(1, userTypeEntities.size());
        UserTypeEntity userTypeEntity = userTypeEntities.iterator().next();
        assertEquals(UserType.NORMAL_USER, userTypeEntity.getType());
        assertEquals(userEntity, userTypeEntity.getUser());
    }

    @Test
    void testToUpdateUserRolesResponse() {
        // Arrange
        AppUserEntity entity = new AppUserEntity();
        entity.setId(1L);
        entity.setUsername("testuser");
        entity.setEmail("testuser@example.com");
        Set<UserTypeEntity> userTypeEntities = new HashSet<>();
        UserTypeEntity userTypeEntity = new UserTypeEntity();
        userTypeEntity.setType(UserType.NORMAL_USER);
        userTypeEntities.add(userTypeEntity);
        entity.setUserRoles(userTypeEntities);

        // Act
        UpdateUserRolesResponse response = userConverter.toUpdateUserRolesResponse(entity);

        // Assert
        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.getUsername(), response.getUsername());
        assertEquals(entity.getEmail(), response.getEmail());
        assertEquals(1, response.getUserRoles().size());
        assertTrue(response.getUserRoles().contains(UserType.NORMAL_USER.name()));
    }
}
