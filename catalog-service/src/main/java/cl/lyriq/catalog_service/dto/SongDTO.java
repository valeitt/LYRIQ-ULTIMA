package cl.lyriq.catalog_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SongDTO {
    
@NotBlank(message = "El título de la canción es obligatorio")
private String title;

@NotBlank(message = "El nombre del artista es obligatorio")
private String artist;

private String album;

private String imageUrl;

@NotNull(message = "El ID del género es obligatorio")
private Long genreId;
}