package cl.lyriq.recommendation_service.dto;

import lombok.Data;

@Data
public class RecommendationDTO {

    private Long userId;

    private Long songId;

    private Double score;
}