package cl.lyriq.favorites_service.assemblers;

import cl.lyriq.favorites_service.controller.FavoriteController;
import cl.lyriq.favorites_service.model.Favorite;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class FavoritesModelAssemblers
        implements RepresentationModelAssembler<Favorite, EntityModel<Favorite>> {

    @Override
    public EntityModel<Favorite> toModel(Favorite favorite) {

        return EntityModel.of(favorite,

                linkTo(
                        methodOn(FavoriteController.class)
                                .getFavoriteById(favorite.getId())
                ).withSelfRel(),

                linkTo(
                        methodOn(FavoriteController.class)
                                .getAllFavorites()
                ).withRel("favorites")
        );
    }
}