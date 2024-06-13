package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.*;
import com.knockoutticket.backend.domain.models.AppUser;
import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.domain.requests.CreateAppUserRequest;
import com.knockoutticket.backend.domain.requests.UpdateAppUserRequest;
import com.knockoutticket.backend.domain.responses.CreateAppUserResponse;
import com.knockoutticket.backend.domain.responses.GetAppUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AppUserControllerTest {

    @Mock
    private CreateAppUserUseCase createAppUserUseCase;

    @Mock
    private GetAppUserUseCase getAppUserUseCase;

    @Mock
    private UpdateAppUserUseCase updateAppUserUseCase;

    @Mock
    private DeleteAppUserUseCase deleteAppUserUseCase;

    @Mock
    private GetAllAppUsersUseCase getAllAppUsersUseCase;

    @InjectMocks
    private AppUserController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testCreateAppUser() {
        //arange
        CreateAppUserRequest request = CreateAppUserRequest.builder()
                .username("testuser")
                .email("testuser@gmail.com")
                .password("password123")
                .build();

        CreateAppUserResponse expectedResponse = new CreateAppUserResponse(1L);

        when(createAppUserUseCase.createAppUser(any(CreateAppUserRequest.class))).thenReturn(expectedResponse);

        //act
        ResponseEntity<CreateAppUserResponse> response = controller.createAppUser(request);


        //assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(createAppUserUseCase).createAppUser(any(CreateAppUserRequest.class));
    }


    @Test
    void testGetAppUser() {
        Long userId = 1L;
        AppUser user = AppUser.builder()
                .id(userId)
                .username("sampleuser")
                .email("sampleuser@gmail.com")
                .password("password")
                .userType(Collections.singleton(UserType.NORMAL_USER))
                .build();
        GetAppUserResponse expectedResponse = new GetAppUserResponse(user);

        when(getAppUserUseCase.getAppUser(userId)).thenReturn(expectedResponse);

        ResponseEntity<GetAppUserResponse> response = controller.getAppUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testUpdateAppUser() {
        Long userId = 1L;
        UpdateAppUserRequest request = new UpdateAppUserRequest();
        request.setId(userId);
        request.setUsername("newUsername");
        request.setEmail("newemail@example.com");
        request.setPassword("newPassword");
        request.setUserType(UserType.NORMAL_USER);

        doNothing().when(updateAppUserUseCase).updateAppUser(eq(userId), any(UpdateAppUserRequest.class));

        ResponseEntity<Void> response = controller.updateAppUser(userId, request);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(updateAppUserUseCase).updateAppUser(eq(userId), any(UpdateAppUserRequest.class));
    }


    @Test
    void testDeleteAppUser() {
        Long userId = 1L;
        doNothing().when(deleteAppUserUseCase).deleteAppUser(userId);

        ResponseEntity<Void> response = controller.deleteAppUser(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deleteAppUserUseCase).deleteAppUser(userId);
    }


    @Test
    void testGetAllAppUsers() {
        AppUser user1 = AppUser.builder()
                .id(1L)
                .username("userOne")
                .email("userOne@example.com")
                .password("hashedPassword1")
                .userType(Collections.singleton(UserType.NORMAL_USER))
                .build();

        AppUser user2 = AppUser.builder()
                .id(2L)
                .username("userTwo")
                .email("userTwo@example.com")
                .password("hashedPassword2")
                .userType(Collections.singleton(UserType.ADMINISTRATOR))
                .build();

        List<GetAppUserResponse> expectedResponses = Arrays.asList(
                new GetAppUserResponse(user1),
                new GetAppUserResponse(user2)
        );
        when(getAllAppUsersUseCase.getAllAppUsers()).thenReturn(expectedResponses);

        ResponseEntity<List<GetAppUserResponse>> response = controller.getAllAppUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponses, response.getBody());
    }

}
