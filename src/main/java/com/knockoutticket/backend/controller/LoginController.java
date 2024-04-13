package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.domain.requests.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.knockoutticket.backend.business.LoginUseCase;
@RestController
@RequestMapping("/AUTH")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173/"})
public class LoginController {
        private final LoginUseCase loginUseCase;

        @PostMapping
        public ResponseEntity login(@RequestBody @Validated LoginRequest loginRequest)
        {
                if(loginUseCase.login(loginRequest)){
                return ResponseEntity.status(HttpStatus.CREATED).body(1);
                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(1);
        }

}
