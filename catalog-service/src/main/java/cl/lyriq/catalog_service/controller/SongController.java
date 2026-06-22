package cl.lyriq.catalog_service.controller;

import cl.lyriq.catalog_service.dto.SongDTO;
import cl.lyriq.catalog_service.model.Song;
import cl.lyriq.catalog_service.service.SongService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private static final Logger logger =
            LoggerFactory.getLogger(SongController.class);

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public List<Song> getAllSongs() {

        logger.info("Getting all songs");

        return songService.getAllSongs();
    }

    @GetMapping("/{id}")
    public Song getSongById(@PathVariable Long id) {

        logger.info("Getting song with ID {}", id);

        return songService.getSongById(id);
    }

    @PostMapping
    public Song createSong(@RequestBody SongDTO dto) {

        logger.info("Creating song: {}", dto.getTitle());

        return songService.saveSong(dto);
    }

    @PutMapping("/{id}")
    public Song updateSong(@PathVariable Long id,
                           @RequestBody Song song) {

        logger.info("Updating song with ID {}", id);

        return songService.updateSong(id, song);
    }

    @DeleteMapping("/{id}")
    public String deleteSong(@PathVariable Long id) {

        logger.info("Deleting song with ID {}", id);

        songService.deleteSong(id);

        return "Genre successfully removed :3";
    }
}