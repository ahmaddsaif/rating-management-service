package dev.localservicesreview.ratingservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "service")
@Getter
@Setter
public class Service {
    @Id
    @Column(name = "id", nullable = false)
    private Long Id;

    @Column(name = "service_name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "service")
    private List<Rating> ratings;
}
