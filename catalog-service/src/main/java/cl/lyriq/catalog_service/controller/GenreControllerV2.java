package cl.lyriq.catalog_service.controller;

import cl.lyriq.catalog_service.assemblers.GenreModelAssembler;
import cl.lyriq.catalog_service.model.Genre;
import cl.lyriq.catalog_service.service.GenreService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/genres/v2")
public class GenreControllerV2 {


private final GenreService genreService;
private final GenreModelAssembler assembler;

public GenreControllerV2(
        GenreService genreService,
        GenreModelAssembler assembler) {

    this.genreService = genreService;
    this.assembler = assembler;
}

@GetMapping
public CollectionModel<EntityModel<Genre>> getAllGenres() {

    List<EntityModel<Genre>> genres =
            genreService.getAllGenres()
                    .stream()
                    .map(assembler::toModel)
                    .toList();

    return CollectionModel.of(
            genres,

            linkTo(
                    methodOn(GenreControllerV2.class)
                            .getAllGenres()
            ).withSelfRel()
    );
}

@GetMapping("/{id}")
public EntityModel<Genre> getGenreById(
        @PathVariable Long id) {

    return assembler.toModel(
            genreService.getGenreById(id)
    );
}

}
