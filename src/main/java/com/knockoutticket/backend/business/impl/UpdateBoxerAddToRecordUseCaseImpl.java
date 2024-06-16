package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.UpdateBoxerAddToRecordUseCase;
import com.knockoutticket.backend.business.exception.InvalidBoxerRecordException;
import com.knockoutticket.backend.domain.requests.UpdateBoxerAddToRecordRequest;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateBoxerAddToRecordUseCaseImpl implements UpdateBoxerAddToRecordUseCase {
    private final BoxerRepository boxerRepository;


    public void UpdateBoxerRecord(Long id, UpdateBoxerAddToRecordRequest request){
        if (!validation(request)) {
            throw new InvalidBoxerRecordException("Invalid record values: Wins, losses, and draws must be greater than or equal to 0");
        }

        BoxerEntity boxer = boxerRepository.findBoxerById(id);
        boxerRepository.save(addRecord(boxer, request));

    }

    private boolean validation(UpdateBoxerAddToRecordRequest request) {
        return request.getWins() >= 0 && request.getLosses() >= 0 && request.getDraws() >= 0;
    }

    private BoxerEntity addRecord(BoxerEntity boxer, UpdateBoxerAddToRecordRequest request){
        Integer wins, losses, draws;

        wins = boxer.getWins();
        draws = boxer.getDraws();
        losses = boxer.getLosses();

        boxer.setWins(wins + request.getWins());
        boxer.setLosses(losses + request.getLosses());
        boxer.setDraws(losses + request.getDraws());

        return boxer;

    }
}
