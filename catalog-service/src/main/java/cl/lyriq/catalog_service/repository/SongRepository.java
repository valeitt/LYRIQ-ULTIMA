package cl.lyriq.catalog_service.repository;

import cl.lyriq.catalog_service.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    // Búsqueda de canciones por nombre de artista (para artist-service)
    List<Song> findByArtistContainingIgnoreCase(String artist);
}