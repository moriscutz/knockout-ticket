package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.CreateAppUserUseCase;
import com.knockoutticket.backend.domain.requests.CreateAppUserRequest;
import com.knockoutticket.backend.domain.responses.CreateAppUserResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAppUserUseCaseImplTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private CreateAppUserUseCaseImpl createAppUserUseCase;

    @Test
    void createAppUser_Success() {
        // Arrange
        CreateAppUserRequest request = new CreateAppUserRequest();
        request.setUsername("testUser");
        request.setPassword("password123"); // Provide a non-blank password
        request.setEmail("test@example.com");

        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        AppUserRepository appUserRepository = mock(AppUserRepository.class);
        when(appUserRepository.existsByUsername(anyString())).thenReturn(false);
        when(appUserRepository.existsByEmail(anyString())).thenReturn(false);
        when(appUserRepository.save(any(AppUserEntity.class))).thenAnswer(invocation -> {
            AppUserEntity entity = invocation.getArgument(0);
            entity.setId(1L); // Set the ID for the saved entity
            return entity;
        });

        CreateAppUserUseCase createAppUserUseCase = new CreateAppUserUseCaseImpl(passwordEncoder, appUserRepository);

        // Act
        CreateAppUserResponse response = createAppUserUseCase.createAppUser(request);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void createAppUser_UsernameExists() {
        // Arrange
        CreateAppUserRequest request = new CreateAppUserRequest("existingUser", "test@example.com", "password");

        when(appUserRepository.existsByUsername(request.getUsername())).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> createAppUserUseCase.createAppUser(request));
        verify(appUserRepository, never()).save(any(AppUserEntity.class));
    }

    @Test
    void createAppUser_EmailExists() {
        // Arrange
        CreateAppUserRequest request = new CreateAppUserRequest();
        request.setUsername("testUser");
        request.setPassword("password123"); // Provide a non-blank password
        request.setEmail("test@example.com");

        when(appUserRepository.existsByUsername(request.getUsername())).thenReturn(false);
        when(appUserRepository.existsByEmail(request.getEmail())).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> createAppUserUseCase.createAppUser(request));
        verify(appUserRepository, never()).save(any(AppUserEntity.class));
    }
}
