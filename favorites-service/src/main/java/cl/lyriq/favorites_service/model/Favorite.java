package cl.lyriq.favorites_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long songId;
}