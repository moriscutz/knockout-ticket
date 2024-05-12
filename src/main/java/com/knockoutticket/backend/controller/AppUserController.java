package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.*;
import com.knockoutticket.backend.domain.models.AppUser;
import com.knockoutticket.backend.domain.requests.CreateAppUserRequest;
import com.knockoutticket.backend.domain.requests.UpdateAppUserRequest;
import com.knockoutticket.backend.domain.responses.CreateAppUserResponse;
import com.knockoutticket.backend.domain.responses.GetAppUserResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173/"})
public class AppUserController {

    private final CreateAppUserUseCase createAppUserUseCase;
    private final GetAppUserUseCase getAppUserUseCase;
    private final UpdateAppUserUseCase updateAppUserUseCase;
    private final DeleteAppUserUseCase deleteAppUserUseCase;
    private final GetAllAppUsersUseCase getAllAppUsersUseCase;


    @PostMapping
    public ResponseEntity<CreateAppUserResponse> createAppUser(@Valid @RequestBody CreateAppUserRequest request) {
        CreateAppUserResponse response = createAppUserUseCase.createAppUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RolesAllowed({"NORMAL_USER", "ADMINISTRATOR", "EVENT_ORGANIZER"})
    @GetMapping("/{id}")
    public ResponseEntity<GetAppUserResponse> getAppUser(@PathVariable Long id) {
        GetAppUserResponse response = getAppUserUseCase.getAppUser(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RolesAllowed({"NORMAL_USER", "ADMINISTRATOR", "EVENT_ORGANIZER"})
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAppUser(@PathVariable Long id, @RequestBody UpdateAppUserRequest request) {
        updateAppUserUseCase.updateAppUser(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RolesAllowed({"ADMINISTRATOR"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppUser(@PathVariable Long id) {
        deleteAppUserUseCase.deleteAppUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RolesAllowed({"NORMAL_USER", "ADMINISTRATOR", "EVENT_ORGANIZER"})
    @GetMapping
    public ResponseEntity<List<GetAppUserResponse>> getAllAppUsers() {
        List<GetAppUserResponse> responses = getAllAppUsersUseCase.getAllAppUsers();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
