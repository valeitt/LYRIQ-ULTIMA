package cl.lyriq.recommendation_service.controller;

import cl.lyriq.recommendation_service.dto.RecommendationDTO;
import cl.lyriq.recommendation_service.model.Recommendation;
import cl.lyriq.recommendation_service.service.RecommendationService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService service;

    public RecommendationController(
            RecommendationService service) {

        this.service = service;
    }

    @GetMapping
    public List<Recommendation> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Recommendation getById(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @PostMapping
    public Recommendation create(
            @RequestBody RecommendationDTO dto) {

        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        service.delete(id);
    }

    @GetMapping("/user/{userId}")
    public List<Recommendation> getByUser(
            @PathVariable Long userId) {

        return service.getByUser(userId);
    }
}