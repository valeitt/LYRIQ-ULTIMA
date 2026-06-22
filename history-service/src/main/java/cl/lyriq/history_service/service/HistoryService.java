package cl.lyriq.history_service.service;

import cl.lyriq.history_service.dto.HistoryDTO;
import cl.lyriq.history_service.model.History;
import cl.lyriq.history_service.repository.HistoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryService {

    private static final Logger logger =
            LoggerFactory.getLogger(HistoryService.class);

    private final HistoryRepository historyRepository;

    public HistoryService(
            HistoryRepository historyRepository) {

        this.historyRepository = historyRepository;
    }

    public List<History> getAllHistory() {

        logger.info("Getting all history records");

        return historyRepository.findAll();
    }

    public List<History> getHistoryByUser(
            Long userId) {

        logger.info("Getting history for user {}",
                userId);

        return historyRepository.findByUserId(userId);
    }

    public History saveHistory(HistoryDTO dto) {

        logger.info(
                "Saving history record for user {} and song {}",
                dto.getUserId(),
                dto.getSongId());

        History history = new History();

        history.setUserId(dto.getUserId());
        history.setSongId(dto.getSongId());
        history.setPlayedAt(LocalDateTime.now());

        History savedHistory = historyRepository.save(history);

        logger.info(
                "History record created successfully with ID {}",
                savedHistory.getId());

        return savedHistory;
    }
}