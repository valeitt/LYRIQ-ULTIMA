package cl.lyriq.favorites_service.controller;

import cl.lyriq.favorites_service.model.Favorite;
import cl.lyriq.favorites_service.service.FavoriteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private static final Logger logger =
            LoggerFactory.getLogger(FavoriteController.class);

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public List<Favorite> getAllFavorites() {

        logger.info("Getting all favorites");

        return favoriteService.getAllFavorites();
    }

    @GetMapping("/{id}")
    public Favorite getFavoriteById(@PathVariable Long id) {

        logger.info("Getting favorite with ID {}", id);

        return favoriteService.getFavoriteById(id);
    }

    @PostMapping
    public Favorite createFavorite(@RequestBody Favorite favorite) {

        logger.info("Creating favorite for user {} and song {}",
                favorite.getUserId(),
                favorite.getSongId());

        return favoriteService.createFavorite(favorite);
    }

    @PutMapping("/{id}")
    public Favorite updateFavorite(@PathVariable Long id,
                                   @RequestBody Favorite favorite) {

        logger.info("Updating favorite with ID {}", id);

        return favoriteService.updateFavorite(id, favorite);
    }

    @DeleteMapping("/{id}")
    public String deleteFavorite(@PathVariable Long id) {

        logger.info("Deleting favorite with ID {}", id);

        favoriteService.deleteFavorite(id);

        return "Favorite deleted successfully ;)";
    }

    @GetMapping("/user/{userId}")
    public List<Favorite> getFavoritesByUser(
            @PathVariable Long userId) {

        logger.info("Getting favorites for user {}", userId);

        return favoriteService.getFavoritesByUser(userId);
    }

    @GetMapping("/count/{userId}")
    public Long countFavoritesByUser(
            @PathVariable Long userId) {

        logger.info("Counting favorites for user {}", userId);

        return favoriteService.countFavoritesByUser(userId);
    }
}