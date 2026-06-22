package cl.lyriq.swipe_service.controller;

import cl.lyriq.swipe_service.dto.SwipeDTO;
import cl.lyriq.swipe_service.model.Swipe;
import cl.lyriq.swipe_service.model.SwipeAction;
import cl.lyriq.swipe_service.service.SwipeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/swipes")
public class SwipeController {

    private static final Logger logger =
            LoggerFactory.getLogger(SwipeController.class);

    private final SwipeService swipeService;

    public SwipeController(SwipeService swipeService) {
        this.swipeService = swipeService;
    }

    @GetMapping
    public List<Swipe> getAllSwipes() {

        logger.info("Getting all swipes");

        return swipeService.getAllSwipes();
    }

    @GetMapping("/{id}")
    public Swipe getSwipeById(@PathVariable Long id) {

        logger.info("Getting swipe with ID {}", id);

        return swipeService.getSwipeById(id);
    }

    @PostMapping
    public Swipe createSwipe(@RequestBody SwipeDTO dto) {

        logger.info(
                "Creating swipe for user {} and song {}",
                dto.getUserId(),
                dto.getSongId());

        return swipeService.createSwipe(dto);
    }

    @PutMapping("/{id}")
    public Swipe updateSwipe(@PathVariable Long id,
                             @RequestBody Swipe swipe) {

        logger.info("Updating swipe with ID {}", id);

        return swipeService.updateSwipe(id, swipe);
    }

    @DeleteMapping("/{id}")
    public String deleteSwipe(@PathVariable Long id) {

        logger.info("Deleting swipe with ID {}", id);

        swipeService.deleteSwipe(id);

        return "Swipe removed successfully :3";
    }

    @GetMapping("/user/{userId}")
    public List<Swipe> getUserSwipes(
            @PathVariable Long userId) {

        logger.info(
                "Getting swipes for user {}",
                userId);

        return swipeService.getUserSwipes(userId);
    }

    @GetMapping("/action/{action}")
    public List<Swipe> getSwipesByAction(
            @PathVariable SwipeAction action) {

        logger.info(
                "Getting swipes with action {}",
                action);

        return swipeService.getSwipesByAction(action);
    }
}