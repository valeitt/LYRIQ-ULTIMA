package cl.lyriq.artist_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArtistDTO {

    @NotBlank(message = "El nombre del artista es obligatorio")
    private String name;

    @NotBlank(message = "El país del artista es obligatorio")
    private String country;
}