package cl.lyriq.swipe_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SwipeDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

    @NotNull(message = "El ID de la canción es obligatorio")
    private Long songId;

    @NotBlank(message = "La acción es obligatoria")
    private String action;
}