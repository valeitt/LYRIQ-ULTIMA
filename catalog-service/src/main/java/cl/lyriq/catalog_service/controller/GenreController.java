package cl.lyriq.catalog_service.controller;

import cl.lyriq.catalog_service.dto.GenreDTO;
import cl.lyriq.catalog_service.model.Genre;
import cl.lyriq.catalog_service.service.GenreService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable Long id) {
        return genreService.getGenreById(id);
    }

    @PostMapping
    public Genre createGenre(@RequestBody GenreDTO dto) {
        return genreService.saveGenre(dto);
    }

    @PutMapping("/{id}")
    public Genre updateGenre(@PathVariable Long id,
                             @RequestBody Genre genre) {

        return genreService.updateGenre(id, genre);
    }

    @DeleteMapping("/{id}")
    public String deleteGenre(@PathVariable Long id) {

        genreService.deleteGenre(id);

        return "Genre successfully removed :3";
    }
}