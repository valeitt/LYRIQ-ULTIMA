package cl.lyriq.catalog_service.assemblers;

import cl.lyriq.catalog_service.controller.SongController;
import cl.lyriq.catalog_service.model.Song;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SongModelAssemblers
        implements RepresentationModelAssembler<Song, EntityModel<Song>> {

    @Override
    public EntityModel<Song> toModel(Song song) {

        return EntityModel.of(song,

                linkTo(
                        methodOn(SongController.class)
                                .getSongById(song.getId())
                ).withSelfRel(),

                linkTo(
                        methodOn(SongController.class)
                                .getAllSongs()
                ).withRel("songs")
        );
    }
}