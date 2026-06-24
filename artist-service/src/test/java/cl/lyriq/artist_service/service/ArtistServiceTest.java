package cl.lyriq.artist_service.service;

import cl.lyriq.artist_service.dto.ArtistDTO;
import cl.lyriq.artist_service.model.Artist;
import cl.lyriq.artist_service.repository.ArtistRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

    @Mock
    private ArtistRepository repository;

    @InjectMocks
    private ArtistService service;

    @Test
    void shouldReturnArtistById() {

        // Given
        Artist artist = new Artist();

        artist.setId(1L);
        artist.setName("Linkin Park");
        artist.setCountry("USA");

        when(repository.findById(1L))
                .thenReturn(Optional.of(artist));

        // When
        Artist result = service.getById(1L);

        // Then
        assertEquals(1L, result.getId());
        assertEquals("Linkin Park", result.getName());
        assertEquals("USA", result.getCountry());
    }

    @Test
    void shouldCreateArtist() {

        // Given
        ArtistDTO dto = new ArtistDTO();

        dto.setName("Metallica");
        dto.setCountry("USA");

        Artist savedArtist = new Artist();

        savedArtist.setId(1L);
        savedArtist.setName("Metallica");
        savedArtist.setCountry("USA");

        when(repository.save(any(Artist.class)))
                .thenReturn(savedArtist);

        // When
        Artist result = service.create(dto);

        // Then
        assertNotNull(result);
        assertEquals("Metallica", result.getName());

        verify(repository, times(1))
                .save(any(Artist.class));
    }

    @Test
    void shouldDeleteArtist() {

        // Given
        Artist artist = new Artist();

        artist.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(artist));

        // When
        service.delete(1L);

        // Then
        verify(repository, times(1))
                .delete(artist);
    }
}