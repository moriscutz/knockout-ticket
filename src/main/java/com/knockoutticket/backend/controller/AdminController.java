package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.UpdateUserRolesAsAdminUseCase;
import com.knockoutticket.backend.domain.requests.UpdateUserRolesRequest;
import com.knockoutticket.backend.domain.responses.UpdateUserRolesResponse;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminController {

    private final UpdateUserRolesAsAdminUseCase updateUserRolesAsAdminUseCase;

    @RolesAllowed({"ADMINISTRATOR"})
    @PutMapping("/{userId}/roles")
    public ResponseEntity<UpdateUserRolesResponse> updateUserRoles(
            @PathVariable Long userId,
            @RequestBody UpdateUserRolesRequest request) {

        UpdateUserRolesResponse response = updateUserRolesAsAdminUseCase.updateUserRoles(userId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
