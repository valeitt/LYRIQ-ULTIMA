package cl.lyriq.history_service.controller;

import cl.lyriq.history_service.assemblers.HistoryModelAssemblers;
import cl.lyriq.history_service.model.History;
import cl.lyriq.history_service.service.HistoryService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/history/v2")
public class HistoryControllerV2 {

    private final HistoryService historyService;
    private final HistoryModelAssemblers assembler;

    public HistoryControllerV2(
            HistoryService historyService,
            HistoryModelAssemblers assembler) {

        this.historyService = historyService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<History>> getAllHistory() {

        List<EntityModel<History>> history =
                historyService.getAllHistory()
                        .stream()
                        .map(assembler::toModel)
                        .toList();

        return CollectionModel.of(
                history,
                linkTo(
                        methodOn(HistoryControllerV2.class)
                                .getAllHistory()
                ).withSelfRel()
        );
    }

    @GetMapping("/user/{userId}")
    public CollectionModel<EntityModel<History>> getHistoryByUser(
            @PathVariable Long userId) {

        List<EntityModel<History>> history =
                historyService.getHistoryByUser(userId)
                        .stream()
                        .map(assembler::toModel)
                        .toList();

        return CollectionModel.of(history);
    }
}