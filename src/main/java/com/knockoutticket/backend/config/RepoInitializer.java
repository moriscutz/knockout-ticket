package com.knockoutticket.backend.config;

import com.knockoutticket.backend.business.CreateAppUserUseCase;
import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class RepoInitializer {

    private final AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;
    private CreateAppUserUseCase createAppUserUseCase;
    public final String password1 = passwordEncoder.encode("password1");
    public final String password2 = passwordEncoder.encode("password2");
    @Autowired
    public RepoInitializer(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @PostConstruct
    public void init() {


        AppUserEntity user1 = AppUserEntity.builder()
                .username("user1")
                .email("user1@example.com")
                .password("password1")
                .userType(UserType.NORMAL_USER)
                .build();

        AppUserEntity user2 = AppUserEntity.builder()
                .username("user2")
                .email("user2@example.com")
                .password("password2")
                .userType(UserType.NORMAL_USER)
                .build();

        appUserRepository.save(user1);
        appUserRepository.save(user2);
    }
}
