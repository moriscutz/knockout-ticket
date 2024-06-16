package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.LoginUseCase;
import com.knockoutticket.backend.domain.requests.LoginRequest;
import com.knockoutticket.backend.domain.responses.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @Mock
    private LoginUseCase loginUseCase;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldLogin() {
        LoginRequest request = new LoginRequest("username", "password");
        LoginResponse response = LoginResponse.builder()
                .accessToken("dummyAccessToken")
                .build();

        when(loginUseCase.login(request)).thenReturn(response);

        ResponseEntity<LoginResponse> result = loginController.login(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(loginUseCase).login(request);
    }
}
