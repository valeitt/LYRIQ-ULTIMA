package cl.lyriq.artist_service.controller;

import cl.lyriq.artist_service.assemblers.ArtistModelAssemblers;
import cl.lyriq.artist_service.model.Artist;
import cl.lyriq.artist_service.service.ArtistService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/artists/v2")
public class ArtistControllerV2 {

    private final ArtistService service;
    private final ArtistModelAssemblers assembler;

    public ArtistControllerV2(
            ArtistService service,
            ArtistModelAssemblers assembler) {

        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Artist>> getAll() {

        List<EntityModel<Artist>> artists =
                service.getAll()
                        .stream()
                        .map(assembler::toModel)
                        .toList();

        return CollectionModel.of(
                artists,
                linkTo(
                        methodOn(ArtistControllerV2.class)
                                .getAll()
                ).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<Artist> getById(
            @PathVariable Long id) {

        return assembler.toModel(
                service.getById(id)
        );
    }
}