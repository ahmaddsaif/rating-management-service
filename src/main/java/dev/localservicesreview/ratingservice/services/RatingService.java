package dev.localservicesreview.ratingservice.services;

import dev.localservicesreview.ratingservice.dtos.RatingDto;
import dev.localservicesreview.ratingservice.exceptions.BadRequestException;
import dev.localservicesreview.ratingservice.exceptions.InternalServerException;
import dev.localservicesreview.ratingservice.exceptions.NotFoundException;
import dev.localservicesreview.ratingservice.exceptions.TPAServiceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

//@Service
public interface RatingService {
    public RatingDto getRatingById(UUID rating_id) throws NotFoundException, InternalServerException;
    public Long getTotalRatingsByServiceId(UUID service_id) throws NotFoundException, TPAServiceException, InternalServerException;
    public Double getAverageRatingOfService(UUID service_id) throws NotFoundException, TPAServiceException, InternalServerException;
    public RatingDto submitRating(RatingDto ratingDto) throws NotFoundException, TPAServiceException, InternalServerException, BadRequestException;
    public RatingDto updateRating(UUID rating_id, Integer rating) throws NotFoundException, InternalServerException, BadRequestException;
    public RatingDto deleteRating(UUID rating_id) throws NotFoundException, InternalServerException;
}
