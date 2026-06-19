package cl.lyriq.playlist_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "playlists")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String name;

    private String description;
}