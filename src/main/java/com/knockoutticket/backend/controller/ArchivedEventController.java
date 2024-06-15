package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.CreateArchivedEventUseCase;
import com.knockoutticket.backend.domain.requests.CreateArchivedEventRequest;
import com.knockoutticket.backend.domain.responses.CreateArchivedEventResponse;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/archives")
@RequiredArgsConstructor
public class ArchivedEventController {

    private final CreateArchivedEventUseCase createArchivedEventUseCase;

    @RolesAllowed({"EVENT_ORGANIZER"})
    @PostMapping
    public ResponseEntity<CreateArchivedEventResponse> createArchivedEvent(CreateArchivedEventRequest request){
        CreateArchivedEventResponse response = createArchivedEventUseCase.createArchivedEvent(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
