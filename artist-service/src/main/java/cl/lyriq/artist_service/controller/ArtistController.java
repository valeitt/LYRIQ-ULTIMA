package cl.lyriq.artist_service.controller;

import cl.lyriq.artist_service.dto.ArtistDTO;
import cl.lyriq.artist_service.model.Artist;
import cl.lyriq.artist_service.service.ArtistService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService service;

    public ArtistController(
            ArtistService service) {

        this.service = service;
    }

    @GetMapping
    public List<Artist> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Artist getById(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @PostMapping
    public Artist create(
            @Valid @RequestBody ArtistDTO dto) {

        return service.create(dto);
    }

    @PutMapping("/{id}")
    public Artist update(
            @PathVariable Long id,
            @RequestBody Artist artist) {

        return service.update(id, artist);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        service.delete(id);
    }
}