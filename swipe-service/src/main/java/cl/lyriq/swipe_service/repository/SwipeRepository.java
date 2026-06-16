package cl.lyriq.swipe_service.repository;

import cl.lyriq.swipe_service.model.Swipe;
import cl.lyriq.swipe_service.model.SwipeAction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwipeRepository
        extends JpaRepository<Swipe, Long> {

    List<Swipe> findByUserId(Long userId);

    List<Swipe> findByAction(SwipeAction action);
}