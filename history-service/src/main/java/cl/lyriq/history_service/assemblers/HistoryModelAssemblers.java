package cl.lyriq.history_service.assemblers;

import cl.lyriq.history_service.controller.HistoryController;
import cl.lyriq.history_service.model.History;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class HistoryModelAssemblers
        implements RepresentationModelAssembler<History, EntityModel<History>> {

    @Override
    public EntityModel<History> toModel(History history) {

        return EntityModel.of(history,

                linkTo(
                        methodOn(HistoryController.class)
                                .getHistoryByUser(history.getUserId())
                ).withRel("user-history"),

                linkTo(
                        methodOn(HistoryController.class)
                                .getAllHistory()
                ).withRel("history")
        );
    }
}