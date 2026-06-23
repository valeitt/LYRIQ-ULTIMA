package cl.lyriq.playlist_song_service.controller;

import cl.lyriq.playlist_song_service.dto.PlaylistSongDTO;
import cl.lyriq.playlist_song_service.model.PlaylistSong;
import cl.lyriq.playlist_song_service.service.PlaylistSongService;

import io.swagger.v3.oas.annotations.Operation;
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
    @GetMapping
    public List<PlaylistSong> getAll() {
        return service.getAll();
    }

    @Operation(
            summary = "Buscar relación por ID",
            description = "Obtiene una relación playlist-canción mediante su ID"
    )
    @GetMapping("/{id}")
    public PlaylistSong getById(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @PostMapping
    public PlaylistSong create(
            @RequestBody PlaylistSongDTO dto) {

        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        service.delete(id);
    }

    @GetMapping("/playlist/{playlistId}")
    public List<PlaylistSong> getByPlaylist(
            @PathVariable Long playlistId) {

        return service.getByPlaylist(
                playlistId);
    }
}