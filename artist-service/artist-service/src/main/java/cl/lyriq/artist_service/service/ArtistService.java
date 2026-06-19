package cl.lyriq.artist_service.service;

import cl.lyriq.artist_service.dto.ArtistDTO;
import cl.lyriq.artist_service.model.Artist;
import cl.lyriq.artist_service.repository.ArtistRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository repository;

    public ArtistService(
            ArtistRepository repository) {

        this.repository = repository;
    }

    public List<Artist> getAll() {
        return repository.findAll();
    }

    public Artist getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Artista no encontrado"));
    }

    public Artist create(
            ArtistDTO dto) {

        Artist artist = new Artist();

        artist.setName(dto.getName());
        artist.setCountry(dto.getCountry());

        return repository.save(artist);
    }

    public Artist update(
            Long id,
            Artist updatedArtist) {

        Artist artist =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Artista no encontrado"));

        artist.setName(updatedArtist.getName());
        artist.setCountry(updatedArtist.getCountry());

        return repository.save(artist);
    }

    public void delete(Long id) {

        Artist artist =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Artista no encontrado"));

        repository.delete(artist);
    }
}