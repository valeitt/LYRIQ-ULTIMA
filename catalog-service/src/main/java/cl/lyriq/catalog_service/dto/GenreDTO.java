package cl.lyriq.catalog_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GenreDTO {

    private Long id;

    @NotBlank(message = "El nombre del género es obligatorio")
    private String genreName;

    @NotBlank(message = "El color de la etiqueta es obligatorio")
    private String tagColor;
}