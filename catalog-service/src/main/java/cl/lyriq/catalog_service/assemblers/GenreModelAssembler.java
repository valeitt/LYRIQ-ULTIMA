package cl.lyriq.catalog_service.assemblers;

import cl.lyriq.catalog_service.controller.GenreController;
import cl.lyriq.catalog_service.model.Genre;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class GenreModelAssembler
        implements RepresentationModelAssembler<Genre, EntityModel<Genre>> {

    @Override
    public EntityModel<Genre> toModel(Genre genre) {

        return EntityModel.of(genre,

                linkTo(
                        methodOn(GenreController.class)
                                .getGenreById(genre.getId())
                ).withSelfRel(),

                linkTo(
                        methodOn(GenreController.class)
                                .getAllGenres()
                ).withRel("genres")
        );
    }
}