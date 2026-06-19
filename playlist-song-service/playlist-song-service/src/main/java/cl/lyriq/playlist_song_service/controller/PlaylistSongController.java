package cl.lyriq.playlist_song_service.controller;

import cl.lyriq.playlist_song_service.dto.PlaylistSongDTO;
import cl.lyriq.playlist_song_service.model.PlaylistSong;
import cl.lyriq.playlist_song_service.service.PlaylistSongService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist-songs")
public class PlaylistSongController {

    private final PlaylistSongService service;

    public PlaylistSongController(
            PlaylistSongService service) {

        this.service = service;
    }

    @GetMapping
    public List<PlaylistSong> getAll() {
        return service.getAll();
    }

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