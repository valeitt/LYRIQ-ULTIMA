package cl.lyriq.playlist_service.controller;

import cl.lyriq.playlist_service.assemblers.PlaylistModelAssemblers;
import cl.lyriq.playlist_service.model.Playlist;
import cl.lyriq.playlist_service.service.PlaylistService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/playlists/v2")
public class PlaylistControllerV2 {

    private final PlaylistService playlistService;
    private final PlaylistModelAssemblers assembler;

    public PlaylistControllerV2(
            PlaylistService playlistService,
            PlaylistModelAssemblers assembler) {

        this.playlistService = playlistService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Playlist>> getAll() {

        List<EntityModel<Playlist>> playlists =
                playlistService.getAllPlaylists()
                        .stream()
                        .map(assembler::toModel)
                        .toList();

        return CollectionModel.of(
                playlists,
                linkTo(
                        methodOn(PlaylistControllerV2.class)
                                .getAll()
                ).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<Playlist> getById(
            @PathVariable Long id) {

        return assembler.toModel(
                playlistService.getPlaylistById(id)
        );
    }
}