package cl.lyriq.playlist_song_service.assemblers;

import cl.lyriq.playlist_song_service.controller.PlaylistSongController;
import cl.lyriq.playlist_song_service.model.PlaylistSong;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PlaylistSongModelAssemblers
        implements RepresentationModelAssembler<PlaylistSong, EntityModel<PlaylistSong>> {

    @Override
    public EntityModel<PlaylistSong> toModel(PlaylistSong playlistSong) {

        return EntityModel.of(playlistSong,

                linkTo(
                        methodOn(PlaylistSongController.class)
                                .getById(playlistSong.getId())
                ).withSelfRel(),

                linkTo(
                        methodOn(PlaylistSongController.class)
                                .getAll()
                ).withRel("playlist-songs")
        );
    }
}