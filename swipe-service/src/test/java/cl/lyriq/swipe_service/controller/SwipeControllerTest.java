package cl.lyriq.swipe_service.controller;

import cl.lyriq.swipe_service.model.Swipe;
import cl.lyriq.swipe_service.model.SwipeAction;
import cl.lyriq.swipe_service.service.SwipeService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SwipeController.class)
class SwipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SwipeService swipeService;

    @Test
    void shouldReturnAllSwipes() throws Exception {

        // Given
        Swipe swipe = new Swipe();

        swipe.setId(1L);
        swipe.setUserId(100L);
        swipe.setSongId(200L);
        swipe.setAction(SwipeAction.LIKE);

        when(swipeService.getAllSwipes())
                .thenReturn(List.of(swipe));

        // When y Then
        mockMvc.perform(get("/swipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].userId").value(100))
                .andExpect(jsonPath("$[0].songId").value(200));
    }

    @Test
    void shouldReturnSwipeById() throws Exception {

        // Given
        Swipe swipe = new Swipe();

        swipe.setId(1L);
        swipe.setUserId(100L);
        swipe.setSongId(200L);
        swipe.setAction(SwipeAction.LIKE);

        when(swipeService.getSwipeById(1L))
                .thenReturn(swipe);

        // When y Then
        mockMvc.perform(get("/swipes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userId").value(100));
    }

    @Test
    void shouldCreateSwipe() throws Exception {

        // Given
        Swipe swipe = new Swipe();

        swipe.setId(1L);
        swipe.setUserId(100L);
        swipe.setSongId(200L);
        swipe.setAction(SwipeAction.LIKE);

        when(swipeService.createSwipe(org.mockito.ArgumentMatchers.any()))
                .thenReturn(swipe);

        String json = """
                {
                    "userId": 100,
                    "songId": 200,
                    "action": "LIKE"
                }
                """;

        // When y Then
        mockMvc.perform(
                        post("/swipes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}