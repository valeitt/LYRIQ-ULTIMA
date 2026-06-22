package cl.lyriq.notification_service.controller;

import cl.lyriq.notification_service.dto.NotificationDTO;
import cl.lyriq.notification_service.model.Notification;
import cl.lyriq.notification_service.service.NotificationService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private static final Logger logger =
            LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService service;

    public NotificationController(
            NotificationService service) {

        this.service = service;
    }

    @GetMapping
    public List<Notification> getAll() {

        logger.info("Getting all notifications");

        return service.getAll();
    }

    @GetMapping("/{id}")
    public Notification getById(
            @PathVariable Long id) {

        logger.info("Getting notification with ID {}", id);

        return service.getById(id);
    }

    @PostMapping
    public Notification create(
            @Valid @RequestBody NotificationDTO dto) {

        logger.info("Creating notification for user {}",
                dto.getUserId());

        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        logger.info("Deleting notification with ID {}", id);

        service.delete(id);
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getByUser(
            @PathVariable Long userId) {

        logger.info("Getting notifications for user {}",
                userId);

        return service.getByUser(userId);
    }
}