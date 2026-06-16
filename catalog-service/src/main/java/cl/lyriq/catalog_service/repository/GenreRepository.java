package cl.lyriq.catalog_service.repository;

import cl.lyriq.catalog_service.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}