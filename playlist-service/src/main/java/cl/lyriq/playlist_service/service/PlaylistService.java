package cl.lyriq.playlist_service.service;

import cl.lyriq.playlist_service.dto.PlaylistDTO;
import cl.lyriq.playlist_service.exception.BadRequestException;
import cl.lyriq.playlist_service.exception.ResourceNotFoundException;
import cl.lyriq.playlist_service.model.Playlist;
import cl.lyriq.playlist_service.repository.PlaylistRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistService(
            PlaylistRepository playlistRepository) {

        this.playlistRepository = playlistRepository;
    }

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public Playlist getPlaylistById(Long id) {

        return playlistRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Playlist no encontrada"));
    }

    public Playlist createPlaylist(
            PlaylistDTO dto) {

        if (dto.getUserId() == null) {
            throw new BadRequestException(
                    "El userId es obligatorio");
        }

        if (dto.getName() == null ||
                dto.getName().trim().isEmpty()) {

            throw new BadRequestException(
                    "El nombre de la playlist es obligatorio");
        }

        Playlist playlist = new Playlist();

        playlist.setUserId(
                dto.getUserId());

        playlist.setName(
                dto.getName());

        playlist.setDescription(
                dto.getDescription());

        return playlistRepository.save(
                playlist);
    }

    public Playlist updatePlaylist(
            Long id,
            Playlist updatedPlaylist) {

        Playlist playlist =
                playlistRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Playlist no encontrada"));

        playlist.setName(
                updatedPlaylist.getName());

        playlist.setDescription(
                updatedPlaylist.getDescription());

        return playlistRepository.save(
                playlist);
    }

    public void deletePlaylist(Long id) {

        Playlist playlist =
                playlistRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Playlist no encontrada"));

        playlistRepository.delete(
                playlist);
    }
}