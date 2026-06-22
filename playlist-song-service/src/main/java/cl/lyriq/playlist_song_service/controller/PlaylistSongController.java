package cl.lyriq.playlist_song_service.controller;

import cl.lyriq.playlist_song_service.dto.PlaylistSongDTO;
import cl.lyriq.playlist_song_service.model.PlaylistSong;
import cl.lyriq.playlist_song_service.service.PlaylistSongService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist-songs")
public class PlaylistSongController {

    private static final Logger logger =
            LoggerFactory.getLogger(PlaylistSongController.class);

    private final PlaylistSongService service;

    public PlaylistSongController(
            PlaylistSongService service) {

        this.service = service;
    }

    @GetMapping
    public List<PlaylistSong> getAll() {

        logger.info("Getting all playlist-song relations");

        return service.getAll();
    }

    @GetMapping("/{id}")
    public PlaylistSong getById(
            @PathVariable Long id) {

        logger.info("Getting playlist-song relation with ID {}",
                id);

        return service.getById(id);
    }

    @PostMapping
    public PlaylistSong create(
            @RequestBody PlaylistSongDTO dto) {

        logger.info(
                "Creating playlist-song relation. Playlist ID: {}, Song ID: {}",
                dto.getPlaylistId(),
                dto.getSongId());

        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        logger.info(
                "Deleting playlist-song relation with ID {}",
                id);

        service.delete(id);
    }

    @GetMapping("/playlist/{playlistId}")
    public List<PlaylistSong> getByPlaylist(
            @PathVariable Long playlistId) {

        logger.info(
                "Getting songs for playlist ID {}",
                playlistId);

        return service.getByPlaylist(
                playlistId);
    }
}