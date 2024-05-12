package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.CreateBoxerUseCase;
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
        String fullName = request.getFullName();

        // I've decided that two or more boxers can share the same name, as there are many occurences in ---
        // real life where fighters share the same name, so it would not be wise to not allow creation of multiple boxers with similar names
        //        BoxerEntity existingBoxer = boxerRepository.findByFullNameIgnoreCase(fullName);
        //        if (existingBoxer != null) {
        //            return CreateBoxerResponse.builder()
        //                    .message("A boxer with a similar name already exists.")
        //                    .id(existingBoxer.getId())
        //                    .build();
        //        }

        BoxerEntity newBoxer = saveNewBoxer(request);
        return CreateBoxerResponse.builder()
                .message("Boxer created successfully.")
                .id(newBoxer.getId())
                .build();
    }

    private BoxerEntity saveNewBoxer(CreateBoxerRequest request) {

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
