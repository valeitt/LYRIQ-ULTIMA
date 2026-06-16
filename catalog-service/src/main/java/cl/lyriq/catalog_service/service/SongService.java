package cl.lyriq.catalog_service.service;

import cl.lyriq.catalog_service.dto.SongDTO;
import cl.lyriq.catalog_service.model.Genre;
import cl.lyriq.catalog_service.model.Song;
import cl.lyriq.catalog_service.repository.GenreRepository;
import cl.lyriq.catalog_service.repository.SongRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private static final Logger logger =
            LoggerFactory.getLogger(SongService.class);

    private final SongRepository songRepository;
    private final GenreRepository genreRepository;

    public SongService(SongRepository songRepository,
                       GenreRepository genreRepository) {

        this.songRepository = songRepository;
        this.genreRepository = genreRepository;
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song getSongById(Long id) {

        return songRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Song not found T-T"));
    }

    public Song saveSong(SongDTO dto) {

        logger.info("Creating song >.< {}", dto.getTitle());

        Genre genre = genreRepository.findById(dto.getGenreId())
                .orElseThrow(() ->
                        new RuntimeException("Genre not found"));

        Song song = new Song();

        song.setTitle(dto.getTitle());
        song.setArtist(dto.getArtist());
        song.setAlbum(dto.getAlbum());
        song.setImageUrl(dto.getImageUrl());
        song.setGenre(genre);

        Song savedSong = songRepository.save(song);

        logger.info("Song saved with ID :3 {}",
                savedSong.getId());

        return savedSong;
    }

    public Song updateSong(Long id, Song updatedSong) {

        Song song = songRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Song not found :("));

        song.setTitle(updatedSong.getTitle());
        song.setArtist(updatedSong.getArtist());
        song.setAlbum(updatedSong.getAlbum());
        song.setImageUrl(updatedSong.getImageUrl());

        logger.info("Song updated with ID :3 {}", id);

        return songRepository.save(song);
    }

    public void deleteSong(Long id) {

        Song song = songRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Song not found :/"));

        songRepository.delete(song);

        logger.info("Song delete with ID :D {}", id);
    }
}