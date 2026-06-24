package cl.lyriq.artist_service.assemblers;

import cl.lyriq.artist_service.controller.ArtistController;
import cl.lyriq.artist_service.model.Artist;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ArtistModelAssemblers
        implements RepresentationModelAssembler<Artist, EntityModel<Artist>> {

    @Override
    public EntityModel<Artist> toModel(Artist artist) {

        return EntityModel.of(artist,

                linkTo(
                        methodOn(ArtistController.class)
                                .getById(artist.getId())
                ).withSelfRel(),

                linkTo(
                        methodOn(ArtistController.class)
                                .getAll()
                ).withRel("artists")
        );
    }
}