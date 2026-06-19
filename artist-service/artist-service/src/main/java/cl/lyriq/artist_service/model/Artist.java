package cl.lyriq.artist_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;
}