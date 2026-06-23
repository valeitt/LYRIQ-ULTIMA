package cl.lyriq.swipe_service.controller;

import cl.lyriq.swipe_service.dto.SwipeDTO;
import cl.lyriq.swipe_service.model.Swipe;
import cl.lyriq.swipe_service.model.SwipeAction;
import cl.lyriq.swipe_service.service.SwipeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@ApiResponse(
        responseCode = "200",
        description = "Lista de swipes obtenida correctamente"
)
    @GetMapping
    public List<Swipe> getAllSwipes() {
        return swipeService.getAllSwipes();
    }

 @Operation(
        summary = "Buscar swipe por ID",
        description = "Obtiene un swipe específico mediante su ID"
)

@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Swipe encontrado"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Swipe no encontrado"
        )
})
    @GetMapping("/{id}")
    public Swipe getSwipeById(
            @PathVariable Long id) {

        return swipeService.getSwipeById(id);
    }

    @Operation(
        summary = "Crear swipe",
        description = "Registra un nuevo swipe realizado por un usuario"
)

@ApiResponse(
        responseCode = "200",
        description = "Swipe creado correctamente"
)
    @PostMapping
    public Swipe createSwipe(
            @RequestBody SwipeDTO dto) {

        return swipeService.createSwipe(dto);
    }


    @Operation(
        summary = "Actualizar swipe",
        description = "Actualiza la información de un swipe existente"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Swipe actualizado correctamente"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Swipe no encontrado"
        )
})

    @PutMapping("/{id}")
    public Swipe updateSwipe(
            @PathVariable Long id,
            @RequestBody Swipe swipe) {

        return swipeService.updateSwipe(id, swipe);
    }

@Operation(
        summary = "Eliminar swipe",
        description = "Elimina un swipe según su ID"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Swipe eliminado correctamente"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Swipe no encontrado"
        )
})

    @DeleteMapping("/{id}")
    public String deleteSwipe(
            @PathVariable Long id) {

        swipeService.deleteSwipe(id);

        return "Swipe removed successfully :3";
    }


@Operation(
        summary = "Obtener swipes por usuario",
        description = "Retorna todos los swipes realizados por un usuario"
)
@ApiResponse(
        responseCode = "200",
        description = "Swipes encontrados correctamente"
)

    @GetMapping("/user/{userId}")
    public List<Swipe> getUserSwipes(
            @PathVariable Long userId) {

        return swipeService.getUserSwipes(userId);
    }

    
@Operation(
        summary = "Obtener swipes por acción",
        description = "Retorna los swipes filtrados segun el tipo de acción (LIKE o DISLIKE)"
)
@ApiResponse(
        responseCode = "200",
        description = "Swipes encontrados correctamente"
)


    @GetMapping("/action/{action}")
    public List<Swipe> getSwipesByAction(
            @PathVariable SwipeAction action) {

        return swipeService.getSwipesByAction(action);
    }
}