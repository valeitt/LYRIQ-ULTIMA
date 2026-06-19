package cl.lyriq.recommendation_service.repository;

import cl.lyriq.recommendation_service.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository
        extends JpaRepository<Recommendation, Long> {

    List<Recommendation> findByUserId(Long userId);
}