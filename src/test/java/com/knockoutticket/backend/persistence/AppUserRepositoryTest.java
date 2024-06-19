package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.UserTypeEntity;
import com.knockoutticket.backend.domain.models.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppUserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    void whenFindByUsername_thenReturnAppUserEntity() {

        AppUserEntity user = AppUserEntity.builder()
                .username("testUser")
                .email("test@example.com")
                .password("password")
                .userRoles(Collections.emptySet())
                .build();
        entityManager.persist(user);
        entityManager.flush();


        AppUserEntity found = appUserRepository.findByUsername(user.getUsername());


        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void save_shouldSaveUserWithAllFields() {

        AppUserEntity user = AppUserEntity.builder()
                .username("garry_kasparov")
                .password("securePassword123")
                .email("garry@example.com")
                .userRoles(Collections.emptySet())
                .build();


        UserTypeEntity userType = UserTypeEntity.builder()
                .type(UserType.NORMAL_USER)
                .user(user)
                .build();
        user.setUserRoles(Collections.singleton(userType));


        AppUserEntity savedUser = appUserRepository.save(user);


        AppUserEntity foundUser = entityManager.find(AppUserEntity.class, savedUser.getId());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(foundUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(foundUser.getPassword()).isEqualTo(user.getPassword());
        assertEquals(user.getUserRoles().size(), foundUser.getUserRoles().size());
    }
}
