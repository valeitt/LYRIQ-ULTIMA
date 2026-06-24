package cl.lyriq.catalog_service.controller;

import cl.lyriq.catalog_service.model.Genre;
import cl.lyriq.catalog_service.service.GenreService;

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

@WebMvcTest(GenreController.class)
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService genreService;

    @Test
    void shouldReturnAllGenres() throws Exception {

        // Given
        Genre genre = new Genre();

        genre.setId(1L);
        genre.setGenreName("Rock");
        genre.setTagColor("Red");

        when(genreService.getAllGenres())
                .thenReturn(List.of(genre));

        // When + Then
        mockMvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].genreName").value("Rock"));
    }

    @Test
    void shouldReturnGenreById() throws Exception {

        // Given
        Genre genre = new Genre();

        genre.setId(1L);
        genre.setGenreName("Rock");
        genre.setTagColor("Red");

        when(genreService.getGenreById(1L))
                .thenReturn(genre);

        // When + Then
        mockMvc.perform(get("/genres/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.genreName").value("Rock"));
    }

    @Test
    void shouldCreateGenre() throws Exception {

        // Given
        Genre genre = new Genre();

        genre.setId(1L);
        genre.setGenreName("Pop");
        genre.setTagColor("Blue");

        when(genreService.saveGenre(any()))
                .thenReturn(genre);

        String json = """
                {
                    "genreName":"Pop",
                    "tagColor":"Blue"
                }
                """;

        // When + Then
        mockMvc.perform(
                        post("/genres")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.genreName").value("Pop"));
    }
}