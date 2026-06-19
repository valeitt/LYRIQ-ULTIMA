package cl.lyriq.playlist_song_service.service;

import cl.lyriq.playlist_song_service.dto.PlaylistSongDTO;
import cl.lyriq.playlist_song_service.model.PlaylistSong;
import cl.lyriq.playlist_song_service.repository.PlaylistSongRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistSongService {

    private final PlaylistSongRepository repository;

    public PlaylistSongService(
            PlaylistSongRepository repository) {

        this.repository = repository;
    }

    public List<PlaylistSong> getAll() {
        return repository.findAll();
    }

    public PlaylistSong getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Relación no encontrada"));
    }

    public PlaylistSong create(
            PlaylistSongDTO dto) {

        PlaylistSong playlistSong =
                new PlaylistSong();

        playlistSong.setPlaylistId(
                dto.getPlaylistId());

        playlistSong.setSongId(
                dto.getSongId());

        return repository.save(
                playlistSong);
    }

    public void delete(Long id) {

        PlaylistSong playlistSong =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Relación no encontrada"));

        repository.delete(playlistSong);
    }

    public List<PlaylistSong> getByPlaylist(
            Long playlistId) {

        return repository.findByPlaylistId(
                playlistId);
    }
}