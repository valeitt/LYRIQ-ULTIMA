package cl.lyriq.notification_service.controller;

import cl.lyriq.notification_service.dto.NotificationDTO;
import cl.lyriq.notification_service.model.Notification;
import cl.lyriq.notification_service.service.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
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
    )
    @GetMapping
    public List<Notification> getAll() {
        return service.getAll();
    }

    @Operation(
            summary = "Buscar notificación por ID",
            description = "Obtiene una notificación específica mediante su ID"
    )
    @GetMapping("/{id}")
    public Notification getById(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @PostMapping
    public Notification create(
            @Valid @RequestBody NotificationDTO dto) {

        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        service.delete(id);
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getByUser(
            @PathVariable Long userId) {

        return service.getByUser(userId);
    }
}