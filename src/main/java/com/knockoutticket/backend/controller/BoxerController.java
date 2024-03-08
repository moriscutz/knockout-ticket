package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.domain.user.Boxer;
import com.knockoutticket.backend.service.BoxerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boxers")
public class BoxerController {

    private final BoxerService boxerService;

    @Autowired
    public BoxerController(BoxerService boxerService) {
        this.boxerService = boxerService;
    }

    @GetMapping
    public ResponseEntity<List<Boxer>> getAllBoxers() {
        List<Boxer> boxers = boxerService.getAllBoxers();
        return new ResponseEntity<>(boxers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boxer> getBoxerById(@PathVariable Long id) {
        Boxer boxer = boxerService.getBoxerById(id)
                .orElse(null);
        if (boxer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(boxer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boxer> createBoxer(@RequestBody Boxer boxer) {
        Boxer createdBoxer = boxerService.saveBoxer(boxer);
        return new ResponseEntity<>(createdBoxer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boxer> updateBoxer(@PathVariable Long id, @RequestBody Boxer boxer) {
        Boxer updatedBoxer = boxerService.updateBoxer(id, boxer);
        return new ResponseEntity<>(updatedBoxer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoxer(@PathVariable Long id) {
        boxerService.deleteBoxer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
