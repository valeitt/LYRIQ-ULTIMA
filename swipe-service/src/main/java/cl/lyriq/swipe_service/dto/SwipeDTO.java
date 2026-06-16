package cl.lyriq.swipe_service.dto;

import lombok.Data;

@Data
public class SwipeDTO {

    private Long userId;
    private Long songId;
    private String action;
}