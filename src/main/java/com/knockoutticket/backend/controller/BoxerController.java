package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.CreateBoxerUseCase;
import com.knockoutticket.backend.business.GetAllBoxersUseCase;
import com.knockoutticket.backend.domain.requests.CreateBoxerRequest;
import com.knockoutticket.backend.domain.responses.CreateBoxerResponse;
import com.knockoutticket.backend.domain.responses.GetBoxerResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boxers")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173/"})
public class BoxerController {
    private final CreateBoxerUseCase createBoxerUseCase;
    private final GetAllBoxersUseCase getAllBoxersUseCase;
    @RolesAllowed({"EVENT_ORGANIZER", "ADMINISTRATOR"})
    @PostMapping
    public ResponseEntity<CreateBoxerResponse> createBoxer(@Valid @RequestBody CreateBoxerRequest request){
        CreateBoxerResponse response = createBoxerUseCase.createBoxer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RolesAllowed({"NORMAL_USER", "ADMINISTRATOR", "EVENT_ORGANIZER"})
    @GetMapping
    public ResponseEntity<List<GetBoxerResponse>> getAllBoxers(){
        List<GetBoxerResponse> responses = getAllBoxersUseCase.getAllBoxers();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
