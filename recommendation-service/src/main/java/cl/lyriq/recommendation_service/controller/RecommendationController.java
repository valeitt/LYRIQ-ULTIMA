package cl.lyriq.recommendation_service.controller;

import cl.lyriq.recommendation_service.dto.RecommendationDTO;
import cl.lyriq.recommendation_service.model.Recommendation;
import cl.lyriq.recommendation_service.service.RecommendationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Recommendations",
        description = "Operaciones relacionadas con recomendaciones musicales"
)
@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService service;

    public RecommendationController(
            RecommendationService service) {

        this.service = service;
    }

    @Operation(
            summary = "Obtener todas las recomendaciones",
            description = "Retorna la lista completa de recomendaciones"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de recomendaciones obtenida correctamente"
)
    @GetMapping
    public List<Recommendation> getAll() {
        return service.getAll();
    }

    @Operation(
            summary = "Buscar recomendación por ID",
            description = "Obtiene una recomendación específica mediante su ID"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Recomendación encontrada"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Recomendación no encontrada"
        )
})
    @GetMapping("/{id}")
    public Recommendation getById(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @Operation(
        summary = "Crear recomendación",
        description = "Registra una nueva recomendación musical"
)
@ApiResponse(
        responseCode = "200",
        description = "Recomendación creada correctamente"
)
    @PostMapping
    public Recommendation create(
            @RequestBody RecommendationDTO dto) {

        return service.create(dto);
    }

    @Operation(
        summary = "Eliminar recomendación",
        description = "Elimina una recomendación según su ID"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Recomendación eliminada correctamente"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Recomendación no encontrada"
        )
})
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        service.delete(id);
    }

@Operation(
        summary = "Obtener recomendaciones por usuario",
        description = "Retorna todas las recomendaciones asociadas a un usuario"
)
@ApiResponse(
        responseCode = "200",
        description = "Recomendaciones encontradas correctamente"
)
    @GetMapping("/user/{userId}")
    public List<Recommendation> getByUser(
            @PathVariable Long userId) {

        return service.getByUser(userId);
    }
}