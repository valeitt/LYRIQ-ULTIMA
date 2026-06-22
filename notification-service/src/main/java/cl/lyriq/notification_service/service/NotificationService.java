package cl.lyriq.notification_service.service;

import cl.lyriq.notification_service.dto.NotificationDTO;
import cl.lyriq.notification_service.model.Notification;
import cl.lyriq.notification_service.repository.NotificationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private static final Logger logger =
            LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository repository;

    public NotificationService(
            NotificationRepository repository) {

        this.repository = repository;
    }

    public List<Notification> getAll() {

        logger.info("Getting all notifications");

        return repository.findAll();
    }

    public Notification getById(Long id) {

        logger.info("Getting notification with ID {}", id);

        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.error(
                            "Notification not found with ID {}",
                            id);
                    return new RuntimeException(
                            "Notification not found");
                });
    }

    public Notification create(
            NotificationDTO dto) {

        logger.info(
                "Creating notification for user {}",
                dto.getUserId());

        Notification notification =
                new Notification();

        notification.setUserId(dto.getUserId());
        notification.setMessage(dto.getMessage());
        notification.setCreatedAt(
                LocalDateTime.now());

        Notification savedNotification =
                repository.save(notification);

        logger.info(
                "Notification created successfully with ID {}",
                savedNotification.getId());

        return savedNotification;
    }

    public void delete(Long id) {

        logger.info(
                "Deleting notification with ID {}",
                id);

        Notification notification =
                repository.findById(id)
                        .orElseThrow(() -> {
                            logger.error(
                                    "Notification not found with ID {}",
                                    id);
                            return new RuntimeException(
                                    "Notification not found");
                        });

        repository.delete(notification);

        logger.info(
                "Notification deleted successfully with ID {}",
                id);
    }

    public List<Notification> getByUser(
            Long userId) {

        logger.info(
                "Getting notifications for user {}",
                userId);

        return repository.findByUserId(userId);
    }
}