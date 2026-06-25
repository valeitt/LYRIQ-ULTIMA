package cl.lyriq.catalog_service.controller;

import cl.lyriq.catalog_service.dto.SongDTO;
import cl.lyriq.catalog_service.model.Song;
import cl.lyriq.catalog_service.service.SongService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    )@ApiResponse(
        responseCode = "200",
        description = "Canciones obtenidas correctamente"
)
    @GetMapping
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    @Operation(
            summary = "Buscar canciones por artista",
            description = "Retorna canciones filtradas por nombre de artista (usado internamente por artist-service)"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Canciones encontradas"),
        @ApiResponse(responseCode = "400", description = "Nombre de artista requerido")
    })
    @GetMapping("/by-artist")
    public List<Song> getSongsByArtist(
            @org.springframework.web.bind.annotation.RequestParam String artist) {

        return songService.getSongsByArtist(artist);
    }

    @Operation(
            summary = "Buscar canción por ID",
            description = "Obtiene una canción específica mediante su ID"
    )@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Canción encontrada"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Canción no encontrada"
        )
})
    @GetMapping("/{id}")
    public Song getSongById(
            @PathVariable Long id) {

        return songService.getSongById(id);
    }

    @Operation(
        summary = "Crear canción",
        description = "Registra una nueva canción en el catálogo"
)
@ApiResponse(
        responseCode = "200",
        description = "Canción creada correctamente"
)
    @PostMapping
    public Song createSong(
            @RequestBody SongDTO dto) {

        return songService.saveSong(dto);
    }

    @Operation(
        summary = "Actualizar canción",
        description = "Actualiza la información de una canción existente"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Canción actualizada correctamente"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Canción no encontrada"
        )
})
    @PutMapping("/{id}")
    public Song updateSong(
            @PathVariable Long id,
            @RequestBody Song song) {

        return songService.updateSong(id, song);
    }

    @Operation(
        summary = "Eliminar canción",
        description = "Elimina una canción según su ID"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Canción eliminada correctamente"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Canción no encontrada"
        )
})
    @DeleteMapping("/{id}")
    public String deleteSong(
            @PathVariable Long id) {

        songService.deleteSong(id);

        return "Song successfully removed :3";
    }
}