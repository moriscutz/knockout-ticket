package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.GetAllBoxersUseCase;
import com.knockoutticket.backend.domain.models.Boxer;
import com.knockoutticket.backend.domain.models.WeightClass;
import com.knockoutticket.backend.domain.responses.GetBoxerResponse;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class GetAllBoxersUseCaseImpl implements GetAllBoxersUseCase {
    private final BoxerRepository boxerRepository;

    @Override
    public List<GetBoxerResponse> getAllBoxers(){
        List<BoxerEntity> allBoxers = boxerRepository.findAll();
        return allBoxers.stream()
                .map(BoxerConverter::toBoxerDTO)
                .map(GetBoxerResponse::new)
                .toList();
    }

    @Override
    public List<GetBoxerResponse> getFilteredBoxers(String fullName, Integer minWins, Integer maxLosses) {
        return boxerRepository.findAll().stream()
                .filter(boxer -> (fullName == null || boxer.getFullName().toLowerCase().contains(fullName.toLowerCase())) &&
                        (minWins == null || boxer.getWins() >= minWins) &&
                        (maxLosses == null || boxer.getLosses() <= maxLosses))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private GetBoxerResponse mapToResponse(BoxerEntity boxerEntity) {
        Boxer boxer = Boxer.builder()
                .id(boxerEntity.getId())
                .fullName(boxerEntity.getFullName())
                .weightClass(WeightClass.valueOf(boxerEntity.getWeightClass().name()))
                .wins(boxerEntity.getWins())
                .losses(boxerEntity.getLosses())
                .draws(boxerEntity.getDraws())
                .weight(boxerEntity.getWeight())
                .age(boxerEntity.getAge())
                .nextEvent(null)
                .build();

        return GetBoxerResponse.builder()
                .boxer(boxer)
                .build();
    }
}
