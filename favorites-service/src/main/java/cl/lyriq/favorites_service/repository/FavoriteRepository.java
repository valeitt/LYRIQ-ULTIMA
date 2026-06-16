package cl.lyriq.favorites_service.repository;

import cl.lyriq.favorites_service.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUserId(Long userId);
}