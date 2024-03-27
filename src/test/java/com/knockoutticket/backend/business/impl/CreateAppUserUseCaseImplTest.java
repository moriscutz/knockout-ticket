package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.domain.requests.CreateAppUserRequest;
import com.knockoutticket.backend.domain.responses.CreateAppUserResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class CreateAppUserUseCaseImplTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private CreateAppUserUseCaseImpl createAppUserUseCase;

    @Test
    void testCreateAppUser_Success() {
        CreateAppUserRequest request = new CreateAppUserRequest("username", "email@example.com", "password");
        AppUserEntity savedEntity = AppUserEntity.builder()
                .id(1L)
                .username("username")
                .email("email@example.com")
                .password("password")
                .userType(UserType.NORMAL_USER)
                .build();

        when(appUserRepository.existsByUsername("username")).thenReturn(false);
        when(appUserRepository.existsByEmail("email@example.com")).thenReturn(false);
        when(appUserRepository.save(ArgumentMatchers.any(AppUserEntity.class))).thenReturn(savedEntity);

        CreateAppUserResponse response = createAppUserUseCase.createAppUser(request);

        assertEquals(1L, response.getId());
    }

    @Test
    void testCreateAppUser_UsernameExists() {

        CreateAppUserRequest request = new CreateAppUserRequest("existingUsername", "newemail@example.com", "password");

        when(appUserRepository.existsByUsername("existingUsername")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> createAppUserUseCase.createAppUser(request));
    }

    @Test
    void testCreateAppUser_EmailExists() {

        CreateAppUserRequest request = new CreateAppUserRequest("newUsername", "existingemail@example.com", "password");

        when(appUserRepository.existsByUsername("newUsername")).thenReturn(false);
        when(appUserRepository.existsByEmail("existingemail@example.com")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> createAppUserUseCase.createAppUser(request));
    }

}
