package cl.lyriq.catalog_service.controller;

import cl.lyriq.catalog_service.dto.GenreDTO;
import cl.lyriq.catalog_service.model.Genre;
import cl.lyriq.catalog_service.service.GenreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    )@ApiResponse(
        responseCode = "200",
        description = "Géneros obtenidos correctamente"
)
    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @Operation(
            summary = "Buscar género por ID",
            description = "Obtiene un género específico mediante su ID"
    )@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Género encontrado"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Género no encontrado"
        )
})
    @GetMapping("/{id}")
    public Genre getGenreById(
            @PathVariable Long id) {

        return genreService.getGenreById(id);
    }


    @Operation(
        summary = "Crear género",
        description = "Registra un nuevo género musical"
)
@ApiResponse(
        responseCode = "200",
        description = "Género creado correctamente"
)
    @PostMapping
    public Genre createGenre(
            @RequestBody GenreDTO dto) {

        return genreService.saveGenre(dto);
    }


    @Operation(
        summary = "Actualizar género",
        description = "Actualiza la información de un género existente"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Género actualizado correctamente"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Género no encontrado"
        )
})
    @PutMapping("/{id}")
    public Genre updateGenre(
            @PathVariable Long id,
            @RequestBody Genre genre) {

        return genreService.updateGenre(id, genre);
    }

    @Operation(
        summary = "Eliminar género",
        description = "Elimina un género según su ID"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Género eliminado correctamente"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Género no encontrado"
        )
})

    @DeleteMapping("/{id}")
    public String deleteGenre(
            @PathVariable Long id) {

        genreService.deleteGenre(id);

        return "Genre successfully removed :3";
    }
}