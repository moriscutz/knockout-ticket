package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.persistence.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteAppUserUseCaseImplTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private DeleteAppUserUseCaseImpl deleteAppUserUseCase;

    @Test
    void deleteAppUser() {
        // Arrange
        long userId = 1L;

        // Act
        deleteAppUserUseCase.deleteAppUser(userId);

        // Assert
        verify(appUserRepository, times(1)).deleteById(userId);
    }
}