package cl.lyriq.playlist_service.controller;

import cl.lyriq.playlist_service.dto.PlaylistDTO;
import cl.lyriq.playlist_service.model.Playlist;
import cl.lyriq.playlist_service.service.PlaylistService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    )@ApiResponse(
        responseCode = "200",
        description = "Lista de playlists obtenida correctamente"
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
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Playlist encontrada"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Playlist no encontrada"
        )
})
    @GetMapping("/{id}")
    public Playlist getById(
            @PathVariable Long id) {

        logger.info("Getting playlist with ID {}", id);

        return playlistService.getPlaylistById(id);
    }

    @Operation(
        summary = "Crear playlist",
        description = "Registra una nueva playlist musical"
)
@ApiResponse(
        responseCode = "200",
        description = "Playlist creada correctamente"
)
    @PostMapping
    public Playlist create(
            @Valid @RequestBody PlaylistDTO dto) {

        logger.info("Creating playlist: {}",
                dto.getName());

        return playlistService.createPlaylist(dto);
    }

    @Operation(
        summary = "Actualizar playlist",
        description = "Actualiza la información de una playlist existente"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Playlist actualizada correctamente"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Playlist no encontrada"
        )
})
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

@Operation(
        summary = "Eliminar playlist",
        description = "Elimina una playlist según su ID"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Playlist eliminada correctamente"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Playlist no encontrada"
        )
})
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        logger.info("Deleting playlist with ID {}",
                id);

        playlistService.deletePlaylist(id);
    }
}