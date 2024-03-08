package com.knockoutticket.backend.service;

import com.knockoutticket.backend.domain.user.EventOrganizer;
import com.knockoutticket.backend.repository.EventOrganizerRepository;
import com.knockoutticket.backend.service.impl.EventOrganizerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventOrganizerServiceTest {

    @Mock
    private EventOrganizerRepository eventOrganizerRepository;

    @InjectMocks
    private EventOrganizerServiceImpl eventOrganizerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveEventOrganizer() {
        EventOrganizer eventOrganizer = new EventOrganizer();
        eventOrganizer.setOrganizationName("Test Organization");
        when(eventOrganizerRepository.save(eventOrganizer)).thenReturn(eventOrganizer);

        EventOrganizer savedEventOrganizer = eventOrganizerService.saveEventOrganizer(eventOrganizer);

        assertNotNull(savedEventOrganizer);
        assertEquals("Test Organization", savedEventOrganizer.getOrganizationName());
    }

    @Test
    void getEventOrganizerById() {
        long id = 1L;
        EventOrganizer eventOrganizer = new EventOrganizer();
        eventOrganizer.setOrganizationName("Test Organization");
        when(eventOrganizerRepository.findById(id)).thenReturn(Optional.of(eventOrganizer));

        Optional<EventOrganizer> optionalEventOrganizer = eventOrganizerService.getEventOrganizerById(id);

        assertTrue(optionalEventOrganizer.isPresent());
        assertEquals("Test Organization", optionalEventOrganizer.get().getOrganizationName());
    }

    @Test
    void getAllEventOrganizers() {
        List<EventOrganizer> eventOrganizerList = new ArrayList<>();
        eventOrganizerList.add(new EventOrganizer());
        eventOrganizerList.add(new EventOrganizer());
        when(eventOrganizerRepository.findAll()).thenReturn(eventOrganizerList);

        List<EventOrganizer> fetchedEventOrganizers = eventOrganizerService.getAllEventOrganizers();

        assertNotNull(fetchedEventOrganizers);
        assertEquals(2, fetchedEventOrganizers.size());
    }

    @Test
    void updateEventOrganizer() {
        long id = 1L;
        EventOrganizer eventOrganizer = new EventOrganizer();
        eventOrganizer.setOrganizationName("Test Organization");
        when(eventOrganizerRepository.findById(id)).thenReturn(Optional.of(eventOrganizer));

        EventOrganizer updatedEventOrganizer = new EventOrganizer();
        updatedEventOrganizer.setOrganizationName("Updated Organization");
        when(eventOrganizerRepository.save(eventOrganizer)).thenReturn(updatedEventOrganizer);

        EventOrganizer result = eventOrganizerService.updateEventOrganizer(id, updatedEventOrganizer);

        assertNotNull(result);
        assertEquals("Updated Organization", result.getOrganizationName());
    }

    @Test
    void deleteEventOrganizer() {
        long id = 1L;
        doNothing().when(eventOrganizerRepository).deleteById(id);

        eventOrganizerService.deleteEventOrganizer(id);

        verify(eventOrganizerRepository, times(1)).deleteById(id);
    }
}
