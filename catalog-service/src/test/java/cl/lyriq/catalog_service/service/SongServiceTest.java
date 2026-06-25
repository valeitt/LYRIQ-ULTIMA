package cl.lyriq.catalog_service.service;

import cl.lyriq.catalog_service.dto.SongDTO;
import cl.lyriq.catalog_service.exception.BadRequestException;
import cl.lyriq.catalog_service.exception.ResourceNotFoundException;
import cl.lyriq.catalog_service.model.Genre;
import cl.lyriq.catalog_service.model.Song;
import cl.lyriq.catalog_service.repository.GenreRepository;
import cl.lyriq.catalog_service.repository.SongRepository;

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
@DisplayName("SongService Unit Tests")
class SongServiceTest {

    @Mock
    private SongRepository songRepository;

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private SongService songService;

    private Song song;
    private Genre genre;
    private SongDTO songDTO;

    @BeforeEach
    void setUp() {
        genre = new Genre();
        genre.setId(1L);
        genre.setGenreName("Rock");

        song = new Song();
        song.setId(1L);
        song.setTitle("Numb");
        song.setArtist("Linkin Park");
        song.setAlbum("Meteora");
        song.setImageUrl("http://img.url");
        song.setGenre(genre);

        songDTO = new SongDTO();
        songDTO.setTitle("Numb");
        songDTO.setArtist("Linkin Park");
        songDTO.setAlbum("Meteora");
        songDTO.setImageUrl("http://img.url");
        songDTO.setGenreId(1L);
    }

    // ── GET ALL ────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Given songs exist, When getAllSongs, Then returns list")
    void getAllSongs_ReturnsList() {
        // Given
        when(songRepository.findAll()).thenReturn(List.of(song));

        // When
        List<Song> result = songService.getAllSongs();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Numb");
        verify(songRepository).findAll();
    }

    @Test
    @DisplayName("Given no songs, When getAllSongs, Then returns empty list")
    void getAllSongs_ReturnsEmptyList() {
        // Given
        when(songRepository.findAll()).thenReturn(List.of());

        // When
        List<Song> result = songService.getAllSongs();

        // Then
        assertThat(result).isEmpty();
    }

    // ── GET BY ID ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Given song exists, When getSongById, Then returns song")
    void shouldReturnSongById() {
        // Given
        when(songRepository.findById(1L)).thenReturn(Optional.of(song));

        // When
        Song result = songService.getSongById(1L);

        // Then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Numb");
    }

    @Test
    @DisplayName("Given song not found, When getSongById, Then throws ResourceNotFoundException")
    void getSongById_WhenNotFound_ThrowsException() {
        // Given
        when(songRepository.findById(99L)).thenReturn(Optional.empty());

        // When / Then
        assertThatThrownBy(() -> songService.getSongById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Song not found");
    }

    // ── CREATE ─────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Given valid DTO, When saveSong, Then saves and returns song")
    void shouldCreateSong() {
        // Given
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(songRepository.save(any(Song.class))).thenReturn(song);

        // When
        Song result = songService.saveSong(songDTO);

        // Then
        assertThat(result.getTitle()).isEqualTo("Numb");
        verify(songRepository).save(any(Song.class));
    }

    @Test
    @DisplayName("Given null title, When saveSong, Then throws BadRequestException")
    void saveSong_NullTitle_ThrowsBadRequest() {
        // Given
        songDTO.setTitle(null);

        // When / Then
        assertThatThrownBy(() -> songService.saveSong(songDTO))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Song title is required");
    }

    @Test
    @DisplayName("Given empty title, When saveSong, Then throws BadRequestException")
    void saveSong_EmptyTitle_ThrowsBadRequest() {
        // Given
        songDTO.setTitle("   ");

        // When / Then
        assertThatThrownBy(() -> songService.saveSong(songDTO))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    @DisplayName("Given null artist, When saveSong, Then throws BadRequestException")
    void saveSong_NullArtist_ThrowsBadRequest() {
        // Given
        songDTO.setArtist(null);

        // When / Then
        assertThatThrownBy(() -> songService.saveSong(songDTO))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Artist is required");
    }

    @Test
    @DisplayName("Given null genreId, When saveSong, Then throws BadRequestException")
    void saveSong_NullGenreId_ThrowsBadRequest() {
        // Given
        songDTO.setGenreId(null);

        // When / Then
        assertThatThrownBy(() -> songService.saveSong(songDTO))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Genre ID is required");
    }

    @Test
    @DisplayName("Given invalid genreId, When saveSong, Then throws ResourceNotFoundException")
    void saveSong_GenreNotFound_ThrowsException() {
        // Given
        when(genreRepository.findById(99L)).thenReturn(Optional.empty());
        songDTO.setGenreId(99L);

        // When / Then
        assertThatThrownBy(() -> songService.saveSong(songDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Genre not found");
    }

    // ── UPDATE ─────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Given existing song, When updateSong, Then updates and returns")
    void updateSong_WhenExists_ReturnsUpdated() {
        // Given
        Song updated = new Song();
        updated.setTitle("Somewhere I Belong");
        updated.setArtist("Linkin Park");
        updated.setAlbum("Meteora");
        updated.setImageUrl("http://new.url");

        when(songRepository.findById(1L)).thenReturn(Optional.of(song));
        when(songRepository.save(any(Song.class))).thenReturn(updated);

        // When
        Song result = songService.updateSong(1L, updated);

        // Then
        assertThat(result.getTitle()).isEqualTo("Somewhere I Belong");
        verify(songRepository).save(any(Song.class));
    }

    @Test
    @DisplayName("Given song not found, When updateSong, Then throws ResourceNotFoundException")
    void updateSong_WhenNotFound_ThrowsException() {
        // Given
        when(songRepository.findById(99L)).thenReturn(Optional.empty());

        // When / Then
        assertThatThrownBy(() -> songService.updateSong(99L, song))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    // ── DELETE ─────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Given existing song, When deleteSong, Then deletes successfully")
    void shouldDeleteSong() {
        // Given
        when(songRepository.findById(1L)).thenReturn(Optional.of(song));

        // When
        songService.deleteSong(1L);

        // Then
        verify(songRepository).delete(song);
    }

    @Test
    @DisplayName("Given song not found, When deleteSong, Then throws ResourceNotFoundException")
    void deleteSong_WhenNotFound_ThrowsException() {
        // Given
        when(songRepository.findById(99L)).thenReturn(Optional.empty());

        // When / Then
        assertThatThrownBy(() -> songService.deleteSong(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    // ── GET BY ARTIST ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("Given artist name, When getSongsByArtist, Then returns filtered songs")
    void getSongsByArtist_ValidName_ReturnsSongs() {
        // Given
        when(songRepository.findByArtistContainingIgnoreCase("Linkin Park"))
                .thenReturn(List.of(song));

        // When
        List<Song> result = songService.getSongsByArtist("Linkin Park");

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getArtist()).isEqualTo("Linkin Park");
    }

    @Test
    @DisplayName("Given null artist, When getSongsByArtist, Then throws BadRequestException")
    void getSongsByArtist_NullName_ThrowsBadRequest() {
        // When / Then
        assertThatThrownBy(() -> songService.getSongsByArtist(null))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    @DisplayName("Given empty artist, When getSongsByArtist, Then throws BadRequestException")
    void getSongsByArtist_EmptyName_ThrowsBadRequest() {
        // When / Then
        assertThatThrownBy(() -> songService.getSongsByArtist("  "))
                .isInstanceOf(BadRequestException.class);
    }
}