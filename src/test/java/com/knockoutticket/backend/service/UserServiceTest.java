package com.knockoutticket.backend.service;


import com.knockoutticket.backend.repository.UserRepository;
import com.knockoutticket.backend.domain.user.AppUser;
import com.knockoutticket.backend.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveUser() {
        AppUser user = new AppUser();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.save(any(AppUser.class))).thenReturn(user);

        AppUser savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals("test@example.com", savedUser.getEmail());
        assertEquals("password", savedUser.getPassword());

        verify(userRepository, times(1)).save(any(AppUser.class));
    }

    @Test
    void getUserById() {
        AppUser user = new AppUser();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<AppUser> foundUser = userService.getUserById(1L);

        assertTrue(foundUser.isPresent());
        assertEquals(1L, foundUser.get().getId());
        assertEquals("test@example.com", foundUser.get().getEmail());
        assertEquals("password", foundUser.get().getPassword());

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getAllUsers() {
        List<AppUser> userList = new ArrayList<>();
        userList.add(new AppUser());
        userList.add(new AppUser());

        when(userRepository.findAll()).thenReturn(userList);

        List<AppUser> foundUsers = userService.getAllUsers();

        assertEquals(2, foundUsers.size());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void updateUser() {
        AppUser existingUser = new AppUser();
        existingUser.setId(1L);
        existingUser.setEmail("test@example.com");
        existingUser.setPassword("password");

        AppUser updatedUser = new AppUser();
        updatedUser.setId(1L);
        updatedUser.setEmail("updated@example.com");
        updatedUser.setPassword("updatedPassword");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(AppUser.class))).thenReturn(updatedUser);

        AppUser returnedUser = userService.updateUser(1L, updatedUser);

        assertNotNull(returnedUser);
        assertEquals(1L, returnedUser.getId());
        assertEquals("updated@example.com", returnedUser.getEmail());
        assertEquals("updatedPassword", returnedUser.getPassword());

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(AppUser.class));
    }

    @Test
    void deleteUser() {
        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
