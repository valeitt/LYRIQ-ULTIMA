package cl.lyriq.history_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HistoryDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

    @NotNull(message = "El ID de la canción es obligatorio")
    private Long songId;
}