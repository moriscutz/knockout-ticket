package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.AppUser;
import com.knockoutticket.backend.domain.responses.GetAppUserResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllAppUsersUseCaseImplTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private GetAllAppUsersUseCaseImpl getAllAppUsersUseCase;

    @Test
    void getAllAppUsers_ShouldReturnAllUsers() {
        // Arrange
        AppUserEntity userEntity1 = new AppUserEntity();
        userEntity1.setId(1L);
        userEntity1.setUsername("user1");

        AppUserEntity userEntity2 = new AppUserEntity();
        userEntity2.setId(2L);
        userEntity2.setUsername("user2");

        AppUser user1 = new AppUser();
        user1.setId(1L);
        user1.setUsername("user1");

        AppUser user2 = new AppUser();
        user2.setId(2L);
        user2.setUsername("user2");

        when(appUserRepository.findAll()).thenReturn(Arrays.asList(userEntity1, userEntity2));
        when(userConverter.toModel(userEntity1)).thenReturn(user1);
        when(userConverter.toModel(userEntity2)).thenReturn(user2);

        // Act
        List<GetAppUserResponse> responses = getAllAppUsersUseCase.getAllAppUsers();

        // Assert
        assertEquals(2, responses.size());
        assertEquals("user1", responses.get(0).getUser().getUsername());
        assertEquals("user2", responses.get(1).getUser().getUsername());

        verify(appUserRepository, times(1)).findAll();
        verify(userConverter, times(1)).toModel(userEntity1);
        verify(userConverter, times(1)).toModel(userEntity2);
    }
}
