package dev.localservicesreview.ratingservice.services;

import java.util.UUID;
import dev.localservicesreview.ratingservice.dtos.RatingDto;
import dev.localservicesreview.ratingservice.exceptions.InternalServerException;
import dev.localservicesreview.ratingservice.exceptions.NotFoundException;
import dev.localservicesreview.ratingservice.exceptions.TPAServiceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

//@Service
public interface RatingService {
    public RatingDto getRatingById(Long rating_id) throws NotFoundException, InternalServerException;
    public Long getTotalRatingsByServiceId(UUID service_id) throws NotFoundException, TPAServiceException, InternalServerException;
    public Double getAverageRatingOfService(UUID service_id) throws NotFoundException, TPAServiceException, InternalServerException;
    public RatingDto submitRating(RatingDto ratingDto) throws NotFoundException, TPAServiceException, InternalServerException;
    public RatingDto updateRating(Long rating_id, Integer rating) throws NotFoundException, InternalServerException;
    public RatingDto deleteRating(Long rating_id) throws NotFoundException, InternalServerException;
}
