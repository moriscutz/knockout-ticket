package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.*;
import com.knockoutticket.backend.config.security.token.AccessTokenDecoder;
import com.knockoutticket.backend.domain.models.AppUser;
import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.domain.requests.CreateAppUserRequest;
import com.knockoutticket.backend.domain.requests.UpdateAppUserRequest;
import com.knockoutticket.backend.domain.responses.CreateAppUserResponse;
import com.knockoutticket.backend.domain.responses.GetAppUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppUserController.class)
class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateAppUserUseCase createAppUserUseCase;

    @MockBean
    private GetAppUserUseCase getAppUserUseCase;

    @MockBean
    private UpdateAppUserUseCase updateAppUserUseCase;

    @MockBean
    private DeleteAppUserUseCase deleteAppUserUseCase;

    @MockBean
    private GetAllAppUsersUseCase getAllAppUsersUseCase;

    @MockBean
    private AccessTokenDecoder accessTokenDecoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR"})
    void shouldCreateAppUser() throws Exception {
        CreateAppUserRequest request = CreateAppUserRequest.builder()
                .username("testuser")
                .email("testuser@gmail.com")
                .password("password123")
                .build();

        CreateAppUserResponse response = new CreateAppUserResponse(1L);

        when(createAppUserUseCase.createAppUser(any(CreateAppUserRequest.class))).thenReturn(response);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\",\"email\":\"testuser@gmail.com\",\"password\":\"password123\"}")
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @WithMockUser(roles = {"NORMAL_USER", "ADMINISTRATOR", "EVENT_ORGANIZER"})
    void shouldGetAppUserById() throws Exception {
        Long userId = 1L;
        AppUser user = AppUser.builder()
                .id(userId)
                .username("sampleuser")
                .email("sampleuser@gmail.com")
                .password("password")
                .userType(Collections.singleton(UserType.NORMAL_USER))
                .build();
        GetAppUserResponse response = new GetAppUserResponse(user);

        when(getAppUserUseCase.getAppUser(userId)).thenReturn(response);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id").value(1L))
                .andExpect(jsonPath("$.user.username").value("sampleuser"));
    }

    @Test
    @WithMockUser(roles = {"NORMAL_USER", "ADMINISTRATOR", "EVENT_ORGANIZER"})
    void shouldUpdateAppUser() throws Exception {
        Long userId = 1L;
        UpdateAppUserRequest request = new UpdateAppUserRequest();
        request.setId(userId);
        request.setUsername("newUsername");
        request.setEmail("newemail@example.com");
        request.setPassword("newPassword");
        request.setUserType(UserType.NORMAL_USER);

        doNothing().when(updateAppUserUseCase).updateAppUser(anyLong(), any(UpdateAppUserRequest.class));

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"username\":\"newUsername\",\"email\":\"newemail@example.com\",\"password\":\"newPassword\",\"userType\":\"NORMAL_USER\"}")
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR"})
    void shouldDeleteAppUser() throws Exception {
        doNothing().when(deleteAppUserUseCase).deleteAppUser(anyLong());

        mockMvc.perform(delete("/users/1").with(csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = {"NORMAL_USER", "ADMINISTRATOR", "EVENT_ORGANIZER"})
    void shouldGetAllAppUsers() throws Exception {
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

        List<GetAppUserResponse> responses = Arrays.asList(
                new GetAppUserResponse(user1),
                new GetAppUserResponse(user2)
        );

        when(getAllAppUsersUseCase.getAllAppUsers()).thenReturn(responses);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].user.id").value(1L))
                .andExpect(jsonPath("$[0].user.username").value("userOne"))
                .andExpect(jsonPath("$[1].user.id").value(2L))
                .andExpect(jsonPath("$[1].user.username").value("userTwo"));
    }

    private MockHttpServletRequestBuilder withCsrf(MockHttpServletRequestBuilder builder) {
        return builder.with(csrf());
    }
}
