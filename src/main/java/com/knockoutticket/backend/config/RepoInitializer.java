package com.knockoutticket.backend.config;

import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class RepoInitializer {

    private final AppUserRepository appUserRepository;

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
