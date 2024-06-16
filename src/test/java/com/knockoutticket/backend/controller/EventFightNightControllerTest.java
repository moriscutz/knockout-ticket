package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.*;
import com.knockoutticket.backend.config.security.token.AccessTokenDecoder;
import com.knockoutticket.backend.domain.requests.AddEventToFightNightRequest;
import com.knockoutticket.backend.domain.requests.CreateEventFightNightRequest;
import com.knockoutticket.backend.domain.requests.UpdateEventFightNightRequest;
import com.knockoutticket.backend.domain.responses.AddEventToFightNightResponse;
import com.knockoutticket.backend.domain.responses.CreateEventFightNightResponse;
import com.knockoutticket.backend.domain.responses.GetEventFightNightResponse;
import com.knockoutticket.backend.domain.responses.UpdateEventFightNightResponse;
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

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
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

    @MockBean
    private AccessTokenDecoder accessTokenDecoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR", "EVENT_ORGANIZER"})
    void testCreateEventFightNight() throws Exception {
        CreateEventFightNightRequest request = new CreateEventFightNightRequest();
        CreateEventFightNightResponse response = new CreateEventFightNightResponse(1L);

        when(createEventFightNightUseCase.createEventFightNight(any(CreateEventFightNightRequest.class))).thenReturn(response);

        mockMvc.perform(post("/eventFightNights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Event\",\"date\":\"2023-12-31\",\"startTime\":\"18:00\",\"endTime\":\"23:00\",\"place\":\"Test Venue\"}")
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR", "EVENT_ORGANIZER", "NORMAL_USER"})
    void testGetEventFightNight() throws Exception {
        GetEventFightNightResponse response = new GetEventFightNightResponse(1L, "Test Event", null, null, null, "Test Venue", Collections.emptyList());

        when(getEventFightNightUseCase.getEventFightNight(anyLong())).thenReturn(response);

        mockMvc.perform(get("/eventFightNights/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Event"));
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR", "EVENT_ORGANIZER", "NORMAL_USER"})
    void testGetAllEventFightNights() throws Exception {
        GetEventFightNightResponse response = new GetEventFightNightResponse(1L, "Test Event", null, null, null, "Test Venue", Collections.emptyList());
        List<GetEventFightNightResponse> responses = List.of(response);

        when(getAllEventFightNightsUseCase.getAllEventFightNights()).thenReturn(responses);

        mockMvc.perform(get("/eventFightNights"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Test Event"));
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR", "EVENT_ORGANIZER"})
    void testAddEventToFightNight() throws Exception {
        AddEventToFightNightRequest request = new AddEventToFightNightRequest();
        AddEventToFightNightResponse response = new AddEventToFightNightResponse(1L);

        when(addEventToFightNightUseCase.addEventToFightNight(any(AddEventToFightNightRequest.class))).thenReturn(response);

        mockMvc.perform(post("/eventFightNights/1/addEvent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"eventFightNightId\":1,\"boxerId1\":1,\"boxerId2\":2,\"organizerId\":1,\"eventDate\":\"2023-12-31T18:00\",\"eventStatus\":\"Scheduled\",\"eventPlace\":\"Test Venue\"}")
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventId").value(1L));
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR", "EVENT_ORGANIZER"})
    void testUpdateEventFightNight() throws Exception {
        UpdateEventFightNightRequest request = new UpdateEventFightNightRequest();
        UpdateEventFightNightResponse response = new UpdateEventFightNightResponse(1L, "Updated Event", null, null, null, "Updated Venue");

        when(updateEventFightNightUseCase.updateEventFightNight(anyLong(), any(UpdateEventFightNightRequest.class))).thenReturn(response);

        mockMvc.perform(put("/eventFightNights/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Event\",\"date\":\"2023-12-31\",\"startTime\":\"18:00\",\"endTime\":\"23:00\",\"place\":\"Updated Venue\"}")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Updated Event"));
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR", "EVENT_ORGANIZER"})
    void testDeleteEventFightNight() throws Exception {
        mockMvc.perform(delete("/eventFightNights/1").with(csrf()))
                .andExpect(status().isNoContent());
    }

    // CSRF setup method
    private MockHttpServletRequestBuilder withCsrf(MockHttpServletRequestBuilder builder) {
        return builder.with(csrf());
    }
}
