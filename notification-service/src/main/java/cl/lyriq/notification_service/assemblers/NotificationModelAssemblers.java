package cl.lyriq.notification_service.assemblers;

import cl.lyriq.notification_service.controller.NotificationController;
import cl.lyriq.notification_service.model.Notification;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class NotificationModelAssemblers
        implements RepresentationModelAssembler<Notification, EntityModel<Notification>> {

    @Override
    public EntityModel<Notification> toModel(Notification notification) {

        return EntityModel.of(notification,

                linkTo(
                        methodOn(NotificationController.class)
                                .getById(notification.getId())
                ).withSelfRel(),

                linkTo(
                        methodOn(NotificationController.class)
                                .getAll()
                ).withRel("notifications")
        );
    }
}