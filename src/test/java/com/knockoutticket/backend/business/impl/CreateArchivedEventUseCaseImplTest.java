package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.DeleteEventUseCase;
import com.knockoutticket.backend.business.exception.BoxerNotFoundException;
import com.knockoutticket.backend.business.exception.OrganizerNotFoundException;
import com.knockoutticket.backend.domain.requests.CreateArchivedEventRequest;
import com.knockoutticket.backend.domain.responses.CreateArchivedEventResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.ArchivedEventRepository;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.ArchivedEventEntity;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateArchivedEventUseCaseImplTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private BoxerRepository boxerRepository;

    @Mock
    private ArchivedEventRepository archivedEventRepository;

    @Mock
    private DeleteEventUseCase deleteEventUseCase;

    @InjectMocks
    private CreateArchivedEventUseCaseImpl createArchivedEventUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateArchivedEvent() {
        // Arrange
        Long organizerId = 1L;
        Long winnerId = 2L;
        Long loserId = 3L;
        Long eventId = 4L;
        LocalDateTime date = LocalDateTime.now();
        String place = "Test Venue";

        CreateArchivedEventRequest request = CreateArchivedEventRequest.builder()
                .organizerId(organizerId)
                .winnerId(winnerId)
                .loserId(loserId)
                .eventId(eventId)
                .date(date)
                .place(place)
                .build();

        AppUserEntity organizer = new AppUserEntity();
        organizer.setId(organizerId);
        BoxerEntity winner = new BoxerEntity();
        winner.setId(winnerId);
        BoxerEntity loser = new BoxerEntity();
        loser.setId(loserId);

        ArchivedEventEntity archivedEvent = ArchivedEventEntity.builder()
                .id(1L)
                .organizer(organizer)
                .date(date)
                .winner(winner)
                .loser(loser)
                .place(place)
                .build();

        when(appUserRepository.findById(organizerId)).thenReturn(java.util.Optional.of(organizer));
        when(boxerRepository.findById(winnerId)).thenReturn(java.util.Optional.of(winner));
        when(boxerRepository.findById(loserId)).thenReturn(java.util.Optional.of(loser));
        when(archivedEventRepository.save(any(ArchivedEventEntity.class))).thenReturn(archivedEvent);

        // Act
        CreateArchivedEventResponse response = createArchivedEventUseCase.createArchivedEvent(request);

        // Assert
        assertEquals(1L, response.getId());
        verify(deleteEventUseCase).deleteEvent(eventId);
    }

    @Test
    void shouldThrowOrganizerNotFoundException() {
        // Arrange
        Long organizerId = 1L;
        Long winnerId = 2L;
        Long loserId = 3L;
        Long eventId = 4L;
        LocalDateTime date = LocalDateTime.now();
        String place = "Test Venue";

        CreateArchivedEventRequest request = CreateArchivedEventRequest.builder()
                .organizerId(organizerId)
                .winnerId(winnerId)
                .loserId(loserId)
                .eventId(eventId)
                .date(date)
                .place(place)
                .build();

        when(appUserRepository.findById(organizerId)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(OrganizerNotFoundException.class, () -> createArchivedEventUseCase.createArchivedEvent(request));
    }

    @Test
    void shouldThrowBoxerNotFoundExceptionForWinner() {
        // Arrange
        Long organizerId = 1L;
        Long winnerId = 2L;
        Long loserId = 3L;
        Long eventId = 4L;
        LocalDateTime date = LocalDateTime.now();
        String place = "Test Venue";

        CreateArchivedEventRequest request = CreateArchivedEventRequest.builder()
                .organizerId(organizerId)
                .winnerId(winnerId)
                .loserId(loserId)
                .eventId(eventId)
                .date(date)
                .place(place)
                .build();

        AppUserEntity organizer = new AppUserEntity();
        organizer.setId(organizerId);

        when(appUserRepository.findById(organizerId)).thenReturn(java.util.Optional.of(organizer));
        when(boxerRepository.findById(winnerId)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(BoxerNotFoundException.class, () -> createArchivedEventUseCase.createArchivedEvent(request));
    }

    @Test
    void shouldThrowBoxerNotFoundExceptionForLoser() {
        // Arrange
        Long organizerId = 1L;
        Long winnerId = 2L;
        Long loserId = 3L;
        Long eventId = 4L;
        LocalDateTime date = LocalDateTime.now();
        String place = "Test Venue";

        CreateArchivedEventRequest request = CreateArchivedEventRequest.builder()
                .organizerId(organizerId)
                .winnerId(winnerId)
                .loserId(loserId)
                .eventId(eventId)
                .date(date)
                .place(place)
                .build();

        AppUserEntity organizer = new AppUserEntity();
        organizer.setId(organizerId);
        BoxerEntity winner = new BoxerEntity();
        winner.setId(winnerId);

        when(appUserRepository.findById(organizerId)).thenReturn(java.util.Optional.of(organizer));
        when(boxerRepository.findById(winnerId)).thenReturn(java.util.Optional.of(winner));
        when(boxerRepository.findById(loserId)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(BoxerNotFoundException.class, () -> createArchivedEventUseCase.createArchivedEvent(request));
    }
}
