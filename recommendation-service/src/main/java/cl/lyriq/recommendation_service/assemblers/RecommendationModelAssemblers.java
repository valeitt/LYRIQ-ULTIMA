package cl.lyriq.recommendation_service.assemblers;

import cl.lyriq.recommendation_service.controller.RecommendationController;
import cl.lyriq.recommendation_service.model.Recommendation;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class RecommendationModelAssemblers
        implements RepresentationModelAssembler<Recommendation, EntityModel<Recommendation>> {

    @Override
    public EntityModel<Recommendation> toModel(Recommendation recommendation) {

        return EntityModel.of(recommendation,

                linkTo(
                        methodOn(RecommendationController.class)
                                .getById(recommendation.getId())
                ).withSelfRel(),

                linkTo(
                        methodOn(RecommendationController.class)
                                .getAll()
                ).withRel("recommendations")
        );
    }
}