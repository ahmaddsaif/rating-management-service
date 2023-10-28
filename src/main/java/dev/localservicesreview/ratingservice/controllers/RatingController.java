package dev.localservicesreview.ratingservice.controllers;

import dev.localservicesreview.ratingservice.dtos.RatingDto;
import dev.localservicesreview.ratingservice.exceptions.InternalServerException;
import dev.localservicesreview.ratingservice.exceptions.NotFoundException;
import dev.localservicesreview.ratingservice.exceptions.TPAServiceException;
import dev.localservicesreview.ratingservice.models.Rating;
import dev.localservicesreview.ratingservice.models.Service;
import dev.localservicesreview.ratingservice.services.TPANotificationService;
import dev.localservicesreview.ratingservice.services.RatingService;
import dev.localservicesreview.ratingservice.services.TPAServiceManagementService;
import dev.localservicesreview.ratingservice.services.TPAUserService;
import dev.localservicesreview.ratingservice.thirdpartyclients.serviceMgmtSvc.ServiceMgmtSvcDto;
import dev.localservicesreview.ratingservice.thirdpartyclients.userSvc.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;
    private final TPAServiceManagementService serviceManagementService;
    private final TPAUserService userService;
    private final TPANotificationService notificationService;

    public RatingController(RatingService ratingService,
                            TPAServiceManagementService serviceManagementService,
                            TPAUserService userService,
                            TPANotificationService notificationService) {
        this.ratingService = ratingService;
        this.serviceManagementService = serviceManagementService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @GetMapping("/getTotalServiceRatings/{service_id}")
    public ResponseEntity<Long> getTotalRatingsByServiceId(
            @PathVariable("service_id") Long service_id)
            throws NotFoundException, TPAServiceException, InternalServerException{
        Long totalRatings = ratingService.getTotalRatingsByServiceId(service_id);
        return new ResponseEntity<>(totalRatings, HttpStatus.OK);
    }

    @GetMapping("/getAverageServiceRatings/{service_id}")
    public ResponseEntity<Double> getAverageRatingOfService(
            @PathVariable("service_id") Long service_id)
            throws NotFoundException, TPAServiceException, InternalServerException{
        Double averageRating = ratingService.getAverageRatingOfService(service_id);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }

    @PostMapping("/submit")
    public ResponseEntity<RatingDto> submitRating(@RequestBody RatingDto ratingDto)
            throws NotFoundException, TPAServiceException, InternalServerException {

        // Call the service to submit the rating
        RatingDto newRating = ratingService.submitRating(ratingDto);

        // Return a response with the newly created rating
        return new ResponseEntity<>(newRating, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingDto> updateRating(
            @PathVariable("id") Long id, @RequestBody RatingDto ratingDto)
            throws NotFoundException, TPAServiceException, InternalServerException{

        // Call the service to submit the rating
        RatingDto updatedRating = ratingService.updateRating(id, ratingDto.getRating());

        // Return a response with the newly created rating
        return new ResponseEntity<>(updatedRating, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RatingDto> deleteRating(@PathVariable("id") Long id)
            throws NotFoundException, TPAServiceException, InternalServerException{

        // Call the service to submit the rating
        RatingDto deletedRating = ratingService.deleteRating(id);

        // Return a response with the newly created rating
        return new ResponseEntity<>(deletedRating, HttpStatus.OK);
    }
}
