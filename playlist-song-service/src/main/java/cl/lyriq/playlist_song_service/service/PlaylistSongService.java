package cl.lyriq.playlist_song_service.service;

import cl.lyriq.playlist_song_service.dto.PlaylistSongDTO;
import cl.lyriq.playlist_song_service.model.PlaylistSong;
import cl.lyriq.playlist_song_service.repository.PlaylistSongRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistSongService {

    private static final Logger logger =
            LoggerFactory.getLogger(PlaylistSongService.class);

    private final PlaylistSongRepository repository;

    public PlaylistSongService(
            PlaylistSongRepository repository) {

        this.repository = repository;
    }

    public List<PlaylistSong> getAll() {

        logger.info("Getting all playlist-song relations");

        return repository.findAll();
    }

    public PlaylistSong getById(Long id) {

        logger.info(
                "Getting playlist-song relation with ID {}",
                id);

        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.error(
                            "Playlist-song relation not found with ID {}",
                            id);
                    return new RuntimeException(
                            "Playlist-song relation not found");
                });
    }

    public PlaylistSong create(
            PlaylistSongDTO dto) {

        logger.info(
                "Creating playlist-song relation. Playlist ID: {}, Song ID: {}",
                dto.getPlaylistId(),
                dto.getSongId());

        PlaylistSong playlistSong =
                new PlaylistSong();

        playlistSong.setPlaylistId(
                dto.getPlaylistId());

        playlistSong.setSongId(
                dto.getSongId());

        PlaylistSong savedRelation =
                repository.save(playlistSong);

        logger.info(
                "Playlist-song relation created successfully with ID {}",
                savedRelation.getId());

        return savedRelation;
    }

    public void delete(Long id) {

        logger.info(
                "Deleting playlist-song relation with ID {}",
                id);

        PlaylistSong playlistSong =
                repository.findById(id)
                        .orElseThrow(() -> {
                            logger.error(
                                    "Playlist-song relation not found with ID {}",
                                    id);
                            return new RuntimeException(
                                    "Playlist-song relation not found");
                        });

        repository.delete(playlistSong);

        logger.info(
                "Playlist-song relation deleted successfully with ID {}",
                id);
    }

    public List<PlaylistSong> getByPlaylist(
            Long playlistId) {

        logger.info(
                "Getting all songs from playlist ID {}",
                playlistId);

        return repository.findByPlaylistId(
                playlistId);
    }
}