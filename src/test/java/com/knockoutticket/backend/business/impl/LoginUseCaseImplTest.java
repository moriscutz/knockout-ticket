package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.InvalidCredentialsException;
import com.knockoutticket.backend.config.security.token.AccessToken;
import com.knockoutticket.backend.config.security.token.AccessTokenEncoder;
import com.knockoutticket.backend.config.security.token.impl.AccessTokenImpl;
import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.domain.requests.LoginRequest;
import com.knockoutticket.backend.domain.responses.LoginResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.UserTypeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseImplTest {

    @Mock
    private AppUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @Test
    void login_ValidCredentials_ReturnsAccessToken() {
        // Arrange
        String username = "testuser";
        String password = "testpassword";
        LoginRequest loginRequest = new LoginRequest(username, password);

        AppUserEntity user = new AppUserEntity();
        user.setUsername(username);
        user.setPassword(password);

        UserTypeEntity userTypeEntity = new UserTypeEntity();
        userTypeEntity.setType(UserType.NORMAL_USER);
        user.setUserRoles(Collections.singleton(userTypeEntity));

        when(userRepository.findByUsername(username)).thenReturn(user);
        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);

        // Mocking access token generation
        AccessToken accessToken = new AccessTokenImpl(username, user.getId(), Collections.singleton(UserType.NORMAL_USER.toString()));
        when(accessTokenEncoder.encode(accessToken)).thenReturn("mocked_access_token");

        // Act
        LoginResponse response = loginUseCase.login(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals("mocked_access_token", response.getAccessToken());
        verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, times(1)).matches(password, user.getPassword());
        verify(accessTokenEncoder, times(1)).encode(accessToken);
    }

    @Test
    void login_InvalidCredentials_ThrowsInvalidCredentialsException() {
        // Arrange
        String username = "testuser";
        String password = "testpassword";
        LoginRequest loginRequest = new LoginRequest(username, password);

        when(userRepository.findByUsername(username)).thenReturn(null);

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(loginRequest));
        verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, never()).matches(anyString(), anyString());
        verify(accessTokenEncoder, never()).encode(any());
    }
}
