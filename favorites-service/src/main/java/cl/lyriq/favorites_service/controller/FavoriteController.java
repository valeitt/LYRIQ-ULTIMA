package cl.lyriq.favorites_service.controller;

import cl.lyriq.favorites_service.model.Favorite;
import cl.lyriq.favorites_service.service.FavoriteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Favorites",
        description = "Operaciones relacionadas con canciones favoritas"
)
@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(
            FavoriteService favoriteService) {

        this.favoriteService = favoriteService;
    }

    @Operation(
            summary = "Obtener todos los favoritos",
            description = "Retorna la lista completa de canciones favoritas"
    )
    @GetMapping
    public List<Favorite> getAllFavorites() {
        return favoriteService.getAllFavorites();
    }

    @Operation(
            summary = "Buscar favorito por ID",
            description = "Obtiene un favorito específico mediante su ID"
    )
    @GetMapping("/{id}")
    public Favorite getFavoriteById(
            @PathVariable Long id) {

        return favoriteService.getFavoriteById(id);
    }

    @PostMapping
    public Favorite createFavorite(
            @RequestBody Favorite favorite) {

        return favoriteService.createFavorite(favorite);
    }

    @PutMapping("/{id}")
    public Favorite updateFavorite(
            @PathVariable Long id,
            @RequestBody Favorite favorite) {

        return favoriteService.updateFavorite(id, favorite);
    }

    @DeleteMapping("/{id}")
    public String deleteFavorite(
            @PathVariable Long id) {

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