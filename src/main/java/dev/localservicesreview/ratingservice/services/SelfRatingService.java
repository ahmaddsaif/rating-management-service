package dev.localservicesreview.ratingservice.services;

import dev.localservicesreview.ratingservice.dtos.RatingDto;
import dev.localservicesreview.ratingservice.exceptions.InternalServerException;
import dev.localservicesreview.ratingservice.exceptions.NotFoundException;
import dev.localservicesreview.ratingservice.exceptions.TPAServiceException;
import dev.localservicesreview.ratingservice.models.Rating;
import dev.localservicesreview.ratingservice.models.User;
import dev.localservicesreview.ratingservice.repositories.RatingRepository;
import dev.localservicesreview.ratingservice.thirdpartyclients.serviceMgmtSvc.ServiceMgmtSvcDto;
import dev.localservicesreview.ratingservice.thirdpartyclients.userSvc.UserDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfRatingService")
@Primary
public class SelfRatingService implements RatingService {
    private RatingRepository ratingRepository;
    TPAServiceManagementService serviceManagementService;
    TPANotificationService notificationService;
    TPAUserService userService;

    public SelfRatingService(RatingRepository ratingRepository,
                             TPAServiceManagementService serviceManagementService,
                             TPANotificationService notificationService,
                             TPAUserService userService) {
        this.ratingRepository = ratingRepository;
        this.serviceManagementService = serviceManagementService;
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @Override
    public RatingDto getRatingById(Long rating_id) throws NotFoundException, InternalServerException {
        try {
            Rating rating = ratingRepository.findById(rating_id)
                    .orElseThrow(() -> new NotFoundException("Rating with id: " + rating_id + " does not exist."));
            return convertRatingToRatingDto(rating);
        } catch (Exception e) {
            System.out.println(e);
            throw new InternalServerException("Some error occurred at rating service.");
        }
    }

    @Override
    public Long getTotalRatingsByServiceId(Long service_id)
            throws NotFoundException, TPAServiceException, InternalServerException {
        ServiceMgmtSvcDto service = serviceManagementService.getServiceById(service_id);
        return ratingRepository.countByService_Id(service.getId());
    }

    @Override
    public Double getAverageRatingOfService(Long service_id)
            throws NotFoundException, TPAServiceException, InternalServerException{
        ServiceMgmtSvcDto service = serviceManagementService.getServiceById(service_id);
        // Get all ratings for the service
        List<Rating> ratingsList = ratingRepository.findAllByService_Id(service.getId());
        Integer[] ratings = new Integer[ratingsList.size()];
        for(int i = 0; i < ratingsList.size(); i++) {
            ratings[i] = ratingsList.get(i).getRating();
        }
        // Calculate the average rating
        return calculateAverageRating(ratings);
    }

    @Override
    public RatingDto submitRating(RatingDto ratingDto)
            throws NotFoundException, TPAServiceException, InternalServerException{
        ServiceMgmtSvcDto service = serviceManagementService.getServiceById(ratingDto.getService_id());
        UserDto user = userService.getUserById(ratingDto.getUser_id());

        Rating ratingObj = convertRatingDtoToRating(ratingDto);
        Rating createdRating = ratingRepository.save(ratingObj);

        return convertRatingToRatingDto(createdRating);
    }

    @Override
    public RatingDto updateRating(Long rating_id, Integer rating)
    throws NotFoundException, InternalServerException{
        try {
            Rating ratingObj = ratingRepository.findById(rating_id)
                    .orElseThrow(() -> new NotFoundException("Rating with id: " + rating_id + " does not exist."));
            ratingObj.setRating(rating);
            Rating updatedRating = ratingRepository.save(ratingObj);
            return convertRatingToRatingDto(updatedRating);
        } catch (Exception e) {
            System.out.println(e);
            throw new InternalServerException("Some error occurred at rating service.");
        }
    }

    @Override
    public RatingDto deleteRating(Long rating_id)
    throws NotFoundException, InternalServerException{
        try {
            Rating ratingObj = ratingRepository.findById(rating_id)
                    .orElseThrow(() -> new NotFoundException("Rating with id: " + rating_id + " does not exist."));
            ratingRepository.delete(ratingObj);
            return convertRatingToRatingDto(ratingObj);
        } catch (Exception e) {
            System.out.println(e);
            throw new InternalServerException("Some error occurred at rating service.");        }
    }

    public RatingDto convertRatingToRatingDto(Rating rating) {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setId(rating.getId());
        ratingDto.setRating(rating.getRating());
        ratingDto.setService_id(rating.getService().getId());
        ratingDto.setUser_id(rating.getUser().getId());
        return ratingDto;
    }

    public Rating convertRatingDtoToRating(RatingDto ratingDto)
    throws NotFoundException, TPAServiceException, InternalServerException{
        Rating rating = new Rating();
        rating.setId(ratingDto.getId());
        rating.setRating(ratingDto.getRating());

        dev.localservicesreview.ratingservice.models.Service service =
                convertServiceDtoToService(
                        serviceManagementService.getServiceById(ratingDto.getService_id()));
        rating.setService(service);

        User user = convertUserDtoToUser(userService.getUserById(ratingDto.getUser_id()));
        rating.setUser(user);

        return rating;
    }

    public User convertUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        return user;
    }

    public dev.localservicesreview.ratingservice.models.Service
    convertServiceDtoToService(ServiceMgmtSvcDto serviceDto) {
        dev.localservicesreview.ratingservice.models.Service service =
                new dev.localservicesreview.ratingservice.models.Service();
        service.setId(serviceDto.getId());
        service.setName(serviceDto.getName());
        return service;
    }

    public double calculateAverageRating(Integer[] ratings) {
        // Calculate the sum of all ratings
        double sum = 0;
        int count = 0; // Number of valid ratings within the range [1, 5]

        for (double rating : ratings) {
            if (rating >= 1 && rating <= 5) {
                sum += rating;
                count++;
            }
        }

        if (count == 0) {
            // Return a default value
            return 0.0;
        }

        // Calculate the average within the range [1, 5]
        double average = sum / count;

        return Math.min(5.0, Math.max(1.0, average)); // Ensure the calculated average is within the range [1, 5]
    }
}
