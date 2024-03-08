package com.knockoutticket.backend.service;

import com.knockoutticket.backend.domain.user.Boxer;

import java.util.List;
import java.util.Optional;

public interface BoxerService {
    Boxer saveBoxer(Boxer boxer);
    Optional<Boxer> getBoxerById(Long id);
    List<Boxer> getAllBoxers();
    Boxer updateBoxer(Long id, Boxer boxer);
    void deleteBoxer(Long id);
}
