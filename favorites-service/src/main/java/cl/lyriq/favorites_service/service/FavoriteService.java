package cl.lyriq.favorites_service.service;

import cl.lyriq.favorites_service.model.Favorite;
import cl.lyriq.favorites_service.repository.FavoriteRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    public Favorite getFavoriteById(Long id) {

        return favoriteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Favorite not found T.T"));
    }

    public Favorite createFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    public Favorite updateFavorite(Long id,
                                   Favorite updatedFavorite) {

        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Favorite not found T.T"));

        favorite.setUserId(updatedFavorite.getUserId());
        favorite.setSongId(updatedFavorite.getSongId());

        return favoriteRepository.save(favorite);
    }

    public void deleteFavorite(Long id) {

        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Favorite not found T.T"));

        favoriteRepository.delete(favorite);
    }

    public List<Favorite> getFavoritesByUser(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public Long countFavoritesByUser(Long userId) {
        return (long) favoriteRepository.findByUserId(userId).size();
    }
}