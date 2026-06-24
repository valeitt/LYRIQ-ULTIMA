package cl.lyriq.catalog_service.service;

import cl.lyriq.catalog_service.dto.GenreDTO;
import cl.lyriq.catalog_service.model.Genre;
import cl.lyriq.catalog_service.repository.GenreRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService;

    @Test
    void shouldReturnGenreById() {

        // Given
        Genre genre = new Genre();

        genre.setId(1L);
        genre.setGenreName("Rock");
        genre.setTagColor("Red");

        when(genreRepository.findById(1L))
                .thenReturn(Optional.of(genre));

        // When
        Genre result =
                genreService.getGenreById(1L);

        // Then
        assertEquals(1L, result.getId());
        assertEquals("Rock", result.getGenreName());
        assertEquals("Red", result.getTagColor());
    }

    @Test
    void shouldCreateGenre() {

        // Given
        GenreDTO dto = new GenreDTO();

        dto.setGenreName("Pop");
        dto.setTagColor("Blue");

        Genre savedGenre = new Genre();

        savedGenre.setId(1L);
        savedGenre.setGenreName("Pop");
        savedGenre.setTagColor("Blue");

        when(genreRepository.save(any(Genre.class)))
                .thenReturn(savedGenre);

        // When
        Genre result =
                genreService.saveGenre(dto);

        // Then
        assertNotNull(result);
        assertEquals("Pop", result.getGenreName());

        verify(genreRepository, times(1))
                .save(any(Genre.class));
    }

    @Test
    void shouldDeleteGenre() {

        // Given
        Genre genre = new Genre();

        genre.setId(1L);

        when(genreRepository.findById(1L))
                .thenReturn(Optional.of(genre));

        // When
        genreService.deleteGenre(1L);

        // Then
        verify(genreRepository, times(1))
                .delete(genre);
    }
}