package cl.lyriq.favorites_service.controller;

import cl.lyriq.favorites_service.assemblers.FavoritesModelAssemblers;
import cl.lyriq.favorites_service.model.Favorite;
import cl.lyriq.favorites_service.service.FavoriteService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/favorites/v2")
public class FavoriteControllerV2 {

    private final FavoriteService favoriteService;
    private final FavoritesModelAssemblers assembler;

    public FavoriteControllerV2(
            FavoriteService favoriteService,
            FavoritesModelAssemblers assembler) {

        this.favoriteService = favoriteService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Favorite>> getAllFavorites() {

        List<EntityModel<Favorite>> favorites =
                favoriteService.getAllFavorites()
                        .stream()
                        .map(assembler::toModel)
                        .toList();

        return CollectionModel.of(
                favorites,
                linkTo(
                        methodOn(FavoriteControllerV2.class)
                                .getAllFavorites()
                ).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<Favorite> getFavoriteById(
            @PathVariable Long id) {

        return assembler.toModel(
                favoriteService.getFavoriteById(id)
        );
    }
}