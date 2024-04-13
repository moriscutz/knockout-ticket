package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.LoginUseCase;
import com.knockoutticket.backend.domain.requests.LoginRequest;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private AppUserRepository userRepository;
    //private final AccessTokenEncoder accessTokenEncoder;
    @Override
    public boolean login(LoginRequest request) {
        AppUserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NoSuchElementException("USER NOT FOUND"));

        return matchesPassword(request.getPassword(), user);
    }

    private boolean matchesPassword(String passwordToCheck, AppUserEntity userToCheck)
    {
        return passwordToCheck.matches(userToCheck.getPassword());
    }
}
