package cl.lyriq.playlist_song_service.dto;

import lombok.Data;

@Data
public class PlaylistSongDTO {

    private Long playlistId;

    private Long songId;
}