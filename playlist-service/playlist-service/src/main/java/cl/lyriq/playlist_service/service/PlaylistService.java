package cl.lyriq.playlist_service.service;

import cl.lyriq.playlist_service.dto.PlaylistDTO;
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
                        new RuntimeException(
                                "Playlist no encontrada"));
    }

    public Playlist createPlaylist(
            PlaylistDTO dto) {

        Playlist playlist = new Playlist();

        playlist.setUserId(dto.getUserId());
        playlist.setName(dto.getName());
        playlist.setDescription(dto.getDescription());

        return playlistRepository.save(playlist);
    }

    public Playlist updatePlaylist(
            Long id,
            Playlist updatedPlaylist) {

        Playlist playlist =
                playlistRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Playlist no encontrada"));

        playlist.setName(updatedPlaylist.getName());
        playlist.setDescription(
                updatedPlaylist.getDescription());

        return playlistRepository.save(playlist);
    }

    public void deletePlaylist(Long id) {

        Playlist playlist =
                playlistRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Playlist no encontrada"));

        playlistRepository.delete(playlist);
    }
}