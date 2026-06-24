package cl.lyriq.swipe_service.assemblers;

import cl.lyriq.swipe_service.controller.SwipeController;
import cl.lyriq.swipe_service.model.Swipe;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SwipeModelAssemblers
        implements RepresentationModelAssembler<Swipe, EntityModel<Swipe>> {

    @Override
    public EntityModel<Swipe> toModel(Swipe swipe) {

        return EntityModel.of(swipe,

                linkTo(
                        methodOn(SwipeController.class)
                                .getSwipeById(swipe.getId())
                ).withSelfRel(),

                linkTo(
                        methodOn(SwipeController.class)
                                .getAllSwipes()
                ).withRel("swipes")
        );
    }
}