package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.CreateBoxerUseCase;
import com.knockoutticket.backend.domain.requests.CreateBoxerRequest;
import com.knockoutticket.backend.domain.responses.CreateBoxerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boxers")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173/"})
public class BoxerController {
    private final CreateBoxerUseCase createBoxerUseCase;

    @PostMapping
    public ResponseEntity<CreateBoxerResponse> createBoxer(@Valid @RequestBody CreateBoxerRequest request){
        System.out.println(request);
        CreateBoxerResponse response = createBoxerUseCase.createBoxer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
