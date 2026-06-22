package cl.lyriq.playlist_service.service;

import cl.lyriq.playlist_service.dto.PlaylistDTO;
import cl.lyriq.playlist_service.model.Playlist;
import cl.lyriq.playlist_service.repository.PlaylistRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    private static final Logger logger =
            LoggerFactory.getLogger(PlaylistService.class);

    private final PlaylistRepository playlistRepository;

    public PlaylistService(
            PlaylistRepository playlistRepository) {

        this.playlistRepository = playlistRepository;
    }

    public List<Playlist> getAllPlaylists() {

        logger.info("Getting all playlists");

        return playlistRepository.findAll();
    }

    public Playlist getPlaylistById(Long id) {

        logger.info("Getting playlist with ID {}", id);

        return playlistRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error(
                            "Playlist not found with ID {}",
                            id);
                    return new RuntimeException(
                            "Playlist not found");
                });
    }

    public Playlist createPlaylist(
            PlaylistDTO dto) {

        logger.info("Creating playlist: {}",
                dto.getName());

        Playlist playlist = new Playlist();

        playlist.setUserId(dto.getUserId());
        playlist.setName(dto.getName());
        playlist.setDescription(dto.getDescription());

        Playlist savedPlaylist =
                playlistRepository.save(playlist);

        logger.info(
                "Playlist created successfully with ID {}",
                savedPlaylist.getId());

        return savedPlaylist;
    }

    public Playlist updatePlaylist(
            Long id,
            Playlist updatedPlaylist) {

        logger.info(
                "Updating playlist with ID {}",
                id);

        Playlist playlist =
                playlistRepository.findById(id)
                        .orElseThrow(() -> {
                            logger.error(
                                    "Playlist not found with ID {}",
                                    id);
                            return new RuntimeException(
                                    "Playlist not found");
                        });

        playlist.setName(updatedPlaylist.getName());
        playlist.setDescription(
                updatedPlaylist.getDescription());

        Playlist savedPlaylist =
                playlistRepository.save(playlist);

        logger.info(
                "Playlist updated successfully with ID {}",
                id);

        return savedPlaylist;
    }

    public void deletePlaylist(Long id) {

        logger.info(
                "Deleting playlist with ID {}",
                id);

        Playlist playlist =
                playlistRepository.findById(id)
                        .orElseThrow(() -> {
                            logger.error(
                                    "Playlist not found with ID {}",
                                    id);
                            return new RuntimeException(
                                    "Playlist not found");
                        });

        playlistRepository.delete(playlist);

        logger.info(
                "Playlist deleted successfully with ID {}",
                id);
    }
}