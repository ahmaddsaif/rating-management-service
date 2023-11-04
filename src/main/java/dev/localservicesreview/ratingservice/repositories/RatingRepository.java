package dev.localservicesreview.ratingservice.repositories;

import dev.localservicesreview.ratingservice.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Long countByServiceId(UUID service_id);

    List<Rating> findAllByServiceId(UUID serviceId);
}
