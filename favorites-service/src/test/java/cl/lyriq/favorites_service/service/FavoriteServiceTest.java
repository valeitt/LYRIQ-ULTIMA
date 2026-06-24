package cl.lyriq.favorites_service.service;

import cl.lyriq.favorites_service.model.Favorite;
import cl.lyriq.favorites_service.repository.FavoriteRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavoriteServiceTest {

    @Mock
    private FavoriteRepository favoriteRepository;

    @InjectMocks
    private FavoriteService favoriteService;

    @Test
    void shouldReturnFavoriteById() {

        // Given
        Favorite favorite = new Favorite();

        favorite.setId(1L);
        favorite.setUserId(100L);
        favorite.setSongId(200L);

        when(favoriteRepository.findById(1L))
                .thenReturn(Optional.of(favorite));

        // When
        Favorite result =
                favoriteService.getFavoriteById(1L);

        // Then
        assertEquals(1L, result.getId());
        assertEquals(100L, result.getUserId());
        assertEquals(200L, result.getSongId());
    }

    @Test
    void shouldCreateFavorite() {

        // Given
        Favorite favorite = new Favorite();

        favorite.setUserId(100L);
        favorite.setSongId(200L);

        Favorite savedFavorite = new Favorite();

        savedFavorite.setId(1L);
        savedFavorite.setUserId(100L);
        savedFavorite.setSongId(200L);

        when(favoriteRepository.save(any(Favorite.class)))
                .thenReturn(savedFavorite);

        // When
        Favorite result =
                favoriteService.createFavorite(favorite);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(favoriteRepository, times(1))
                .save(any(Favorite.class));
    }

    @Test
    void shouldDeleteFavorite() {

        // Given
        Favorite favorite = new Favorite();

        favorite.setId(1L);

        when(favoriteRepository.findById(1L))
                .thenReturn(Optional.of(favorite));

        // When
        favoriteService.deleteFavorite(1L);

        // Then
        verify(favoriteRepository, times(1))
                .delete(favorite);
    }
}