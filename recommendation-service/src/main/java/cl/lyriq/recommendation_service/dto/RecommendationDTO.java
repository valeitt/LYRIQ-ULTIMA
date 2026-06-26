package cl.lyriq.recommendation_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class RecommendationDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

    @NotNull(message = "El ID de la canción es obligatorio")
    private Long songId;

    @NotNull(message = "El puntaje de recomendación es obligatorio")
    @PositiveOrZero(message = "El puntaje debe ser mayor o igual a 0")
    private Double score;
}