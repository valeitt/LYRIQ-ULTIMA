package cl.lyriq.artist_service.controller;

import cl.lyriq.artist_service.dto.ArtistDTO;
import cl.lyriq.artist_service.model.Artist;
import cl.lyriq.artist_service.service.ArtistService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Artists",
        description = "Operaciones relacionadas con artistas musicales"
)
@RestController
@RequestMapping("/artists")
public class ArtistController {

    private static final Logger logger =
            LoggerFactory.getLogger(ArtistController.class);

    private final ArtistService service;

    public ArtistController(
            ArtistService service) {

        this.service = service;
    }

    @Operation(
            summary = "Obtener todos los artistas",
            description = "Retorna la lista completa de artistas"
    )
    @GetMapping
    public List<Artist> getAll() {

        logger.info("Getting all artists");

        return service.getAll();
    }

    @Operation(
            summary = "Buscar artista por ID",
            description = "Obtiene un artista específico mediante su ID"
    )
    @GetMapping("/{id}")
    public Artist getById(
            @PathVariable Long id) {

        logger.info("Getting artist with ID {}", id);

        return service.getById(id);
    }

    @PostMapping
    public Artist create(
            @Valid @RequestBody ArtistDTO dto) {

        logger.info("Creating artist: {}", dto.getName());

        return service.create(dto);
    }

    @PutMapping("/{id}")
    public Artist update(
            @PathVariable Long id,
            @RequestBody Artist artist) {

        logger.info("Updating artist with ID {}", id);

        return service.update(id, artist);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        logger.info("Deleting artist with ID {}", id);

        service.delete(id);
    }
}