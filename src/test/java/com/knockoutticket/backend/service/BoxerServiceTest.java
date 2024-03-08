package com.knockoutticket.backend.service;

import com.knockoutticket.backend.domain.user.Boxer;
import com.knockoutticket.backend.repository.BoxerRepository;
import com.knockoutticket.backend.service.impl.BoxerServiceImpl;
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

class BoxerServiceTest {

    @Mock
    private BoxerRepository boxerRepository;

    @InjectMocks
    private BoxerServiceImpl boxerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveBoxer() {
        Boxer boxer = new Boxer();
        boxer.setId(1L);
        when(boxerRepository.save(boxer)).thenReturn(boxer);

        Boxer savedBoxer = boxerService.saveBoxer(boxer);

        assertNotNull(savedBoxer);
        assertEquals(1L, savedBoxer.getId());
    }

    @Test
    void getBoxerById() {
        long id = 1L;
        Boxer boxer = new Boxer();
        boxer.setId(id);
        when(boxerRepository.findById(id)).thenReturn(Optional.of(boxer));

        Optional<Boxer> optionalBoxer = boxerService.getBoxerById(id);

        assertTrue(optionalBoxer.isPresent());
        assertEquals(id, optionalBoxer.get().getId());
    }

    @Test
    void getAllBoxers() {
        List<Boxer> boxerList = new ArrayList<>();
        boxerList.add(new Boxer());
        boxerList.add(new Boxer());
        when(boxerRepository.findAll()).thenReturn(boxerList);

        List<Boxer> fetchedBoxers = boxerService.getAllBoxers();

        assertNotNull(fetchedBoxers);
        assertEquals(2, fetchedBoxers.size());
    }

    @Test
    void updateBoxer() {
        long id = 1L;
        Boxer boxer = new Boxer();
        boxer.setId(id);
        when(boxerRepository.findById(id)).thenReturn(Optional.of(boxer));

        Boxer updatedBoxer = new Boxer();
        updatedBoxer.setId(id);
        updatedBoxer.setFullName("Updated Name");
        when(boxerRepository.save(boxer)).thenReturn(updatedBoxer);

        Boxer result = boxerService.updateBoxer(id, updatedBoxer);

        assertNotNull(result);
        assertEquals("Updated Name", result.getFullName());
    }

    @Test
    void deleteBoxer() {
        long id = 1L;
        doNothing().when(boxerRepository).deleteById(id);

        boxerService.deleteBoxer(id);

        verify(boxerRepository, times(1)).deleteById(id);
    }
}
