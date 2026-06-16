package cl.lyriq.swipe_service.dto;

import lombok.Data;

@Data
public class FavoriteRequestDTO {

    private Long userId;
    private Long songId;
}