package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.DeleteArchivedEventsForOrganizerUseCase;
import com.knockoutticket.backend.business.DeleteBookingsForUserUseCase;
import com.knockoutticket.backend.business.DeleteEventsForOrganizerUseCase;
import com.knockoutticket.backend.business.exception.UserNotFoundException;
import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.UserTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteAppUserUseCaseImplTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private DeleteBookingsForUserUseCase deleteBookingsForUserUseCase;

    @Mock
    private DeleteArchivedEventsForOrganizerUseCase deleteArchivedEventsForOrganizerUseCase;

    @Mock
    private DeleteEventsForOrganizerUseCase deleteEventsForOrganizerUseCase;

    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private DeleteAppUserUseCaseImpl deleteAppUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteAppUser_NormalUser() {
        Long userId = 1L;
        AppUserEntity user = createUserWithRole(UserType.NORMAL_USER);

        when(appUserRepository.findById(userId)).thenReturn(Optional.of(user));

        deleteAppUserUseCase.deleteAppUser(userId);

        verify(deleteBookingsForUserUseCase, times(1)).deleteBookingsForUser(userId);
        verify(appUserRepository, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteAppUser_EventOrganizer() {
        Long userId = 2L;
        AppUserEntity user = createUserWithRole(UserType.EVENT_ORGANIZER);

        when(appUserRepository.findById(userId)).thenReturn(Optional.of(user));

        deleteAppUserUseCase.deleteAppUser(userId);

        verify(deleteArchivedEventsForOrganizerUseCase, times(1)).deleteArchivedEventsForOrganizer(userId);
        verify(deleteEventsForOrganizerUseCase, times(1)).deleteEventsForOrganizer(userId);
        verify(appUserRepository, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteAppUser_UserNotFound() {
        Long userId = 3L;

        when(appUserRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> deleteAppUserUseCase.deleteAppUser(userId));
    }

    private AppUserEntity createUserWithRole(UserType role) {
        Set<UserTypeEntity> roles = new HashSet<>();
        roles.add(UserTypeEntity.builder().type(role).build());

        return AppUserEntity.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .userRoles(roles)
                .build();
    }
}
