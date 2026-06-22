package cl.lyriq.catalog_service.service;

import cl.lyriq.catalog_service.dto.GenreDTO;
import cl.lyriq.catalog_service.model.Genre;
import cl.lyriq.catalog_service.repository.GenreRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private static final Logger logger =
            LoggerFactory.getLogger(GenreService.class);

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {

        logger.info("Getting all genres");

        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id) {

        logger.info("Getting genre with ID {}", id);

        return genreRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Genre not found with ID {}", id);
                    return new RuntimeException("Genre not found T.T");
                });
    }

    public Genre saveGenre(GenreDTO dto) {

        logger.info("Creating genre: {}", dto.getGenreName());

        Genre genre = new Genre();

        genre.setGenreName(dto.getGenreName());
        genre.setTagColor(dto.getTagColor());

        Genre savedGenre = genreRepository.save(genre);

        logger.info("Genre created successfully with ID {}",
                savedGenre.getId());

        return savedGenre;
    }

    public Genre updateGenre(Long id, Genre updatedGenre) {

        logger.info("Updating genre with ID {}", id);

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Genre not found with ID {}", id);
                    return new RuntimeException("Genre not found T.T");
                });

        genre.setGenreName(updatedGenre.getGenreName());
        genre.setTagColor(updatedGenre.getTagColor());

        Genre savedGenre = genreRepository.save(genre);

        logger.info("Genre updated successfully with ID {}", id);

        return savedGenre;
    }

    public void deleteGenre(Long id) {

        logger.info("Deleting genre with ID {}", id);

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Genre not found with ID {}", id);
                    return new RuntimeException("Genre not found T.T");
                });

        genreRepository.delete(genre);

        logger.info("Genre deleted successfully with ID {}", id);
    }
}