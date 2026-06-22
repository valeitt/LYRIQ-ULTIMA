package cl.lyriq.artist_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArtistDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String country;
}