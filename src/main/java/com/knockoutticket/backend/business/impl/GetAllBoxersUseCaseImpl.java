package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.GetAllBoxersUseCase;
import com.knockoutticket.backend.domain.models.Boxer;
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
                .collect(Collectors.toList());
    }
}
