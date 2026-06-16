package cl.lyriq.history_service.repository;

import cl.lyriq.history_service.model.History;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository
        extends JpaRepository<History, Long> {

    List<History> findByUserId(Long userId);
}