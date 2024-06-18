//package com.knockoutticket.backend.persistence;
//
//import com.knockoutticket.backend.domain.models.EventStatus;
//import com.knockoutticket.backend.domain.models.WeightClass;
//import com.knockoutticket.backend.persistence.entity.AppUserEntity;
//import com.knockoutticket.backend.persistence.entity.BoxerEntity;
//import com.knockoutticket.backend.persistence.entity.EventEntity;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class EventRepositoryTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private EventRepository eventRepository;
//
//    @Test
//    void whenFindByBoxer1IdOrBoxer2Id_thenReturnEventEntities() {
//
//        BoxerEntity boxer1 = BoxerEntity.builder()
//                .fullName("Lennox Lewis")
//                .weightClass(WeightClass.HEAVYWEIGHT)
//                .wins(41)
//                .losses(2)
//                .draws(1)
//                .weight(250.0f)
//                .age(55)
//                .build();
//        entityManager.persist(boxer1);
//
//        BoxerEntity boxer2 = BoxerEntity.builder()
//                .fullName("Evander Holyfield")
//                .weightClass(WeightClass.HEAVYWEIGHT)
//                .wins(44)
//                .losses(10)
//                .draws(2)
//                .weight(220.0f)
//                .age(58)
//                .build();
//        entityManager.persist(boxer2);
//
//        BoxerEntity boxer3 = BoxerEntity.builder()
//                .fullName("Mike Tyson")
//                .weightClass(WeightClass.HEAVYWEIGHT)
//                .wins(50)
//                .losses(6)
//                .draws(2)
//                .weight(240.0f)
//                .age(54)
//                .build();
//        entityManager.persist(boxer3);
//
//        AppUserEntity organizer = AppUserEntity.builder()
//                .username("organizer")
//                .email("organizer@example.com")
//                .password("password")
//                .build();
//        entityManager.persist(organizer);
//
//        EventEntity event1 = EventEntity.builder()
//                .boxer1(boxer1)
//                .boxer2(boxer2)
//                .organizer(organizer)
//                .date(LocalDateTime.now())
//                .status(EventStatus.SCHEDULED)
//                .place("Madison Square Garden")
//                .build();
//        entityManager.persist(event1);
//
//        EventEntity event2 = EventEntity.builder()
//                .boxer1(boxer3)
//                .boxer2(null)
//                .organizer(organizer)
//                .date(LocalDateTime.now().plusDays(1))
//                .status(EventStatus.SCHEDULED)
//                .place("MGM Grand")
//                .build();
//        entityManager.persist(event2);
//
//        entityManager.flush();
//
//
//        List<EventEntity> foundEventsForBoxer1 = eventRepository.findByBoxer1IdOrBoxer2Id(boxer1.getId(), boxer1.getId());
//        List<EventEntity> foundEventsForBoxer2 = eventRepository.findByBoxer1IdOrBoxer2Id(boxer2.getId(), boxer2.getId());
//        List<EventEntity> foundEventsForBoxer3 = eventRepository.findByBoxer1IdOrBoxer2Id(boxer3.getId(), boxer3.getId());
//
//        assertThat(foundEventsForBoxer1)
//                .isNotNull()
//                .hasSize(1)
//                .contains(event1);
//
//        assertThat(foundEventsForBoxer2)
//                .isNotNull()
//                .hasSize(1)
//                .contains(event1);
//
//        assertThat(foundEventsForBoxer3)
//                .isNotNull()
//                .hasSize(1)
//                .contains(event2);
//    }
//}
