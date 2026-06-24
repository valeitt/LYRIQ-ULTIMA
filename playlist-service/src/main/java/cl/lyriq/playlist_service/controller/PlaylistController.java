package cl.lyriq.playlist_service.controller;

import cl.lyriq.playlist_service.dto.PlaylistDTO;
import cl.lyriq.playlist_service.model.Playlist;
import cl.lyriq.playlist_service.service.PlaylistService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Playlists",
        description = "Operaciones relacionadas con playlists musicales"
)
@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private static final Logger logger =
            LoggerFactory.getLogger(PlaylistController.class);

    private final PlaylistService playlistService;

    public PlaylistController(
            PlaylistService playlistService) {

        this.playlistService = playlistService;
    }

    @Operation(
            summary = "Obtener todas las playlists",
            description = "Retorna la lista completa de playlists"
    )
    @GetMapping
    public List<Playlist> getAll() {

        logger.info("Getting all playlists");

        return playlistService.getAllPlaylists();
    }

    @Operation(
            summary = "Buscar playlist por ID",
            description = "Obtiene una playlist específica mediante su ID"
    )
    @GetMapping("/{id}")
    public Playlist getById(
            @PathVariable Long id) {

        logger.info("Getting playlist with ID {}", id);

        return playlistService.getPlaylistById(id);
    }

    @PostMapping
    public Playlist create(
            @Valid @RequestBody PlaylistDTO dto) {

        logger.info("Creating playlist: {}",
                dto.getName());

        return playlistService.createPlaylist(dto);
    }

    @PutMapping("/{id}")
    public Playlist update(
            @PathVariable Long id,
            @RequestBody Playlist playlist) {

        logger.info("Updating playlist with ID {}",
                id);

        return playlistService.updatePlaylist(
                id,
                playlist);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        logger.info("Deleting playlist with ID {}",
                id);

        playlistService.deletePlaylist(id);
    }
}