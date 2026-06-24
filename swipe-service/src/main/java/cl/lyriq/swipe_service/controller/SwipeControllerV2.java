package cl.lyriq.swipe_service.controller;

import cl.lyriq.swipe_service.assemblers.SwipeModelAssemblers;
import cl.lyriq.swipe_service.model.Swipe;
import cl.lyriq.swipe_service.service.SwipeService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/swipes/v2")
public class SwipeControllerV2 {

    private final SwipeService swipeService;
    private final SwipeModelAssemblers assembler;

    public SwipeControllerV2(
            SwipeService swipeService,
            SwipeModelAssemblers assembler) {

        this.swipeService = swipeService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Swipe>> getAllSwipes() {

        List<EntityModel<Swipe>> swipes =
                swipeService.getAllSwipes()
                        .stream()
                        .map(assembler::toModel)
                        .toList();

        return CollectionModel.of(
                swipes,
                linkTo(
                        methodOn(SwipeControllerV2.class)
                                .getAllSwipes()
                ).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<Swipe> getSwipeById(
            @PathVariable Long id) {

        return assembler.toModel(
                swipeService.getSwipeById(id)
        );
    }
}