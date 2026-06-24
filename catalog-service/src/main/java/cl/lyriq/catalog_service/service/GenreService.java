package cl.lyriq.catalog_service.service;

import cl.lyriq.catalog_service.dto.GenreDTO;
import cl.lyriq.catalog_service.exception.BadRequestException;
import cl.lyriq.catalog_service.exception.ResourceNotFoundException;
import cl.lyriq.catalog_service.model.Genre;
import cl.lyriq.catalog_service.repository.GenreRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(
            GenreRepository genreRepository) {

        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id) {

        return genreRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Genre not found"));
    }

    public Genre saveGenre(
            GenreDTO dto) {

        if (dto.getGenreName() == null ||
                dto.getGenreName().trim().isEmpty()) {

            throw new BadRequestException(
                    "Genre name is required");
        }

        Genre genre = new Genre();

        genre.setGenreName(
                dto.getGenreName());

        genre.setTagColor(
                dto.getTagColor());

        return genreRepository.save(
                genre);
    }

    public Genre updateGenre(
            Long id,
            Genre updatedGenre) {

        Genre genre =
                genreRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Genre not found"));

        genre.setGenreName(
                updatedGenre.getGenreName());

        genre.setTagColor(
                updatedGenre.getTagColor());

        return genreRepository.save(
                genre);
    }

    public void deleteGenre(Long id) {

        Genre genre =
                genreRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Genre not found"));

        genreRepository.delete(
                genre);
    }
}