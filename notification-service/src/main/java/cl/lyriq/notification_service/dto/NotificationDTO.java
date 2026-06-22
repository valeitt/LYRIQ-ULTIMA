package cl.lyriq.notification_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificationDTO {

    private Long userId;

    @NotBlank
    private String message;
}