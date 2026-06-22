package cl.lyriq.catalog_service.controller;

import cl.lyriq.catalog_service.dto.GenreDTO;
import cl.lyriq.catalog_service.model.Genre;
import cl.lyriq.catalog_service.service.GenreService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private static final Logger logger =
            LoggerFactory.getLogger(GenreController.class);

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> getAllGenres() {

        logger.info("Getting all genres");

        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable Long id) {

        logger.info("Getting genre with ID {}", id);

        return genreService.getGenreById(id);
    }

    @PostMapping
    public Genre createGenre(@RequestBody GenreDTO dto) {

        logger.info("Creating genre: {}", dto.getGenreName());

        return genreService.saveGenre(dto);
    }

    @PutMapping("/{id}")
    public Genre updateGenre(@PathVariable Long id,
                             @RequestBody Genre genre) {

        logger.info("Updating genre with ID {}", id);

        return genreService.updateGenre(id, genre);
    }

    @DeleteMapping("/{id}")
    public String deleteGenre(@PathVariable Long id) {

        logger.info("Deleting genre with ID {}", id);

        genreService.deleteGenre(id);

        return "Genre successfully removed :3";
    }
}