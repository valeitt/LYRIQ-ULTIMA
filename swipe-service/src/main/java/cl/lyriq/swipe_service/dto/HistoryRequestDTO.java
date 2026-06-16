package cl.lyriq.swipe_service.dto;

import lombok.Data;

@Data
public class HistoryRequestDTO {

    private Long userId;
    private Long songId;
}