package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.GetBoxerByIdUseCase;
import com.knockoutticket.backend.domain.responses.GetBoxerResponse;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetBoxerByIdUseCaseImpl implements GetBoxerByIdUseCase {
    private final BoxerRepository boxerRepository;

    @Override
    public GetBoxerResponse getBoxerById(Long id) {
        BoxerEntity boxerEntity = boxerRepository.findBoxerById(id);
        return new GetBoxerResponse(BoxerConverter.toBoxerDTO(boxerEntity));
    }
}
