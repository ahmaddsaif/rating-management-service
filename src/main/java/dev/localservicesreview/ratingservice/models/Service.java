package dev.localservicesreview.ratingservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity(name = "service")
@Getter
@Setter
public class Service {
    @Id
    @Column(name = "id", nullable = false)
    private UUID Id;

    @Column(name = "service_name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> ratings;
}
