package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.domain.models.WeightClass;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.domain.models.EventStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoxerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BoxerRepository boxerRepository;

    @Test
    void whenFindByFullNameIgnoreCase_thenReturnBoxerEntity() {

        BoxerEntity boxer = BoxerEntity.builder()
                .fullName("Albert Einstein")
                .weightClass(WeightClass.HEAVYWEIGHT)
                .wins(50)
                .losses(6)
                .draws(2)
                .weight(240.0f)
                .age(54)
                .build();
        entityManager.persist(boxer);
        entityManager.flush();


        BoxerEntity found = boxerRepository.findByFullNameIgnoreCase("aLbert einStein");


        assertThat(found).isNotNull();
        assertThat(found.getFullName()).isEqualTo(boxer.getFullName());
    }

    @Test
    void whenFindBoxerById_thenReturnBoxerEntity() {

        BoxerEntity boxer = BoxerEntity.builder()
                .fullName("Muhammad Ali")
                .weightClass(WeightClass.HEAVYWEIGHT)
                .wins(56)
                .losses(5)
                .draws(0)
                .weight(215.0f)
                .age(74)
                .build();
        entityManager.persist(boxer);
        entityManager.flush();


        BoxerEntity found = boxerRepository.findBoxerById(boxer.getId());


        assertThat(found).isNotNull();
        assertThat(found.getFullName()).isEqualTo(boxer.getFullName());
    }

    @Test
    void whenFindBoxersByEventId_thenReturnBoxerEntities() {

        BoxerEntity boxer1 = BoxerEntity.builder()
                .fullName("Lennox Lewis")
                .weightClass(WeightClass.HEAVYWEIGHT)
                .wins(41)
                .losses(2)
                .draws(1)
                .weight(250.0f)
                .age(55)
                .build();
        entityManager.persist(boxer1);

        BoxerEntity boxer2 = BoxerEntity.builder()
                .fullName("Evander Holyfield")
                .weightClass(WeightClass.HEAVYWEIGHT)
                .wins(44)
                .losses(10)
                .draws(2)
                .weight(220.0f)
                .age(58)
                .build();
        entityManager.persist(boxer2);

        AppUserEntity organizer = AppUserEntity.builder()
                .username("organizer")
                .email("organizer@example.com")
                .password("password")
                .build();
        entityManager.persist(organizer);

        EventEntity event = EventEntity.builder()
                .boxer1(boxer1)
                .boxer2(boxer2)
                .organizer(organizer)
                .date(LocalDateTime.now())
                .status(EventStatus.SCHEDULED)
                .place("Madison Square Garden")
                .build();
        entityManager.persist(event);
        entityManager.flush();


        List<BoxerEntity> foundBoxers = boxerRepository.findBoxersByEventId(event.getId());


        assertThat(foundBoxers).isNotNull();
        assertThat(foundBoxers).hasSize(2);
        assertThat(foundBoxers).contains(boxer1, boxer2);
    }
}
