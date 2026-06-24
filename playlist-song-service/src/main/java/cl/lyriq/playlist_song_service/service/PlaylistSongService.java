package cl.lyriq.playlist_song_service.service;

import cl.lyriq.playlist_song_service.dto.PlaylistSongDTO;
import cl.lyriq.playlist_song_service.exception.BadRequestException;
import cl.lyriq.playlist_song_service.exception.ResourceNotFoundException;
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
                        new ResourceNotFoundException(
                                "Relación no encontrada"));
    }

    public PlaylistSong create(
            PlaylistSongDTO dto) {

        if (dto.getPlaylistId() == null) {
            throw new BadRequestException(
                    "El playlistId es obligatorio");
        }

        if (dto.getSongId() == null) {
            throw new BadRequestException(
                    "El songId es obligatorio");
        }

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
                                new ResourceNotFoundException(
                                        "Relación no encontrada"));

        repository.delete(playlistSong);
    }

    public List<PlaylistSong> getByPlaylist(
            Long playlistId) {

        return repository.findByPlaylistId(
                playlistId);
    }
}