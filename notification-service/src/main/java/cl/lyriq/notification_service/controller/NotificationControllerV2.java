package cl.lyriq.notification_service.controller;

import cl.lyriq.notification_service.assemblers.NotificationModelAssemblers;
import cl.lyriq.notification_service.model.Notification;
import cl.lyriq.notification_service.service.NotificationService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/notifications/v2")
public class NotificationControllerV2 {

    private final NotificationService service;
    private final NotificationModelAssemblers assembler;

    public NotificationControllerV2(
            NotificationService service,
            NotificationModelAssemblers assembler) {

        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Notification>> getAll() {

        List<EntityModel<Notification>> notifications =
                service.getAll()
                        .stream()
                        .map(assembler::toModel)
                        .toList();

        return CollectionModel.of(
                notifications,
                linkTo(
                        methodOn(NotificationControllerV2.class)
                                .getAll()
                ).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<Notification> getById(
            @PathVariable Long id) {

        return assembler.toModel(
                service.getById(id)
        );
    }
}