package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.UserNotFoundException;
import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.domain.requests.UpdateUserRolesRequest;
import com.knockoutticket.backend.domain.responses.UpdateUserRolesResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateUserRolesAsAdminUseCaseImplTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private UpdateUserRolesAsAdminUseCaseImpl updateUserRolesAsAdminUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateUserRoles_Success() {
        // Arrange
        Long userId = 1L;
        AppUserEntity mockUserEntity = new AppUserEntity();
        mockUserEntity.setId(userId);
        mockUserEntity.setUsername("testuser");
        mockUserEntity.setEmail("testuser@example.com");
        mockUserEntity.setUserRoles(new HashSet<>());

        UpdateUserRolesRequest request = new UpdateUserRolesRequest();
        request.setUserType(Set.of(UserType.ADMINISTRATOR, UserType.EVENT_ORGANIZER));

        UpdateUserRolesResponse mockResponse = new UpdateUserRolesResponse();
        mockResponse.setId(userId);
        mockResponse.setUsername("testuser");
        mockResponse.setEmail("testuser@example.com");
        mockResponse.setUserRoles(request.getUserType().stream().map(UserType::name).collect(Collectors.toSet()));

        when(appUserRepository.findById(userId)).thenReturn(Optional.of(mockUserEntity));
        when(userConverter.toUpdateUserRolesResponse(any(AppUserEntity.class))).thenReturn(mockResponse);

        // Act
        UpdateUserRolesResponse response = updateUserRolesAsAdminUseCase.updateUserRoles(userId, request);

        // Assert
        assertNotNull(response);
        assertEquals(userId, response.getId());
        assertEquals("testuser", response.getUsername());
        assertEquals("testuser@example.com", response.getEmail());
        assertTrue(response.getUserRoles().contains("ADMINISTRATOR"));
        assertTrue(response.getUserRoles().contains("EVENT_ORGANIZER"));

        verify(appUserRepository, times(1)).findById(userId);
        verify(appUserRepository, times(1)).save(mockUserEntity);
        verify(userConverter, times(1)).toUpdateUserRolesResponse(mockUserEntity);
    }

    @Test
    void testUpdateUserRoles_UserNotFound() {
        // Arrange
        Long userId = 999L;
        UpdateUserRolesRequest request = new UpdateUserRolesRequest();
        request.setUserType(Set.of(UserType.ADMINISTRATOR , UserType.EVENT_ORGANIZER));

        when(appUserRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> updateUserRolesAsAdminUseCase.updateUserRoles(userId, request));

        verify(appUserRepository, times(1)).findById(userId);
        verify(appUserRepository, never()).save(any(AppUserEntity.class));
        verify(userConverter, never()).toUpdateUserRolesResponse(any(AppUserEntity.class));
    }
}
