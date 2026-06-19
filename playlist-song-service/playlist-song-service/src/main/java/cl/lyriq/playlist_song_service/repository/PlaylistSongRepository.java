package cl.lyriq.playlist_song_service.repository;

import cl.lyriq.playlist_song_service.model.PlaylistSong;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistSongRepository
        extends JpaRepository<PlaylistSong, Long> {

    List<PlaylistSong> findByPlaylistId(Long playlistId);
}