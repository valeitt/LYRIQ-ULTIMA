package cl.lyriq.notification_service.service;

import cl.lyriq.notification_service.dto.NotificationDTO;
import cl.lyriq.notification_service.exception.BadRequestException;
import cl.lyriq.notification_service.exception.ResourceNotFoundException;
import cl.lyriq.notification_service.model.Notification;
import cl.lyriq.notification_service.repository.NotificationRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(
            NotificationRepository repository) {

        this.repository = repository;
    }

    public List<Notification> getAll() {
        return repository.findAll();
    }

    public Notification getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Notificación no encontrada"));
    }

    public Notification create(
            NotificationDTO dto) {

        if (dto.getUserId() == null) {
            throw new BadRequestException(
                    "El userId es obligatorio");
        }

        if (dto.getMessage() == null ||
                dto.getMessage().trim().isEmpty()) {

            throw new BadRequestException(
                    "El mensaje es obligatorio");
        }

        Notification notification =
                new Notification();

        notification.setUserId(
                dto.getUserId());

        notification.setMessage(
                dto.getMessage());

        notification.setCreatedAt(
                LocalDateTime.now());

        return repository.save(
                notification);
    }

    public void delete(Long id) {

        Notification notification =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notificación no encontrada"));

        repository.delete(notification);
    }

    public List<Notification> getByUser(
            Long userId) {

        return repository.findByUserId(userId);
    }
}