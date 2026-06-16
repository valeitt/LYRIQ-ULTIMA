package cl.lyriq.swipe_service.dto;

import lombok.Data;

@Data
public class SongResponseDTO {

    private Long idSong;
    private String title;
    private String artist;
    private String album;
    private String imageUrl;
}