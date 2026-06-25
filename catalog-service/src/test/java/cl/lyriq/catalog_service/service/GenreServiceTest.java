package cl.lyriq.catalog_service.service;

import cl.lyriq.catalog_service.dto.GenreDTO;
import cl.lyriq.catalog_service.exception.BadRequestException;
import cl.lyriq.catalog_service.exception.ResourceNotFoundException;
import cl.lyriq.catalog_service.model.Genre;
import cl.lyriq.catalog_service.repository.GenreRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("GenreService Unit Tests")
class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService;

    private Genre genre;
    private GenreDTO genreDTO;

    @BeforeEach
    void setUp() {
        genre = new Genre();
        genre.setId(1L);
        genre.setGenreName("Rock");
        genre.setTagColor("#FF0000");

        genreDTO = new GenreDTO();
        genreDTO.setGenreName("Rock");
        genreDTO.setTagColor("#FF0000");
    }

    @Test
    @DisplayName("Given genres exist, When getAllGenres, Then returns list")
    void getAllGenres_ReturnsList() {
        // Given
        when(genreRepository.findAll()).thenReturn(List.of(genre));
        // When
        List<Genre> result = genreService.getAllGenres();
        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getGenreName()).isEqualTo("Rock");
    }

    @Test
    @DisplayName("Given genre exists, When getGenreById, Then returns genre")
    void getGenreById_WhenExists_ReturnsGenre() {
        // Given
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        // When
        Genre result = genreService.getGenreById(1L);
        // Then
        assertThat(result.getGenreName()).isEqualTo("Rock");
    }

    @Test
    @DisplayName("Given genre not found, When getGenreById, Then throws ResourceNotFoundException")
    void getGenreById_WhenNotFound_ThrowsException() {
        // Given
        when(genreRepository.findById(99L)).thenReturn(Optional.empty());
        // When / Then
        assertThatThrownBy(() -> genreService.getGenreById(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Given valid DTO, When saveGenre, Then saves genre")
    void saveGenre_ValidDTO_ReturnsSavedGenre() {
        // Given
        when(genreRepository.save(any(Genre.class))).thenReturn(genre);
        // When
        Genre result = genreService.saveGenre(genreDTO);
        // Then
        assertThat(result.getGenreName()).isEqualTo("Rock");
    }

    @Test
    @DisplayName("Given null name, When saveGenre, Then throws BadRequestException")
    void saveGenre_NullName_ThrowsBadRequest() {
        // Given
        genreDTO.setGenreName(null);
        // When / Then
        assertThatThrownBy(() -> genreService.saveGenre(genreDTO))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    @DisplayName("Given empty name, When saveGenre, Then throws BadRequestException")
    void saveGenre_EmptyName_ThrowsBadRequest() {
        // Given
        genreDTO.setGenreName("  ");
        // When / Then
        assertThatThrownBy(() -> genreService.saveGenre(genreDTO))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    @DisplayName("Given existing genre, When updateGenre, Then updates and returns")
    void updateGenre_WhenExists_ReturnsUpdated() {
        // Given
        Genre updated = new Genre();
        updated.setGenreName("Pop");
        updated.setTagColor("#00FF00");
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(genreRepository.save(any(Genre.class))).thenReturn(updated);
        // When
        Genre result = genreService.updateGenre(1L, updated);
        // Then
        assertThat(result.getGenreName()).isEqualTo("Pop");
    }

    @Test
    @DisplayName("Given genre not found, When updateGenre, Then throws ResourceNotFoundException")
    void updateGenre_WhenNotFound_ThrowsException() {
        // Given
        when(genreRepository.findById(99L)).thenReturn(Optional.empty());
        // When / Then
        assertThatThrownBy(() -> genreService.updateGenre(99L, genre))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Given existing genre, When deleteGenre, Then deletes")
    void deleteGenre_WhenExists_Deletes() {
        // Given
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        // When
        genreService.deleteGenre(1L);
        // Then
        verify(genreRepository).delete(genre);
    }

    @Test
    @DisplayName("Given genre not found, When deleteGenre, Then throws ResourceNotFoundException")
    void deleteGenre_WhenNotFound_ThrowsException() {
        // Given
        when(genreRepository.findById(99L)).thenReturn(Optional.empty());
        // When / Then
        assertThatThrownBy(() -> genreService.deleteGenre(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}