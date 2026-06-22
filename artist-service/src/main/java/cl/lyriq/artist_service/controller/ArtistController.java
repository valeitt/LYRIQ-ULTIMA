package cl.lyriq.artist_service.controller;

import cl.lyriq.artist_service.dto.ArtistDTO;
import cl.lyriq.artist_service.model.Artist;
import cl.lyriq.artist_service.service.ArtistService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Artist> getAll() {

        logger.info("Getting all artists");

        return service.getAll();
    }

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