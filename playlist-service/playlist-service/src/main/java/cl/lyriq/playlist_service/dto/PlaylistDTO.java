package cl.lyriq.playlist_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlaylistDTO {

    private Long userId;

    @NotBlank
    private String name;

    private String description;
}