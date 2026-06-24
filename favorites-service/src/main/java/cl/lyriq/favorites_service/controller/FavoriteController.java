package cl.lyriq.favorites_service.controller;

import cl.lyriq.favorites_service.model.Favorite;
import cl.lyriq.favorites_service.service.FavoriteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    )@ApiResponse(
        responseCode = "200",
        description = "Favoritos obtenidos correctamente"
)
    @GetMapping
    public List<Favorite> getAllFavorites() {
        return favoriteService.getAllFavorites();
    }

    @Operation(
            summary = "Buscar favorito por ID",
            description = "Obtiene un favorito específico mediante su ID"
    )@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Favorito encontrado"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Favorito no encontrado"
        )
})
    @GetMapping("/{id}")
    public Favorite getFavoriteById(
            @PathVariable Long id) {

        return favoriteService.getFavoriteById(id);
    }
@Operation(
        summary = "Crear favorito",
        description = "Agrega una canción a la lista de favoritos"
)
@ApiResponse(
        responseCode = "200",
        description = "Favorito creado correctamente"
)

    @PostMapping
    public Favorite createFavorite(
            @RequestBody Favorite favorite) {

        return favoriteService.createFavorite(favorite);
    }

    @Operation(
        summary = "Actualizar favorito",
        description = "Actualiza la información de un favorito existente"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Favorito actualizado correctamente"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Favorito no encontrado"
        )
})
    @PutMapping("/{id}")
    public Favorite updateFavorite(
            @PathVariable Long id,
            @RequestBody Favorite favorite) {

        return favoriteService.updateFavorite(id, favorite);
    }


    @Operation(
        summary = "Eliminar favorito",
        description = "Elimina un favorito según su ID"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Favorito eliminado correctamente"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Favorito no encontrado"
        )
})
    @DeleteMapping("/{id}")
    public String deleteFavorite(
            @PathVariable Long id) {

        favoriteService.deleteFavorite(id);

        return "Favorite deleted successfully ;)";
    }


    @Operation(
        summary = "Obtener favoritos por usuario",
        description = "Retorna todos los favoritos asociados a un usuario"
)
@ApiResponse(
        responseCode = "200",
        description = "Favoritos encontrados correctamente"
)
    @GetMapping("/user/{userId}")
    public List<Favorite> getFavoritesByUser(
            @PathVariable Long userId) {

        return favoriteService.getFavoritesByUser(userId);
    }

    @Operation(
        summary = "Contar favoritos por usuario",
        description = "Retorna la cantidad total de favoritos de un usuario"
)
@ApiResponse(
        responseCode = "200",
        description = "Cantidad de favoritos obtenida correctamente"
)
    @GetMapping("/count/{userId}")
    public Long countFavoritesByUser(
            @PathVariable Long userId) {

        return favoriteService.countFavoritesByUser(userId);
    }
}