package cl.lyriq.catalog_service.dto;

import lombok.Data;

@Data
public class SongDTO {

    private String title;
    private String artist;
    private String album;
    private String imageUrl;
    private Long genreId;
}