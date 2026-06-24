package cl.lyriq.playlist_song_service.controller;

import cl.lyriq.playlist_song_service.assemblers.PlaylistSongModelAssemblers;
import cl.lyriq.playlist_song_service.model.PlaylistSong;
import cl.lyriq.playlist_song_service.service.PlaylistSongService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/playlist-songs/v2")
public class PlaylistSongControllerV2 {

    private final PlaylistSongService service;
    private final PlaylistSongModelAssemblers assembler;

    public PlaylistSongControllerV2(
            PlaylistSongService service,
            PlaylistSongModelAssemblers assembler) {

        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<PlaylistSong>> getAll() {

        List<EntityModel<PlaylistSong>> playlistSongs =
                service.getAll()
                        .stream()
                        .map(assembler::toModel)
                        .toList();

        return CollectionModel.of(
                playlistSongs,
                linkTo(
                        methodOn(PlaylistSongControllerV2.class)
                                .getAll()
                ).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<PlaylistSong> getById(
            @PathVariable Long id) {

        return assembler.toModel(
                service.getById(id)
        );
    }
}