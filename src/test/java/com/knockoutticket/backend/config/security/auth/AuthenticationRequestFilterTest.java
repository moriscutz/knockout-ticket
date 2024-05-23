package com.knockoutticket.backend.config.security.auth;

import com.knockoutticket.backend.config.security.token.AccessToken;
import com.knockoutticket.backend.config.security.token.AccessTokenDecoder;
import com.knockoutticket.backend.config.security.token.exception.InvalidAccessTokenException;
import com.knockoutticket.backend.config.security.token.impl.AccessTokenImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthenticationRequestFilterTest {

    private MockMvc mockMvc;

    @Mock
    private AccessTokenDecoder accessTokenDecoder;

    @BeforeEach
    public void setup() {
        AuthenticationRequestFilter filter = new AuthenticationRequestFilter();
        ReflectionTestUtils.setField(filter, "accessTokenDecoder", accessTokenDecoder);

        //Test controller
        TestController testController = new TestController();

        mockMvc = MockMvcBuilders
                .standaloneSetup(testController)
                .addFilters(filter)
                .build();
    }

    @AfterEach
    public void cleanup() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void whenNoAuthorizationHeader_thenContinueFilterChain() throws Exception {
        mockMvc.perform(get("/test-endpoint"))
                .andExpect(status().isOk());
    }

    @Test
    void whenValidToken_thenAuthenticateAndContinueFilterChain() throws Exception {
        AccessToken validAccessToken = new AccessTokenImpl("username", 123L, Set.of("USER"));
        when(accessTokenDecoder.decode(anyString())).thenReturn(validAccessToken);

        mockMvc.perform(get("/test-endpoint")
                        .header("Authorization", "Bearer valid.token"))
                .andExpect(status().isOk());

        // Verify that the SecurityContext is set up
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void whenInvalidToken_thenSendAuthenticationError() throws Exception {
        when(accessTokenDecoder.decode(anyString())).thenThrow(InvalidAccessTokenException.class);

        mockMvc.perform(get("/test-endpoint")
                        .header("Authorization", "Bearer invalid.token"))
                .andExpect(status().isUnauthorized());
    }
}

@RestController
class TestController {

    @GetMapping("/test-endpoint")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Success");
    }
}
