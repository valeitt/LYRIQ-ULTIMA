package cl.lyriq.recommendation_service.service;

import cl.lyriq.recommendation_service.dto.RecommendationDTO;
import cl.lyriq.recommendation_service.model.Recommendation;
import cl.lyriq.recommendation_service.repository.RecommendationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    private static final Logger logger =
            LoggerFactory.getLogger(RecommendationService.class);

    private final RecommendationRepository repository;

    public RecommendationService(
            RecommendationRepository repository) {

        this.repository = repository;
    }

    public List<Recommendation> getAll() {

        logger.info("Getting all recommendations");

        return repository.findAll();
    }

    public Recommendation getById(Long id) {

        logger.info(
                "Getting recommendation with ID {}",
                id);

        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.error(
                            "Recommendation not found with ID {}",
                            id);
                    return new RuntimeException(
                            "Recommendation not found");
                });
    }

    public Recommendation create(
            RecommendationDTO dto) {

        logger.info(
                "Creating recommendation for user {} and song {}",
                dto.getUserId(),
                dto.getSongId());

        Recommendation recommendation =
                new Recommendation();

        recommendation.setUserId(
                dto.getUserId());

        recommendation.setSongId(
                dto.getSongId());

        recommendation.setScore(
                dto.getScore());

        Recommendation savedRecommendation =
                repository.save(
                        recommendation);

        logger.info(
                "Recommendation created successfully with ID {}",
                savedRecommendation.getId());

        return savedRecommendation;
    }

    public void delete(Long id) {

        logger.info(
                "Deleting recommendation with ID {}",
                id);

        Recommendation recommendation =
                repository.findById(id)
                        .orElseThrow(() -> {
                            logger.error(
                                    "Recommendation not found with ID {}",
                                    id);
                            return new RuntimeException(
                                    "Recommendation not found");
                        });

        repository.delete(recommendation);

        logger.info(
                "Recommendation deleted successfully with ID {}",
                id);
    }

    public List<Recommendation> getByUser(
            Long userId) {

        logger.info(
                "Getting recommendations for user {}",
                userId);

        return repository.findByUserId(userId);
    }
}