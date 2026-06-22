package cl.lyriq.favorites_service.service;

import cl.lyriq.favorites_service.model.Favorite;
import cl.lyriq.favorites_service.repository.FavoriteRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private static final Logger logger =
            LoggerFactory.getLogger(FavoriteService.class);

    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public List<Favorite> getAllFavorites() {

        logger.info("Getting all favorites");

        return favoriteRepository.findAll();
    }

    public Favorite getFavoriteById(Long id) {

        logger.info("Getting favorite with ID {}", id);

        return favoriteRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Favorite not found with ID {}", id);
                    return new RuntimeException("Favorite not found T.T");
                });
    }

    public Favorite createFavorite(Favorite favorite) {

        logger.info("Creating favorite for user {} and song {}",
                favorite.getUserId(),
                favorite.getSongId());

        Favorite savedFavorite = favoriteRepository.save(favorite);

        logger.info("Favorite created successfully with ID {}",
                savedFavorite.getId());

        return savedFavorite;
    }

    public Favorite updateFavorite(Long id,
                                   Favorite updatedFavorite) {

        logger.info("Updating favorite with ID {}", id);

        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Favorite not found with ID {}", id);
                    return new RuntimeException("Favorite not found T.T");
                });

        favorite.setUserId(updatedFavorite.getUserId());
        favorite.setSongId(updatedFavorite.getSongId());

        Favorite savedFavorite = favoriteRepository.save(favorite);

        logger.info("Favorite updated successfully with ID {}", id);

        return savedFavorite;
    }

    public void deleteFavorite(Long id) {

        logger.info("Deleting favorite with ID {}", id);

        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Favorite not found with ID {}", id);
                    return new RuntimeException("Favorite not found T.T");
                });

        favoriteRepository.delete(favorite);

        logger.info("Favorite deleted successfully with ID {}", id);
    }

    public List<Favorite> getFavoritesByUser(Long userId) {

        logger.info("Getting favorites for user {}", userId);

        return favoriteRepository.findByUserId(userId);
    }

    public Long countFavoritesByUser(Long userId) {

        logger.info("Counting favorites for user {}", userId);

        return (long) favoriteRepository.findByUserId(userId).size();
    }
}