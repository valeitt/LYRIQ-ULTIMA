package cl.lyriq.catalog_service.controller;

import cl.lyriq.catalog_service.assemblers.SongModelAssemblers;
import cl.lyriq.catalog_service.model.Song;
import cl.lyriq.catalog_service.service.SongService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/songs/v2")
public class SongControllerV2 {

private final SongService songService;
private final SongModelAssemblers assembler;

public SongControllerV2(
        SongService songService,
        SongModelAssemblers assembler) {

    this.songService = songService;
    this.assembler = assembler;
}

@GetMapping
public CollectionModel<EntityModel<Song>> getAllSongs() {

    List<EntityModel<Song>> songs =
            songService.getAllSongs()
                    .stream()
                    .map(assembler::toModel)
                    .toList();

    return CollectionModel.of(
            songs,

            linkTo(
                    methodOn(SongControllerV2.class)
                            .getAllSongs()
            ).withSelfRel()
    );
}

@GetMapping("/{id}")
public EntityModel<Song> getSongById(
        @PathVariable Long id) {

    return assembler.toModel(
            songService.getSongById(id)
    );
}

}
