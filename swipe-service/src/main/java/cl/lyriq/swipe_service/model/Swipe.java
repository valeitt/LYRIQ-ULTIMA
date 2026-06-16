package cl.lyriq.swipe_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "swipes")
public class Swipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long songId;

    @Enumerated(EnumType.STRING)
    private SwipeAction action;
}