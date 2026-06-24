package cl.lyriq.favorites_service.controller;

import cl.lyriq.favorites_service.model.Favorite;
import cl.lyriq.favorites_service.service.FavoriteService;

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

@WebMvcTest(FavoriteController.class)
class FavoriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FavoriteService favoriteService;

    @Test
    void shouldReturnAllFavorites() throws Exception {

        // Given
        Favorite favorite = new Favorite();

        favorite.setId(1L);
        favorite.setUserId(100L);
        favorite.setSongId(200L);

        when(favoriteService.getAllFavorites())
                .thenReturn(List.of(favorite));

        // When + Then
        mockMvc.perform(get("/favorites"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].userId").value(100));
    }

    @Test
    void shouldReturnFavoriteById() throws Exception {

        // Given
        Favorite favorite = new Favorite();

        favorite.setId(1L);
        favorite.setUserId(100L);
        favorite.setSongId(200L);

        when(favoriteService.getFavoriteById(1L))
                .thenReturn(favorite);

        // When + Then
        mockMvc.perform(get("/favorites/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userId").value(100));
    }

    @Test
    void shouldCreateFavorite() throws Exception {

        // Given
        Favorite favorite = new Favorite();

        favorite.setId(1L);
        favorite.setUserId(100L);
        favorite.setSongId(200L);

        when(favoriteService.createFavorite(any()))
                .thenReturn(favorite);

        String json = """
                {
                    "userId":100,
                    "songId":200
                }
                """;

        // When + Then
        mockMvc.perform(
                        post("/favorites")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}