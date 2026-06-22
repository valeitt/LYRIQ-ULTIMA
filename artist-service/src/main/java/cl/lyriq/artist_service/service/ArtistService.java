package cl.lyriq.artist_service.service;

import cl.lyriq.artist_service.dto.ArtistDTO;
import cl.lyriq.artist_service.model.Artist;
import cl.lyriq.artist_service.repository.ArtistRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private static final Logger logger =
            LoggerFactory.getLogger(ArtistService.class);

    private final ArtistRepository repository;

    public ArtistService(
            ArtistRepository repository) {

        this.repository = repository;
    }

    public List<Artist> getAll() {

        logger.info("Getting all artists");

        return repository.findAll();
    }

    public Artist getById(Long id) {

        logger.info("Getting artist with ID {}", id);

        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Artist not found with ID {}", id);
                    return new RuntimeException(
                            "Artist not found");
                });
    }

    public Artist create(
            ArtistDTO dto) {

        logger.info("Creating artist: {}", dto.getName());

        Artist artist = new Artist();

        artist.setName(dto.getName());
        artist.setCountry(dto.getCountry());

        Artist savedArtist = repository.save(artist);

        logger.info("Artist created successfully with ID {}",
                savedArtist.getId());

        return savedArtist;
    }

    public Artist update(
            Long id,
            Artist updatedArtist) {

        logger.info("Updating artist with ID {}", id);

        Artist artist =
                repository.findById(id)
                        .orElseThrow(() -> {
                            logger.error(
                                    "Artist not found with ID {}",
                                    id);
                            return new RuntimeException(
                                    "Artist not found");
                        });

        artist.setName(updatedArtist.getName());
        artist.setCountry(updatedArtist.getCountry());

        Artist savedArtist = repository.save(artist);

        logger.info("Artist updated successfully with ID {}",
                id);

        return savedArtist;
    }

    public void delete(Long id) {

        logger.info("Deleting artist with ID {}", id);

        Artist artist =
                repository.findById(id)
                        .orElseThrow(() -> {
                            logger.error(
                                    "Artist not found with ID {}",
                                    id);
                            return new RuntimeException(
                                    "Artist not found");
                        });

        repository.delete(artist);

        logger.info("Artist deleted successfully with ID {}",
                id);
    }
}