package cl.lyriq.history_service.service;

import cl.lyriq.history_service.dto.HistoryDTO;
import cl.lyriq.history_service.model.History;
import cl.lyriq.history_service.repository.HistoryRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(
            HistoryRepository historyRepository) {

        this.historyRepository = historyRepository;
    }

    public List<History> getAllHistory() {
        return historyRepository.findAll();
    }

    public List<History> getHistoryByUser(
            Long userId) {

        return historyRepository.findByUserId(userId);
    }

    public History saveHistory(HistoryDTO dto) {

        History history = new History();

        history.setUserId(dto.getUserId());
        history.setSongId(dto.getSongId());
        history.setPlayedAt(LocalDateTime.now());

        return historyRepository.save(history);
    }
}