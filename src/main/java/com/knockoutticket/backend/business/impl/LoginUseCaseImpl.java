package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.LoginUseCase;
import com.knockoutticket.backend.business.exception.InvalidCredentialsException;
import com.knockoutticket.backend.config.security.token.AccessToken;
import com.knockoutticket.backend.config.security.token.AccessTokenEncoder;
import com.knockoutticket.backend.config.security.token.impl.AccessTokenImpl;
import com.knockoutticket.backend.domain.requests.LoginRequest;
import com.knockoutticket.backend.domain.responses.LoginResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest){
        AppUserEntity user = userRepository.findByUsername(loginRequest.getUsername());
        if(user == null){
            throw new InvalidCredentialsException();
        }

        if(!matchesPassword(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword){
        return passwordEncoder.matches(rawPassword,encodedPassword);
    }

    private String generateAccessToken(AppUserEntity user) {
        AccessToken accessToken = new AccessTokenImpl(user.getUsername(), user.getId(), getUserRoles(user));
        return accessTokenEncoder.encode(accessToken);
    }

    private Set<String> getUserRoles(AppUserEntity user) {
        return user.getUserRoles().stream()
                .map(userRole -> userRole.getType().toString())
                .collect(Collectors.toSet());
    }
}
