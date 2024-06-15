package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.*;
import com.knockoutticket.backend.config.security.token.AccessTokenDecoder;
import com.knockoutticket.backend.domain.models.Event;
import com.knockoutticket.backend.domain.requests.AddEventToFightNightRequest;
import com.knockoutticket.backend.domain.requests.CreateEventFightNightRequest;
import com.knockoutticket.backend.domain.requests.UpdateEventFightNightRequest;
import com.knockoutticket.backend.domain.responses.AddEventToFightNightResponse;
import com.knockoutticket.backend.domain.responses.CreateEventFightNightResponse;
import com.knockoutticket.backend.domain.responses.GetEventFightNightResponse;
import com.knockoutticket.backend.domain.responses.UpdateEventFightNightResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventFightNightController.class)
class EventFightNightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetEventFightNightUseCase getEventFightNightUseCase;

    @MockBean
    private CreateEventFightNightUseCase createEventFightNightUseCase;

    @MockBean
    private GetAllEventFightNightsUseCase getAllEventFightNightsUseCase;

    @MockBean
    private AddEventToFightNightUseCase addEventToFightNightUseCase;

    @MockBean
    private UpdateEventFightNightUseCase updateEventFightNightUseCase;

    @MockBean
    private DeleteEventFightNightUseCase deleteEventFightNightUseCase;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public AccessTokenDecoder accessTokenDecoder() {
            return Mockito.mock(AccessTokenDecoder.class);
        }
    }

    private GetEventFightNightResponse getEventFightNightResponse;
    private CreateEventFightNightResponse createEventFightNightResponse;
    private AddEventToFightNightResponse addEventToFightNightResponse;
    private UpdateEventFightNightResponse updateEventFightNightResponse;

    @BeforeEach
    void setUp() {
        getEventFightNightResponse = new GetEventFightNightResponse(
                1L, "Event Title", LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2), "Event Place", List.of(new Event()));
        createEventFightNightResponse = new CreateEventFightNightResponse(1L);
        addEventToFightNightResponse = new AddEventToFightNightResponse(1L);
        updateEventFightNightResponse = new UpdateEventFightNightResponse(1L, "Updated Title", LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2), "Updated Place");
    }

    private RequestPostProcessor csrfToken() {
        return request -> {
            HttpSessionCsrfTokenRepository repo = new HttpSessionCsrfTokenRepository();
            CsrfToken token = repo.generateToken(request);
            MockHttpServletResponse response = new MockHttpServletResponse();
            repo.saveToken(token, request, response);
            request.setAttribute(CsrfToken.class.getName(), token);
            request.setAttribute(token.getParameterName(), token);
            return request;
        };
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR", "EVENT_ORGANIZER"})
    void testCreateEventFightNight() throws Exception {
        Mockito.when(createEventFightNightUseCase.createEventFightNight(any(CreateEventFightNightRequest.class)))
                .thenReturn(createEventFightNightResponse);

        mockMvc.perform(post("/eventFightNights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Event Title\", \"place\": \"Event Place\", \"date\": \"2024-01-01\", \"startTime\": \"10:00:00\", \"endTime\": \"12:00:00\"}")
                        .with(csrfToken()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR", "EVENT_ORGANIZER", "NORMAL_USER"})
    void testGetEventFightNight() throws Exception {
        Mockito.when(getEventFightNightUseCase.getEventFightNight(anyLong()))
                .thenReturn(getEventFightNightResponse);

        mockMvc.perform(get("/eventFightNights/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Event Title"))
                .andExpect(jsonPath("$.place").value("Event Place"));
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR", "EVENT_ORGANIZER", "NORMAL_USER"})
    void testGetAllEventFightNights() throws Exception {
        List<GetEventFightNightResponse> allEvents = Arrays.asList(getEventFightNightResponse);
        Mockito.when(getAllEventFightNightsUseCase.getAllEventFightNights()).thenReturn(allEvents);

        mockMvc.perform(get("/eventFightNights"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Event Title"))
                .andExpect(jsonPath("$[0].place").value("Event Place"));
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR", "EVENT_ORGANIZER"})
    void testAddEventToFightNight() throws Exception {
        Mockito.when(addEventToFightNightUseCase.addEventToFightNight(any(AddEventToFightNightRequest.class)))
                .thenReturn(addEventToFightNightResponse);

        mockMvc.perform(post("/eventFightNights/1/addEvent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"eventId\": 1, \"fightNightId\": 1}")
                        .with(csrfToken()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR", "EVENT_ORGANIZER"})
    void testUpdateEventFightNight() throws Exception {
        Mockito.when(updateEventFightNightUseCase.updateEventFightNight(anyLong(), any(UpdateEventFightNightRequest.class)))
                .thenReturn(updateEventFightNightResponse);

        mockMvc.perform(put("/eventFightNights/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Updated Title\", \"place\": \"Updated Place\", \"date\": \"2024-01-01\", \"startTime\": \"10:00:00\", \"endTime\": \"12:00:00\"}")
                        .with(csrfToken()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR", "EVENT_ORGANIZER"})
    void testDeleteEventFightNight() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/eventFightNights/1").with(csrfToken()))
                .andExpect(status().isNoContent());
    }
}
