package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.*;
import com.knockoutticket.backend.domain.requests.CreateBoxerRequest;
import com.knockoutticket.backend.domain.requests.UpdateBoxerAddToRecordRequest;
import com.knockoutticket.backend.domain.requests.UpdateBoxerRequest;
import com.knockoutticket.backend.domain.responses.CreateBoxerResponse;
import com.knockoutticket.backend.domain.responses.GetAggregatedBoxerStatsResponse;
import com.knockoutticket.backend.domain.responses.GetBoxerResponse;
import com.knockoutticket.backend.domain.responses.UpdateBoxerResponse;
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
    private final GetBoxerByIdUseCase getBoxerByIdUseCase;
    private final GetAggregatedBoxerStatsUseCase getAggregatedBoxerStatsUseCase;
    private final UpdateBoxerUseCase updateBoxerUseCase;
    private final DeleteBoxerUseCase deleteBoxerUseCase;
    private final UpdateBoxerAddToRecordUseCase updateBoxerAddToRecordUseCase;

    @RolesAllowed({"EVENT_ORGANIZER", "ADMINISTRATOR"})
    @PostMapping
    public ResponseEntity<CreateBoxerResponse> createBoxer(@Valid @RequestBody CreateBoxerRequest request){
        CreateBoxerResponse response = createBoxerUseCase.createBoxer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RolesAllowed({"NORMAL_USER", "ADMINISTRATOR", "EVENT_ORGANIZER"})
    @GetMapping
    public ResponseEntity<List<GetBoxerResponse>> getAllBoxers(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) Integer minWins,
            @RequestParam(required = false) Integer maxLosses) {

        List<GetBoxerResponse> boxers = getAllBoxersUseCase.getFilteredBoxers(fullName, minWins, maxLosses);
        return new ResponseEntity<>(boxers, HttpStatus.OK);
    }

    @RolesAllowed({"NORMAL_USER", "ADMINISTRATOR", "EVENT_ORGANIZER"})
    @GetMapping("/{id}")
    public ResponseEntity<GetBoxerResponse> getBoxerById(@PathVariable Long id) {
        GetBoxerResponse response = getBoxerByIdUseCase.getBoxerById(id);
        return ResponseEntity.ok(response);
    }

    @RolesAllowed({"NORMAL_USER", "ADMINISTRATOR", "EVENT_ORGANIZER"})
    @GetMapping("/aggregated-boxer-stats")
    public ResponseEntity<GetAggregatedBoxerStatsResponse> getAggregatedBoxerStats(){
        GetAggregatedBoxerStatsResponse response = getAggregatedBoxerStatsUseCase.getAggregatedBoxerStats();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RolesAllowed({"ADMINISTRATOR", "EVENT_ORGANIZER"})
    @PutMapping("/{id}")
    public ResponseEntity<UpdateBoxerResponse> updateBoxer(@PathVariable Long id, @Valid @RequestBody UpdateBoxerRequest request){
        request.setId(id); // Here I ensure that the id form the path is set in the request object.
        UpdateBoxerResponse response = updateBoxerUseCase.updateBoxer(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RolesAllowed({"ADMINISTRATOR", "EVENT_ORGANIZER"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoxer(@PathVariable Long id) {
        deleteBoxerUseCase.deleteBoxer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RolesAllowed({"ADMINISTRATOR", "EVENT_ORGANIZER"})
    @PutMapping("records/{id}")
    public ResponseEntity<Void> updateBoxerRecord(@PathVariable Long id, @Valid @RequestBody UpdateBoxerAddToRecordRequest request){
        request.setId(id);
        updateBoxerAddToRecordUseCase.updateBoxerRecord(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
