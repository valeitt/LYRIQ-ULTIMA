package cl.lyriq.recommendation_service.controller;

import cl.lyriq.recommendation_service.dto.RecommendationDTO;
import cl.lyriq.recommendation_service.model.Recommendation;
import cl.lyriq.recommendation_service.service.RecommendationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private static final Logger logger =
            LoggerFactory.getLogger(RecommendationController.class);

    private final RecommendationService service;

    public RecommendationController(
            RecommendationService service) {

        this.service = service;
    }

    @GetMapping
    public List<Recommendation> getAll() {

        logger.info("Getting all recommendations");

        return service.getAll();
    }

    @GetMapping("/{id}")
    public Recommendation getById(
            @PathVariable Long id) {

        logger.info(
                "Getting recommendation with ID {}",
                id);

        return service.getById(id);
    }

    @PostMapping
    public Recommendation create(
            @RequestBody RecommendationDTO dto) {

        logger.info(
                "Creating recommendation for user {} and song {}",
                dto.getUserId(),
                dto.getSongId());

        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        logger.info(
                "Deleting recommendation with ID {}",
                id);

        service.delete(id);
    }

    @GetMapping("/user/{userId}")
    public List<Recommendation> getByUser(
            @PathVariable Long userId) {

        logger.info(
                "Getting recommendations for user {}",
                userId);

        return service.getByUser(userId);
    }
}