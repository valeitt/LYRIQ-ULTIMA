package cl.lyriq.history_service.controller;

import cl.lyriq.history_service.dto.HistoryDTO;
import cl.lyriq.history_service.model.History;
import cl.lyriq.history_service.service.HistoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "History",
        description = "Operaciones relacionadas con el historial de reproducciones"
)
@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(
            HistoryService historyService) {

        this.historyService = historyService;
    }

    @Operation(
            summary = "Obtener todo el historial",
            description = "Retorna la lista completa del historial de reproducciones"
    )@ApiResponse(
        responseCode = "200",
        description = "Historial obtenido correctamente"

)
    @GetMapping
    public List<History> getAllHistory() {
        return historyService.getAllHistory();
    }

    @Operation(
            summary = "Obtener historial por usuario",
            description = "Retorna el historial de reproducciones de un usuario específico"
    )@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Historial encontrado correctamente"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Usuario no encontrado"
        )
})
    @GetMapping("/user/{userId}")
    public List<History> getHistoryByUser(
            @PathVariable Long userId) {

        return historyService.getHistoryByUser(userId);
    }

    @Operation(
        summary = "Registrar reproducción",
        description = "Guarda una nueva reproducción en el historial del usuario"
)
@ApiResponse(
        responseCode = "200",
        description = "Reproducción registrada correctamente"
)
    @PostMapping
    public History createHistory(
            @RequestBody HistoryDTO dto) {

        return historyService.saveHistory(dto);
    }
}