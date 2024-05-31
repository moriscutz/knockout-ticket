package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.GetEventBoxersUseCase;
import com.knockoutticket.backend.business.exception.TwoBoxersRequiredException;
import com.knockoutticket.backend.domain.models.Boxer;
import com.knockoutticket.backend.domain.requests.GetEventBoxersRequest;
import com.knockoutticket.backend.domain.responses.GetEventBoxersResponse;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetEventBoxersUseCaseImpl implements GetEventBoxersUseCase {
    private final BoxerRepository boxerRepository;

    @Override
    public GetEventBoxersResponse getEventBoxers(GetEventBoxersRequest request){
        List<BoxerEntity> boxers = boxerRepository.findBoxersByEventId(request.getEventId());

        if(boxers.size() != 2 ) {throw new TwoBoxersRequiredException(); }

        Boxer boxer1= BoxerConverter.toBoxerDTO(boxers.get(0));
        Boxer boxer2 = BoxerConverter.toBoxerDTO((boxers.get(1)));

        return GetEventBoxersResponse.builder()
                .boxer1(boxer1)
                .boxer2(boxer2)
                .build();
    }
}
