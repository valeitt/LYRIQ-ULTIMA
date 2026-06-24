package cl.lyriq.playlist_service.controller;

import cl.lyriq.playlist_service.model.Playlist;
import cl.lyriq.playlist_service.service.PlaylistService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlaylistController.class)
class PlaylistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaylistService playlistService;

    @Test
    void shouldReturnAllPlaylists() throws Exception {

        // Given
        Playlist playlist = new Playlist();

        playlist.setId(1L);
        playlist.setUserId(100L);
        playlist.setName("Rock");
        playlist.setDescription("Best songs");

        when(playlistService.getAllPlaylists())
                .thenReturn(List.of(playlist));

        // When + Then
        mockMvc.perform(get("/playlists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Rock"));
    }

    @Test
    void shouldReturnPlaylistById() throws Exception {

        // Given
        Playlist playlist = new Playlist();

        playlist.setId(1L);
        playlist.setUserId(100L);
        playlist.setName("Rock");
        playlist.setDescription("Best songs");

        when(playlistService.getPlaylistById(1L))
                .thenReturn(playlist);

        // When + Then
        mockMvc.perform(get("/playlists/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Rock"));
    }

    @Test
    void shouldCreatePlaylist() throws Exception {

        // Given
        Playlist playlist = new Playlist();

        playlist.setId(1L);
        playlist.setUserId(100L);
        playlist.setName("Rock");
        playlist.setDescription("Best songs");

        when(playlistService.createPlaylist(any()))
                .thenReturn(playlist);

        String json = """
                {
                    "userId":100,
                    "name":"Rock",
                    "description":"Best songs"
                }
                """;

        // When + Then
        mockMvc.perform(
                        post("/playlists")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Rock"));
    }
}