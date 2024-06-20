package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.DeleteArchivedEventsByBoxerUseCase;
import com.knockoutticket.backend.persistence.ArchivedEventRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteArchivedEventsByBoxerUseCaseImpl implements DeleteArchivedEventsByBoxerUseCase {
    private final ArchivedEventRepository archivedEventRepository;

    @Transactional
    @Override
    public void deleteArchivedEventsByBoxer(Long boxerId){
        archivedEventRepository.deleteArchivedEventsByBoxerId(boxerId);
    }
}
