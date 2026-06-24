package cl.lyriq.catalog_service.service;

import cl.lyriq.catalog_service.dto.SongDTO;
import cl.lyriq.catalog_service.model.Genre;
import cl.lyriq.catalog_service.model.Song;
import cl.lyriq.catalog_service.repository.GenreRepository;
import cl.lyriq.catalog_service.repository.SongRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SongServiceTest {

    @Mock
    private SongRepository songRepository;

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private SongService songService;

    @Test
    void shouldReturnSongById() {

        // Given
        Song song = new Song();

        song.setId(1L);
        song.setTitle("Numb");

        when(songRepository.findById(1L))
                .thenReturn(Optional.of(song));

        // When
        Song result = songService.getSongById(1L);

        // Then
        assertEquals(1L, result.getId());
        assertEquals("Numb", result.getTitle());
    }

    @Test
    void shouldCreateSong() {

        // Given
        Genre genre = new Genre();

        genre.setId(1L);
        genre.setGenreName("Rock");

        SongDTO dto = new SongDTO();

        dto.setTitle("Numb");
        dto.setArtist("Linkin Park");
        dto.setAlbum("Meteora");
        dto.setImageUrl("image.jpg");
        dto.setGenreId(1L);

        Song savedSong = new Song();

        savedSong.setId(1L);
        savedSong.setTitle("Numb");
        savedSong.setGenre(genre);

        when(genreRepository.findById(1L))
                .thenReturn(Optional.of(genre));

        when(songRepository.save(any(Song.class)))
                .thenReturn(savedSong);

        // When
        Song result = songService.saveSong(dto);

        // Then
        assertNotNull(result);
        assertEquals("Numb", result.getTitle());

        verify(songRepository, times(1))
                .save(any(Song.class));
    }

    @Test
    void shouldDeleteSong() {

        // Given
        Song song = new Song();

        song.setId(1L);

        when(songRepository.findById(1L))
                .thenReturn(Optional.of(song));

        // When
        songService.deleteSong(1L);

        // Then
        verify(songRepository, times(1))
                .delete(song);
    }
}