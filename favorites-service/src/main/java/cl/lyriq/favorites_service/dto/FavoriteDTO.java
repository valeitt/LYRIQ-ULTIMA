package cl.lyriq.favorites_service.dto;

import lombok.Data;

@Data
public class FavoriteDTO {

    private Long userId;
    private Long songId;
}