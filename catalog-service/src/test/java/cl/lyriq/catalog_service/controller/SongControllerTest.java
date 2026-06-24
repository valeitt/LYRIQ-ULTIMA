package cl.lyriq.catalog_service.controller;

import cl.lyriq.catalog_service.model.Song;
import cl.lyriq.catalog_service.service.SongService;

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

@WebMvcTest(SongController.class)
class SongControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongService songService;

    @Test
    void shouldReturnAllSongs() throws Exception {

        // Given
        Song song = new Song();

        song.setId(1L);
        song.setTitle("Numb");

        when(songService.getAllSongs())
                .thenReturn(List.of(song));

        // When + Then
        mockMvc.perform(get("/songs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Numb"));
    }

    @Test
    void shouldReturnSongById() throws Exception {

        // Given
        Song song = new Song();

        song.setId(1L);
        song.setTitle("Numb");

        when(songService.getSongById(1L))
                .thenReturn(song);

        // When + Then
        mockMvc.perform(get("/songs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Numb"));
    }

    @Test
    void shouldCreateSong() throws Exception {

        // Given
        Song song = new Song();

        song.setId(1L);
        song.setTitle("Numb");

        when(songService.saveSong(any()))
                .thenReturn(song);

        String json = """
                {
                    "title":"Numb",
                    "artist":"Linkin Park",
                    "album":"Meteora",
                    "imageUrl":"image.jpg",
                    "genreId":1
                }
                """;

        // When + Then
        mockMvc.perform(
                        post("/songs")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Numb"));
    }
}