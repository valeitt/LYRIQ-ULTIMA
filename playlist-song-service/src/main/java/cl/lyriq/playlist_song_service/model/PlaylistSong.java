package cl.lyriq.playlist_song_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "playlist_songs")
public class PlaylistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long playlistId;

    private Long songId;
}