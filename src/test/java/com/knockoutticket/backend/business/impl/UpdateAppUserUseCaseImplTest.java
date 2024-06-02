package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.BlankEmailException;
import com.knockoutticket.backend.business.exception.BlankPasswordException;
import com.knockoutticket.backend.business.exception.BlankUsernameException;
import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.domain.requests.UpdateAppUserRequest;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.UserTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateAppUserUseCaseImplTest {

    @Mock
    private AppUserRepository appUserRepository;


    @InjectMocks
    private UpdateAppUserUseCaseImpl updateAppUserUseCase;

    private AppUserEntity existingUser;

    @BeforeEach
    void setUp() {
        existingUser = new AppUserEntity();
        existingUser.setId(1L);
        existingUser.setUsername("existingUser");
        existingUser.setEmail("existing@example.com");
        existingUser.setPassword("encodedPassword");
        existingUser.setUserRoles(new HashSet<>(Set.of(UserTypeEntity.builder()
                .type(UserType.NORMAL_USER)
                .user(existingUser)
                .build())));
    }

    @Test
    void updateAppUser_ShouldThrowException_WhenEmailIsBlank() {
        // Arrange
        Long userId = 1L;
        UpdateAppUserRequest request = new UpdateAppUserRequest();
        request.setId(userId);
        request.setEmail("   ");
        request.setUsername("username");
        request.setPassword("password");
        request.setUserType(UserType.ADMINISTRATOR);

        when(appUserRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        // Act & Assert
        assertThrows(BlankEmailException.class, () -> updateAppUserUseCase.updateAppUser(userId, request));
    }

    @Test
    void updateAppUser_ShouldThrowException_WhenUsernameIsBlank() {
        // Arrange
        Long userId = 1L;
        UpdateAppUserRequest request = new UpdateAppUserRequest();
        request.setId(userId);
        request.setUsername("   ");
        request.setEmail("email@example.com");
        request.setPassword("password");
        request.setUserType(UserType.ADMINISTRATOR);

        when(appUserRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        // Act & Assert
        assertThrows(BlankUsernameException.class, () -> updateAppUserUseCase.updateAppUser(userId, request));
    }

    @Test
    void updateAppUser_ShouldThrowException_WhenPasswordIsBlank() {
        // Arrange
        Long userId = 1L;
        UpdateAppUserRequest request = new UpdateAppUserRequest();
        request.setId(userId);
        request.setPassword("   ");
        request.setEmail("email@example.com");
        request.setUsername("username");
        request.setUserType(UserType.ADMINISTRATOR);

        when(appUserRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        // Act & Assert
        assertThrows(BlankPasswordException.class, () -> updateAppUserUseCase.updateAppUser(userId, request));
    }

    @Test
    void updateAppUser_ShouldThrowException_WhenUserNotFound() {
        // Arrange
        Long userId = 1L;
        UpdateAppUserRequest request = new UpdateAppUserRequest();
        request.setId(userId);
        request.setUsername("updatedUser");
        request.setEmail("updated@example.com");
        request.setPassword("newPassword");
        request.setUserType(UserType.ADMINISTRATOR);

        when(appUserRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> updateAppUserUseCase.updateAppUser(userId, request));
    }
}
