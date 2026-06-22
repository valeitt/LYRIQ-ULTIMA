package cl.lyriq.history_service.controller;

import cl.lyriq.history_service.dto.HistoryDTO;
import cl.lyriq.history_service.model.History;
import cl.lyriq.history_service.service.HistoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private static final Logger logger =
            LoggerFactory.getLogger(HistoryController.class);

    private final HistoryService historyService;

    public HistoryController(
            HistoryService historyService) {

        this.historyService = historyService;
    }

    @GetMapping
    public List<History> getAllHistory() {

        logger.info("Getting all history records");

        return historyService.getAllHistory();
    }

    @GetMapping("/user/{userId}")
    public List<History> getHistoryByUser(
            @PathVariable Long userId) {

        logger.info("Getting history for user {}",
                userId);

        return historyService.getHistoryByUser(userId);
    }

    @PostMapping
    public History createHistory(
            @RequestBody HistoryDTO dto) {

        logger.info(
                "Creating history record for user {} and song {}",
                dto.getUserId(),
                dto.getSongId());

        return historyService.saveHistory(dto);
    }
}