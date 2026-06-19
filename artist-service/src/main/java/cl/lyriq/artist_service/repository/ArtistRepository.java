package cl.lyriq.artist_service.repository;

import cl.lyriq.artist_service.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository
        extends JpaRepository<Artist, Long> {
}