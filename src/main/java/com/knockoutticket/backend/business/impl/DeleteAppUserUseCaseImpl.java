package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.DeleteAppUserUseCase;
import com.knockoutticket.backend.persistence.AppUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAppUserUseCaseImpl implements DeleteAppUserUseCase {

    private final AppUserRepository appUserRepository;

    @Transactional
    @Override
    public void deleteAppUser(Long id) {
        appUserRepository.deleteById(id);
    }
}
