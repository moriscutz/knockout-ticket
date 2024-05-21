package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.CreateBoxerUseCase;
import com.knockoutticket.backend.business.exception.*;
import com.knockoutticket.backend.domain.models.WeightClass;
import com.knockoutticket.backend.domain.requests.CreateBoxerRequest;
import com.knockoutticket.backend.domain.responses.CreateBoxerResponse;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateBoxerUseCaseImpl implements CreateBoxerUseCase {

    private final BoxerRepository boxerRepository;

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public CreateBoxerResponse createBoxer(CreateBoxerRequest request){
        BoxerEntity newBoxer = saveNewBoxer(request);
        return CreateBoxerResponse.builder()
                .message("Boxer created successfully.")
                .id(newBoxer.getId())
                .build();
    }

    private BoxerEntity saveNewBoxer(CreateBoxerRequest request) {

        String fullName = request.getFullName();
        if(fullName == null || fullName.isBlank()){
            throw new BlankBoxerFullNameException();
        }

        WeightClass weightClass = request.getWeightClass();
        if(weightClass == null){
            throw new BlankWeightClassException();
        }

        Integer wins = request.getWins();
        if(wins == null){
            throw new BlankWinsException();
        }
        Integer losses = request.getLosses();
        if(losses == null){
            throw new BlankLossesException();
        }

        Integer draws = request.getDraws();
        if(draws == null){
            throw new BlankDrawsException();
        }

        Float weight = request.getWeight();
        if(weight == null){
            throw new BlankWeightException();
        }

        Integer age = request.getAge();
        if(age == null){
            throw new BlankAgeException();
        }

        BoxerEntity newBoxer = BoxerEntity.builder()
                .fullName(request.getFullName())
                .weightClass(request.getWeightClass())
                .wins(request.getWins())
                .losses(request.getLosses())
                .draws(request.getDraws())
                .weight(request.getWeight())
                .age(request.getAge())
                .build();
        return boxerRepository.save(newBoxer);
    }
}
