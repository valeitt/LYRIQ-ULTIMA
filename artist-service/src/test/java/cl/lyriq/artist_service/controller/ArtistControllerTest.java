package cl.lyriq.artist_service.controller;

import cl.lyriq.artist_service.model.Artist;
import cl.lyriq.artist_service.service.ArtistService;

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

@WebMvcTest(ArtistController.class)
class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistService service;

    @Test
    void shouldReturnAllArtists() throws Exception {

        // Given
        Artist artist = new Artist();

        artist.setId(1L);
        artist.setName("Linkin Park");
        artist.setCountry("USA");

        when(service.getAll())
                .thenReturn(List.of(artist));

        // When + Then
        mockMvc.perform(get("/artists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Linkin Park"))
                .andExpect(jsonPath("$[0].country").value("USA"));
    }

    @Test
    void shouldReturnArtistById() throws Exception {

        // Given
        Artist artist = new Artist();

        artist.setId(1L);
        artist.setName("Linkin Park");
        artist.setCountry("USA");

        when(service.getById(1L))
                .thenReturn(artist);

        // When + Then
        mockMvc.perform(get("/artists/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Linkin Park"));
    }

    @Test
    void shouldCreateArtist() throws Exception {

        // Given
        Artist artist = new Artist();

        artist.setId(1L);
        artist.setName("Metallica");
        artist.setCountry("USA");

        when(service.create(any()))
                .thenReturn(artist);

        String json = """
                {
                    "name":"Metallica",
                    "country":"USA"
                }
                """;

        // When + Then
        mockMvc.perform(
                        post("/artists")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Metallica"));
    }
}