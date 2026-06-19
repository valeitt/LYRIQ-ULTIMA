package cl.lyriq.recommendation_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long songId;

    private Double score;
}