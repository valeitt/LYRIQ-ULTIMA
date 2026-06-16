package cl.lyriq.catalog_service.repository;

import cl.lyriq.catalog_service.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}