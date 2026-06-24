package cl.lyriq.catalog_service.controller;

import cl.lyriq.catalog_service.dto.GenreDTO;
import cl.lyriq.catalog_service.model.Genre;
import cl.lyriq.catalog_service.service.GenreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Genres",
        description = "Operaciones relacionadas con géneros musicales"
)
@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(
            GenreService genreService) {

        this.genreService = genreService;
    }

    @Operation(
            summary = "Obtener todos los géneros",
            description = "Retorna la lista completa de géneros musicales"
    )
    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @Operation(
            summary = "Buscar género por ID",
            description = "Obtiene un género específico mediante su ID"
    )
    @GetMapping("/{id}")
    public Genre getGenreById(
            @PathVariable Long id) {

        return genreService.getGenreById(id);
    }

    @PostMapping
    public Genre createGenre(
            @RequestBody GenreDTO dto) {

        return genreService.saveGenre(dto);
    }

    @PutMapping("/{id}")
    public Genre updateGenre(
            @PathVariable Long id,
            @RequestBody Genre genre) {

        return genreService.updateGenre(id, genre);
    }

    @DeleteMapping("/{id}")
    public String deleteGenre(
            @PathVariable Long id) {

        genreService.deleteGenre(id);

        return "Genre successfully removed :3";
    }
}