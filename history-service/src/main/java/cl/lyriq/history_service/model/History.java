package cl.lyriq.history_service.model;

import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long songId;

    private LocalDateTime playedAt;
}