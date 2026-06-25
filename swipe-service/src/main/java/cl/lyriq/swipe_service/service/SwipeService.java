package cl.lyriq.swipe_service.service;

import cl.lyriq.swipe_service.dto.FavoriteRequestDTO;
import cl.lyriq.swipe_service.dto.HistoryRequestDTO;
import cl.lyriq.swipe_service.dto.SongResponseDTO;
import cl.lyriq.swipe_service.dto.SwipeDTO;

import cl.lyriq.swipe_service.exception.BadRequestException;
import cl.lyriq.swipe_service.exception.ResourceNotFoundException;

import cl.lyriq.swipe_service.model.Swipe;
import cl.lyriq.swipe_service.model.SwipeAction;

import cl.lyriq.swipe_service.repository.SwipeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SwipeService {

    private static final Logger logger =
            LoggerFactory.getLogger(SwipeService.class);

    private final SwipeRepository swipeRepository;
    private final RestTemplate restTemplate;

    public SwipeService(
            SwipeRepository swipeRepository,
            RestTemplate restTemplate) {

        this.swipeRepository = swipeRepository;
        this.restTemplate = restTemplate;
    }

    public List<Swipe> getAllSwipes() {
        return swipeRepository.findAll();
    }

    public Swipe getSwipeById(Long id) {

        return swipeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Swipe not found"));
    }

    public Swipe createSwipe(SwipeDTO dto) {

        logger.info(
                "Creating swipe for user {} and song {}",
                dto.getUserId(),
                dto.getSongId());

        SongResponseDTO song =
                restTemplate.getForObject(
                        "http://catalog-service:7071/songs/" +
                                dto.getSongId(),
                        SongResponseDTO.class
                );

        if (song == null) {

            logger.error(
                    "The song does not exist");

            throw new BadRequestException(
                    "The song does not exist");
        }

        Swipe swipe = new Swipe();

        swipe.setUserId(dto.getUserId());
        swipe.setSongId(dto.getSongId());

        SwipeAction action = SwipeAction.valueOf(
                dto.getAction().toUpperCase()
        );

        swipe.setAction(action);

        Swipe savedSwipe =
                swipeRepository.save(swipe);

        logger.info(
                "Swipe saved successfully with ID {}",
                savedSwipe.getId());

        if (action == SwipeAction.LIKE) {

            logger.info(
                    "Adding song to favorites");

            FavoriteRequestDTO favoriteDTO =
                    new FavoriteRequestDTO();

            favoriteDTO.setUserId(
                    dto.getUserId());

            favoriteDTO.setSongId(
                    dto.getSongId());

            restTemplate.postForObject(
                    "http://favorites-service:7074/favorites",
                    favoriteDTO,
                    Object.class
            );

            logger.info(
                    "Saving playback history");

            HistoryRequestDTO historyDTO =
                    new HistoryRequestDTO();

            historyDTO.setUserId(
                    dto.getUserId());

            historyDTO.setSongId(
                    dto.getSongId());

            restTemplate.postForObject(
                    "http://history-service:7075/history",
                    historyDTO,
                    Object.class
            );
        }

        return savedSwipe;
    }

    public Swipe updateSwipe(
            Long id,
            Swipe updatedSwipe) {

        Swipe swipe =
                swipeRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Swipe not found"));

        swipe.setUserId(
                updatedSwipe.getUserId());

        swipe.setSongId(
                updatedSwipe.getSongId());

        swipe.setAction(
                updatedSwipe.getAction());

        logger.info(
                "Swipe updated with ID {}",
                id);

        return swipeRepository.save(
                swipe);
    }

    public void deleteSwipe(Long id) {

        Swipe swipe =
                swipeRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Swipe not found"));

        swipeRepository.delete(
                swipe);

        logger.info(
                "Swipe removed with ID {}",
                id);
    }

    public List<Swipe> getUserSwipes(
            Long userId) {

        return swipeRepository.findByUserId(
                userId);
    }

    public List<Swipe> getSwipesByAction(
            SwipeAction action) {

        return swipeRepository.findByAction(
                action);
    }
}