package cl.lyriq.playlist_song_service.controller;

import cl.lyriq.playlist_song_service.dto.PlaylistSongDTO;
import cl.lyriq.playlist_song_service.model.PlaylistSong;
import cl.lyriq.playlist_song_service.service.PlaylistSongService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Playlist Songs",
        description = "Operaciones relacionadas con las canciones de las playlists"
)
@RestController
@RequestMapping("/playlist-songs")
public class PlaylistSongController {

    private final PlaylistSongService service;

    public PlaylistSongController(
            PlaylistSongService service) {

        this.service = service;
    }

    @Operation(
            summary = "Obtener todas las relaciones playlist-canción",
            description = "Retorna la lista completa de relaciones entre playlists y canciones"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de relaciones obtenida correctamente"
)
    @GetMapping
    public List<PlaylistSong> getAll() {
        return service.getAll();
    }

    @Operation(
            summary = "Buscar relación por ID",
            description = "Obtiene una relación playlist-canción mediante su ID"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Relación encontrada"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Relación no encontrada"
        )
})
    @GetMapping("/{id}")
    public PlaylistSong getById(
            @PathVariable Long id) {

        return service.getById(id);
    }


@Operation(
        summary = "Crear relación playlist-canción",
        description = "Asocia una canción a una playlist"
)
@ApiResponse(
        responseCode = "200",
        description = "Relación creada correctamente"
)

    @PostMapping
    public PlaylistSong create(
            @RequestBody PlaylistSongDTO dto) {

        return service.create(dto);
    }


    @Operation(
        summary = "Eliminar relación playlist-canción",
        description = "Elimina una relación entre una playlist y una canción"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Relación eliminada correctamente"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Relación no encontrada"
        )
})
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        service.delete(id);
    }


    @Operation(
        summary = "Obtener canciones por playlist",
        description = "Retorna todas las canciones asociadas a una playlist"
)
@ApiResponse(
        responseCode = "200",
        description = "Canciones de la playlist obtenidas correctamente"
)
    @GetMapping("/playlist/{playlistId}")
    public List<PlaylistSong> getByPlaylist(
            @PathVariable Long playlistId) {

        return service.getByPlaylist(
                playlistId);
    }
}