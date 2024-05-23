package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.UserNotFoundException;
import com.knockoutticket.backend.domain.models.AppUser;
import com.knockoutticket.backend.domain.responses.GetAppUserResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAppUserUseCaseImplTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private GetAppUserUseCaseImpl getAppUserUseCase;

    @Test
    void getAppUser_ShouldReturnUser() {
        // Arrange
        Long userId = 1L;

        AppUserEntity userEntity = new AppUserEntity();
        userEntity.setId(userId);
        userEntity.setUsername("testuser");

        AppUser user = new AppUser();
        user.setId(userId);
        user.setUsername("testuser");

        when(appUserRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userConverter.toModel(userEntity)).thenReturn(user);

        // Act
        GetAppUserResponse response = getAppUserUseCase.getAppUser(userId);

        // Assert
        assertEquals(userId, response.getUser().getId());
        assertEquals("testuser", response.getUser().getUsername());

        verify(appUserRepository, times(1)).findById(userId);
        verify(userConverter, times(1)).toModel(userEntity);
    }

    @Test
    void getAppUser_UserNotFound_ShouldThrowException() {
        // Arrange
        Long userId = 1L;

        when(appUserRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> getAppUserUseCase.getAppUser(userId));

        verify(appUserRepository, times(1)).findById(userId);
        verify(userConverter, never()).toModel(any());
    }
}
