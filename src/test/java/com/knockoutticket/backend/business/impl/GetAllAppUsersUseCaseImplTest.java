package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.responses.GetAppUserResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@SpringBootTest
public class GetAllAppUsersUseCaseImplTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private GetAllAppUsersUseCaseImpl getAllAppUsersUseCase;

    @Test
    void getAllAppUsers_ShouldReturnListOfUsers_WhenUsersExist() {
        // Arrange
        List<AppUserEntity> users = Collections.singletonList(new AppUserEntity());
        when(appUserRepository.findAll()).thenReturn(users);

        // Act
        List<GetAppUserResponse> response = getAllAppUsersUseCase.getAllAppUsers();

        // Assert
        assertEquals(users.size(), response.size());
    }
}
