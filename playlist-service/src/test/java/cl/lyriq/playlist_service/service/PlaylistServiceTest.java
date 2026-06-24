package cl.lyriq.playlist_service.service;

import cl.lyriq.playlist_service.dto.PlaylistDTO;
import cl.lyriq.playlist_service.model.Playlist;
import cl.lyriq.playlist_service.repository.PlaylistRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaylistServiceTest {

    @Mock
    private PlaylistRepository playlistRepository;

    @InjectMocks
    private PlaylistService playlistService;

    @Test
    void shouldReturnPlaylistById() {

        // Given
        Playlist playlist = new Playlist();

        playlist.setId(1L);
        playlist.setUserId(100L);
        playlist.setName("Rock");
        playlist.setDescription("Best songs");

        when(playlistRepository.findById(1L))
                .thenReturn(Optional.of(playlist));

        // When
        Playlist result = playlistService.getPlaylistById(1L);

        // Then
        assertEquals(1L, result.getId());
        assertEquals("Rock", result.getName());
    }

    @Test
    void shouldCreatePlaylist() {

        // Given
        PlaylistDTO dto = new PlaylistDTO();

        dto.setUserId(100L);
        dto.setName("Rock");
        dto.setDescription("Best songs");

        Playlist playlist = new Playlist();

        playlist.setId(1L);
        playlist.setUserId(100L);
        playlist.setName("Rock");
        playlist.setDescription("Best songs");

        when(playlistRepository.save(any(Playlist.class)))
                .thenReturn(playlist);

        // When
        Playlist result = playlistService.createPlaylist(dto);

        // Then
        assertNotNull(result);
        assertEquals("Rock", result.getName());

        verify(playlistRepository, times(1))
                .save(any(Playlist.class));
    }

    @Test
    void shouldDeletePlaylist() {

        // Given
        Playlist playlist = new Playlist();

        playlist.setId(1L);

        when(playlistRepository.findById(1L))
                .thenReturn(Optional.of(playlist));

        // When
        playlistService.deletePlaylist(1L);

        // Then
        verify(playlistRepository, times(1))
                .delete(playlist);
    }
}