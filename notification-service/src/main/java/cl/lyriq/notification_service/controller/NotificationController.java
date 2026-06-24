package cl.lyriq.notification_service.controller;

import cl.lyriq.notification_service.dto.NotificationDTO;
import cl.lyriq.notification_service.model.Notification;
import cl.lyriq.notification_service.service.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Notifications",
        description = "Operaciones relacionadas con notificaciones de usuarios"
)
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(
            NotificationService service) {

        this.service = service;
    }

    @Operation(
            summary = "Obtener todas las notificaciones",
            description = "Retorna la lista completa de notificaciones"
    )@ApiResponse(
        responseCode = "200",
        description = "Lista de notificaciones obtenida correctamente"
)
    @GetMapping
    public List<Notification> getAll() {
        return service.getAll();
    }

    @Operation(
            summary = "Buscar notificación por ID",
            description = "Obtiene una notificación específica mediante su ID"
    )@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Notificación encontrada"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Notificación no encontrada"
    
            )
})

    @GetMapping("/{id}")
    public Notification getById(
            @PathVariable Long id) {

        return service.getById(id);
    }


    @Operation(
        summary = "Crear notificación",
        description = "Registra una nueva notificación para un usuario"
)
@ApiResponse(
        responseCode = "200",
        description = "Notificación creada correctamente"
)
    @PostMapping
    public Notification create(
            @Valid @RequestBody NotificationDTO dto) {

        return service.create(dto);
    }

    @Operation(
        summary = "Eliminar notificación",
        description = "Elimina una notificación según su ID"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Notificación eliminada correctamente"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Notificación no encontrada"
        )
})
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        service.delete(id);
    }


    @Operation(
        summary = "Obtener notificaciones por usuario",
        description = "Retorna todas las notificaciones asociadas a un usuario"
)
@ApiResponse(
        responseCode = "200",
        description = "Notificaciones encontradas correctamente"
)
    @GetMapping("/user/{userId}")
    public List<Notification> getByUser(
            @PathVariable Long userId) {

        return service.getByUser(userId);
    }
}