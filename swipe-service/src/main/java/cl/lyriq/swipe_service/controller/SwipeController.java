package cl.lyriq.swipe_service.controller;

import cl.lyriq.swipe_service.dto.SwipeDTO;
import cl.lyriq.swipe_service.model.Swipe;
import cl.lyriq.swipe_service.model.SwipeAction;
import cl.lyriq.swipe_service.service.SwipeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Swipes",
        description = "Operaciones relacionadas con los swipes"
)
@RestController
@RequestMapping("/swipes")
public class SwipeController {

    private final SwipeService swipeService;

    public SwipeController(
            SwipeService swipeService) {

        this.swipeService = swipeService;
    }

    @Operation(
            summary = "Obtener todos los swipes",
            description = "Retorna la lista completa de swipes registrados"
    )
    @GetMapping
    public List<Swipe> getAllSwipes() {
        return swipeService.getAllSwipes();
    }

    @Operation(
            summary = "Buscar swipe por ID",
            description = "Obtiene un swipe específico mediante su ID"
    )
    @GetMapping("/{id}")
    public Swipe getSwipeById(
            @PathVariable Long id) {

        return swipeService.getSwipeById(id);
    }

    @PostMapping
    public Swipe createSwipe(
            @RequestBody SwipeDTO dto) {

        return swipeService.createSwipe(dto);
    }

    @PutMapping("/{id}")
    public Swipe updateSwipe(
            @PathVariable Long id,
            @RequestBody Swipe swipe) {

        return swipeService.updateSwipe(id, swipe);
    }

    @DeleteMapping("/{id}")
    public String deleteSwipe(
            @PathVariable Long id) {

        swipeService.deleteSwipe(id);

        return "Swipe removed successfully :3";
    }

    @GetMapping("/user/{userId}")
    public List<Swipe> getUserSwipes(
            @PathVariable Long userId) {

        return swipeService.getUserSwipes(userId);
    }

    @GetMapping("/action/{action}")
    public List<Swipe> getSwipesByAction(
            @PathVariable SwipeAction action) {

        return swipeService.getSwipesByAction(action);
    }
}