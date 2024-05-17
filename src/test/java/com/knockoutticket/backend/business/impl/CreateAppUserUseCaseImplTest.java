package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.*;
import com.knockoutticket.backend.domain.requests.CreateAppUserRequest;
import com.knockoutticket.backend.domain.responses.CreateAppUserResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class CreateAppUserUseCaseImplTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private CreateAppUserUseCaseImpl createAppUserUseCase;

    @BeforeEach
    void setup() {
        lenient().when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        lenient().when(appUserRepository.existsByUsername(anyString())).thenReturn(false);
        lenient().when(appUserRepository.existsByEmail(anyString())).thenReturn(false);
        lenient().when(appUserRepository.save(any(AppUserEntity.class))).thenAnswer(i -> {
            AppUserEntity user = i.getArgument(0);
            user.setId(1L);
            return user;
        });
    }

    @Test
    void createAppUser_SuccessfulCreation_ReturnsResponse() {
        // Arrange
        CreateAppUserRequest request = new CreateAppUserRequest("newuser", "newuser@example.com", "password");
        when(appUserRepository.existsByUsername(request.getUsername())).thenReturn(false);
        when(appUserRepository.existsByEmail(request.getEmail())).thenReturn(false);

        // Act
        CreateAppUserResponse response = createAppUserUseCase.createAppUser(request);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getId());
        verify(appUserRepository, times(1)).save(any(AppUserEntity.class));
    }

    @Test
    void createAppUser_UsernameExists_ThrowsUsernameAlreadyExistsException() {
        // Arrange
        CreateAppUserRequest request = new CreateAppUserRequest("existingUser", "user@example.com", "password");
        when(appUserRepository.existsByUsername(request.getUsername())).thenReturn(true);

        // Act & Assert
        assertThrows(UsernameAlreadyExistsException.class, () -> createAppUserUseCase.createAppUser(request));
    }

    @Test
    void createAppUser_EmailExists_ThrowsEmailAlreadyExistsException() {
        // Arrange
        CreateAppUserRequest request = new CreateAppUserRequest("user", "existing@example.com", "password");
        when(appUserRepository.existsByEmail(request.getEmail())).thenReturn(true);

        // Act & Assert
        assertThrows(EmailAlreadyExistsException.class, () -> createAppUserUseCase.createAppUser(request));
    }

    @Test
    void createAppUser_BlankEmail_ThrowsBlankEmailException() {
        // Arrange
        CreateAppUserRequest request = new CreateAppUserRequest("user", "  ", "password");
        when(appUserRepository.existsByUsername(request.getUsername())).thenReturn(false);
        when(appUserRepository.existsByEmail(request.getEmail())).thenReturn(false);

        // Act & Assert
        assertThrows(BlankEmailException.class, () -> createAppUserUseCase.createAppUser(request));
        verify(appUserRepository, never()).save(any(AppUserEntity.class));
    }

    @Test
    void createAppUser_BlankUsername_ThrowsBlankUsernameException() {
        // Arrange
        CreateAppUserRequest request = new CreateAppUserRequest("  ", "user@example.com", "password");
        when(appUserRepository.existsByUsername(request.getUsername())).thenReturn(false);
        when(appUserRepository.existsByEmail(request.getEmail())).thenReturn(false);

        // Act & Assert
        assertThrows(BlankUsernameException.class, () -> createAppUserUseCase.createAppUser(request));
        verify(appUserRepository, never()).save(any(AppUserEntity.class));
    }

    @Test
    void createAppUser_BlankPassword_ThrowsBlankPasswordException() {
        // Arrange
        CreateAppUserRequest request = new CreateAppUserRequest("user", "user@example.com", "  ");
        when(appUserRepository.existsByUsername(request.getUsername())).thenReturn(false);
        when(appUserRepository.existsByEmail(request.getEmail())).thenReturn(false);

        // Act & Assert
        assertThrows(BlankPasswordException.class, () -> createAppUserUseCase.createAppUser(request));
        verify(appUserRepository, never()).save(any(AppUserEntity.class));
    }
}
