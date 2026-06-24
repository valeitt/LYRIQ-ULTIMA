package cl.lyriq.playlist_service.assemblers;

import cl.lyriq.playlist_service.controller.PlaylistController;
import cl.lyriq.playlist_service.model.Playlist;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PlaylistModelAssemblers
        implements RepresentationModelAssembler<Playlist, EntityModel<Playlist>> {

    @Override
    public EntityModel<Playlist> toModel(Playlist playlist) {

        return EntityModel.of(playlist,

                linkTo(
                        methodOn(PlaylistController.class)
                                .getById(playlist.getId())
                ).withSelfRel(),

                linkTo(
                        methodOn(PlaylistController.class)
                                .getAll()
                ).withRel("playlists")
        );
    }
}