package cl.lyriq.history_service.controller;

import cl.lyriq.history_service.dto.HistoryDTO;
import cl.lyriq.history_service.model.History;
import cl.lyriq.history_service.service.HistoryService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(
            HistoryService historyService) {

        this.historyService = historyService;
    }

    @GetMapping
    public List<History> getAllHistory() {
        return historyService.getAllHistory();
    }

    @GetMapping("/user/{userId}")
    public List<History> getHistoryByUser(
            @PathVariable Long userId) {

        return historyService.getHistoryByUser(userId);
    }

    @PostMapping
    public History createHistory(
            @RequestBody HistoryDTO dto) {

        return historyService.saveHistory(dto);
    }
}