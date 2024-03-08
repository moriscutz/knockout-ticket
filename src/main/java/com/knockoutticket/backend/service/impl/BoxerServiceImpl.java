package com.knockoutticket.backend.service.impl;

import com.knockoutticket.backend.domain.user.Boxer;
import com.knockoutticket.backend.repository.BoxerRepository;
import com.knockoutticket.backend.service.BoxerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BoxerServiceImpl implements BoxerService {
    private final BoxerRepository boxerRepository;

    @Autowired
    public BoxerServiceImpl(BoxerRepository boxerRepository) {
        this.boxerRepository = boxerRepository;
    }

    @Override
    public Boxer saveBoxer(Boxer boxer) {
        return boxerRepository.save(boxer);
    }

    @Override
    public Optional<Boxer> getBoxerById(Long id) {
        return boxerRepository.findById(id);
    }

    @Override
    public List<Boxer> getAllBoxers() {
        return boxerRepository.findAll();
    }

    @Override
    public Boxer updateBoxer(Long id, Boxer boxer) {
        return boxerRepository.findById(id)
                .map(existingBoxer -> {
                    existingBoxer.setEmail(boxer.getEmail());
                    existingBoxer.setPassword(boxer.getPassword());
                    existingBoxer.setUserType(boxer.getUserType());
                    existingBoxer.setFullName(boxer.getFullName());
                    existingBoxer.setWeightClass(boxer.getWeightClass());
                    existingBoxer.setWins(boxer.getWins());
                    existingBoxer.setDraws(boxer.getDraws());
                    existingBoxer.setLosses(boxer.getLosses());
                    existingBoxer.setNoContests(boxer.getNoContests());
                    existingBoxer.setAge(boxer.getAge());
                    return boxerRepository.save(existingBoxer);
                }).orElseThrow(() -> new RuntimeException("Boxer not found"));
    }

    @Override
    public void deleteBoxer(Long id) {
        boxerRepository.deleteById(id);
    }
}
