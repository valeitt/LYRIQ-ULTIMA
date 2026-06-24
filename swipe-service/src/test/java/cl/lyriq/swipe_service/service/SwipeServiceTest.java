package cl.lyriq.swipe_service.service;

import cl.lyriq.swipe_service.dto.SongResponseDTO;
import cl.lyriq.swipe_service.dto.SwipeDTO;
import cl.lyriq.swipe_service.model.Swipe;
import cl.lyriq.swipe_service.model.SwipeAction;
import cl.lyriq.swipe_service.repository.SwipeRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SwipeServiceTest {

    @Mock
    private SwipeRepository swipeRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private SwipeService swipeService;

    @Test
    void shouldReturnSwipeById() {

        // Given
        Swipe swipe = new Swipe();
        swipe.setId(1L);
        swipe.setUserId(100L);
        swipe.setSongId(200L);

        when(swipeRepository.findById(1L))
                .thenReturn(Optional.of(swipe));

        // When
        Swipe result = swipeService.getSwipeById(1L);

        // Then
        assertEquals(1L, result.getId());
        assertEquals(100L, result.getUserId());
        assertEquals(200L, result.getSongId());
    }

    @Test
    void shouldCreateSwipeLike() {

        // Given
        SwipeDTO dto = new SwipeDTO();
        dto.setUserId(100L);
        dto.setSongId(200L);
        dto.setAction("LIKE");

        SongResponseDTO song = new SongResponseDTO();
        song.setIdSong(200L);

        Swipe savedSwipe = new Swipe();
        savedSwipe.setId(1L);
        savedSwipe.setUserId(100L);
        savedSwipe.setSongId(200L);
        savedSwipe.setAction(SwipeAction.LIKE);

        when(restTemplate.getForObject(
                contains("/songs/"),
                eq(SongResponseDTO.class)))
                .thenReturn(song);

        when(swipeRepository.save(any(Swipe.class)))
                .thenReturn(savedSwipe);

        // When
        Swipe result = swipeService.createSwipe(dto);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(swipeRepository, times(1))
                .save(any(Swipe.class));

        verify(restTemplate, times(2))
                .postForObject(anyString(), any(), eq(Object.class));
    }

    @Test
    void shouldDeleteSwipe() {

        // Given
        Swipe swipe = new Swipe();
        swipe.setId(1L);

        when(swipeRepository.findById(1L))
                .thenReturn(Optional.of(swipe));

        // When
        swipeService.deleteSwipe(1L);

        // Then
        verify(swipeRepository, times(1))
                .delete(swipe);
    }
}