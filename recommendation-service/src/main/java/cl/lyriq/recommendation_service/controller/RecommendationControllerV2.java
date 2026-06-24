package cl.lyriq.recommendation_service.controller;

import cl.lyriq.recommendation_service.assemblers.RecommendationModelAssemblers;
import cl.lyriq.recommendation_service.model.Recommendation;
import cl.lyriq.recommendation_service.service.RecommendationService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/recommendations/v2")
public class RecommendationControllerV2 {

private final RecommendationService service;
private final RecommendationModelAssemblers assembler;

public RecommendationControllerV2(
        RecommendationService service,
        RecommendationModelAssemblers assembler) {

    this.service = service;
    this.assembler = assembler;
}

@GetMapping
public CollectionModel<EntityModel<Recommendation>> getAll() {

    List<EntityModel<Recommendation>> recommendations =
            service.getAll()
                    .stream()
                    .map(assembler::toModel)
                    .toList();

    return CollectionModel.of(
            recommendations,
            linkTo(
                    methodOn(RecommendationControllerV2.class)
                            .getAll()
            ).withSelfRel()
    );
}

@GetMapping("/{id}")
public EntityModel<Recommendation> getById(
        @PathVariable Long id) {

    return assembler.toModel(
            service.getById(id)
    );
}

}
