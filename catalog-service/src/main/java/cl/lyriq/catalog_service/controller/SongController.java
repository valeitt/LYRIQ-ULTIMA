package cl.lyriq.catalog_service.controller;

import cl.lyriq.catalog_service.dto.SongDTO;
import cl.lyriq.catalog_service.model.Song;
import cl.lyriq.catalog_service.service.SongService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Songs",
        description = "Operaciones relacionadas con canciones"
)
@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    public SongController(
            SongService songService) {

        this.songService = songService;
    }

    @Operation(
            summary = "Obtener todas las canciones",
            description = "Retorna la lista completa de canciones"
    )
    @GetMapping
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    @Operation(
            summary = "Buscar canción por ID",
            description = "Obtiene una canción específica mediante su ID"
    )
    @GetMapping("/{id}")
    public Song getSongById(
            @PathVariable Long id) {

        return songService.getSongById(id);
    }

    @PostMapping
    public Song createSong(
            @RequestBody SongDTO dto) {

        return songService.saveSong(dto);
    }

    @PutMapping("/{id}")
    public Song updateSong(
            @PathVariable Long id,
            @RequestBody Song song) {

        return songService.updateSong(id, song);
    }

    @DeleteMapping("/{id}")
    public String deleteSong(
            @PathVariable Long id) {

        songService.deleteSong(id);

        return "Song successfully removed :3";
    }
}