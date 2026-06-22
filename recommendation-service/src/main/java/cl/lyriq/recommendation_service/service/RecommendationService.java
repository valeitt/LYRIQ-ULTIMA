package cl.lyriq.recommendation_service.service;

import cl.lyriq.recommendation_service.dto.RecommendationDTO;
import cl.lyriq.recommendation_service.exception.BadRequestException;
import cl.lyriq.recommendation_service.exception.ResourceNotFoundException;
import cl.lyriq.recommendation_service.model.Recommendation;
import cl.lyriq.recommendation_service.repository.RecommendationRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    private final RecommendationRepository repository;

    public RecommendationService(
            RecommendationRepository repository) {

        this.repository = repository;
    }

    public List<Recommendation> getAll() {
        return repository.findAll();
    }

    public Recommendation getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Recomendación no encontrada"));
    }

    public Recommendation create(
            RecommendationDTO dto) {

        if (dto.getUserId() == null) {
            throw new BadRequestException(
                    "El userId es obligatorio");
        }

        if (dto.getSongId() == null) {
            throw new BadRequestException(
                    "El songId es obligatorio");
        }

        if (dto.getScore() == null) {
            throw new BadRequestException(
                    "El score es obligatorio");
        }

        Recommendation recommendation =
                new Recommendation();

        recommendation.setUserId(
                dto.getUserId());

        recommendation.setSongId(
                dto.getSongId());

        recommendation.setScore(
                dto.getScore());

        return repository.save(
                recommendation);
    }

    public void delete(Long id) {

        Recommendation recommendation =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Recomendación no encontrada"));

        repository.delete(recommendation);
    }

    public List<Recommendation> getByUser(
            Long userId) {

        return repository.findByUserId(userId);
    }
}