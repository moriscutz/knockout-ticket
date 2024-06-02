package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.UpdateBoxerUseCase;
import com.knockoutticket.backend.business.exception.BoxerNotFoundException;
import com.knockoutticket.backend.domain.requests.UpdateBoxerRequest;
import com.knockoutticket.backend.domain.responses.UpdateBoxerResponse;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateBoxerUseCaseImpl implements UpdateBoxerUseCase {

    private final BoxerRepository boxerRepository;

    @Transactional
    @Override
    public UpdateBoxerResponse updateBoxer(UpdateBoxerRequest request){
        BoxerEntity existingBoxer = boxerRepository.findById(request.getId())
                .orElseThrow(() -> new BoxerNotFoundException(request.getId()));

        existingBoxer.setFullName(request.getFullName());
        existingBoxer.setWins(request.getWins());
        existingBoxer.setLosses(request.getLosses());
        existingBoxer.setDraws(request.getDraws());

        boxerRepository.save(existingBoxer);

        return UpdateBoxerResponse.builder()
                .id(existingBoxer.getId())
                .fullName(existingBoxer.getFullName())
                .wins(existingBoxer.getWins())
                .losses(existingBoxer.getLosses())
                .draws(existingBoxer.getDraws())
                .build();
    }
}
