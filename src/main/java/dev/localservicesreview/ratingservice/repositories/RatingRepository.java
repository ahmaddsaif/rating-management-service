package dev.localservicesreview.ratingservice.repositories;

import dev.localservicesreview.ratingservice.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Long countByService_Id(Long service_id);

    List<Rating> findAllByService_Id(Long serviceId);
}
