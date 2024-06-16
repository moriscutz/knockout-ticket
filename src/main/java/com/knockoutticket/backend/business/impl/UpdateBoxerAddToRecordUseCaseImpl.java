package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.UpdateBoxerAddToRecordUseCase;
import com.knockoutticket.backend.business.exception.InvalidBoxerRecordException;
import com.knockoutticket.backend.domain.requests.UpdateBoxerAddToRecordRequest;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//Clarification: This class adds wins/losses/draws to a boxer's record.
@Service
@RequiredArgsConstructor
public class UpdateBoxerAddToRecordUseCaseImpl implements UpdateBoxerAddToRecordUseCase {
    private final BoxerRepository boxerRepository;

    public void updateBoxerRecord(Long id, UpdateBoxerAddToRecordRequest request){
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


        Integer wins = boxer.getWins();
        Integer draws = boxer.getDraws();
        Integer losses = boxer.getLosses();

        boxer.setWins(wins + request.getWins());
        boxer.setLosses(losses + request.getLosses());
        boxer.setDraws(draws + request.getDraws());

        return boxer;

    }
}
