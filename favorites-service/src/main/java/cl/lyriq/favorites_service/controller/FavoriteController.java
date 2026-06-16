package cl.lyriq.favorites_service.controller;

import cl.lyriq.favorites_service.model.Favorite;
import cl.lyriq.favorites_service.service.FavoriteService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public List<Favorite> getAllFavorites() {
        return favoriteService.getAllFavorites();
    }

    @GetMapping("/{id}")
    public Favorite getFavoriteById(@PathVariable Long id) {
        return favoriteService.getFavoriteById(id);
    }

    @PostMapping
    public Favorite createFavorite(@RequestBody Favorite favorite) {
        return favoriteService.createFavorite(favorite);
    }

    @PutMapping("/{id}")
    public Favorite updateFavorite(@PathVariable Long id,
                                   @RequestBody Favorite favorite) {

        return favoriteService.updateFavorite(id, favorite);
    }

    @DeleteMapping("/{id}")
    public String deleteFavorite(@PathVariable Long id) {

        favoriteService.deleteFavorite(id);

        return "Favorite deleted successfully ;)";
    }

    @GetMapping("/user/{userId}")
    public List<Favorite> getFavoritesByUser(
            @PathVariable Long userId) {

        return favoriteService.getFavoritesByUser(userId);
    }

    @GetMapping("/count/{userId}")
    public Long countFavoritesByUser(
            @PathVariable Long userId) {

        return favoriteService.countFavoritesByUser(userId);
    }
}