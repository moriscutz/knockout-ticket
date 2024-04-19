package com.knockoutticket.backend.persistence;

import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository userRepository;

    @Test
    void save_shouldSaveAppUserWithAllFields() {
        // Arrange
        AppUserEntity userEntity = new AppUserEntity();
        userEntity.setUsername("testUser");
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("password");

        // Act
        userRepository.save(userEntity);

        // Assert
        assertNotNull(userEntity.getId());
        assertTrue(userRepository.existsByUsername("testUser"));
    }

    @Test
    void findByUsername_shouldReturnCorrectUser() {
        // Arrange
        String username = "testUser";
        AppUserEntity userEntity = new AppUserEntity();
        userEntity.setUsername(username);
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("password");
        userRepository.save(userEntity);

        // Act
        AppUserEntity foundUser = userRepository.findByUsername(username);

        // Assert
        assertNotNull(foundUser);
        assertEquals(username, foundUser.getUsername());
    }

    @Test
    void existsByUsername_shouldReturnTrueIfUsernameExists() {
        // Arrange
        String username = "existingUser";
        AppUserEntity userEntity = new AppUserEntity();
        userEntity.setUsername(username);
        userEntity.setEmail("existing@example.com");
        userEntity.setPassword("password");
        userRepository.save(userEntity);

        // Act
        boolean exists = userRepository.existsByUsername(username);

        // Assert
        assertTrue(exists);
    }

    @Test
    void existsByEmail_shouldReturnTrueIfEmailExists() {
        // Arrange
        String email = "existing@example.com";
        AppUserEntity userEntity = new AppUserEntity();
        userEntity.setUsername("existingUser");
        userEntity.setEmail(email);
        userEntity.setPassword("password");
        userRepository.save(userEntity);

        // Act
        boolean exists = userRepository.existsByEmail(email);

        // Assert
        assertTrue(exists);
    }

    @Test
    void deleteById_shouldDeleteUser() {
        // Arrange
        AppUserEntity userEntity = new AppUserEntity();
        userEntity.setUsername("testUser");
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("password");
        userRepository.save(userEntity);
        Long userId = userEntity.getId();

        // Act
        userRepository.deleteById(userId);

        // Assert
        assertFalse(userRepository.existsById(userId));
    }

    @Test
    void getAllAppUsers_shouldReturnAllUsers() {
        // Arrange
        AppUserEntity user1 = new AppUserEntity();
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");
        user1.setPassword("password1");
        userRepository.save(user1);

        AppUserEntity user2 = new AppUserEntity();
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");
        user2.setPassword("password2");
        userRepository.save(user2);

        // Act
        List<AppUserEntity> users = userRepository.findAll();

        // Assert
        assertEquals(2, users.size());
    }
}
