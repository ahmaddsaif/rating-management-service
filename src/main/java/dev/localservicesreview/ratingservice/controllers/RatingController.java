package dev.localservicesreview.ratingservice.controllers;

import dev.localservicesreview.ratingservice.dtos.RatingDto;
import dev.localservicesreview.ratingservice.exceptions.BadRequestException;
import dev.localservicesreview.ratingservice.exceptions.InternalServerException;
import dev.localservicesreview.ratingservice.exceptions.NotFoundException;
import dev.localservicesreview.ratingservice.exceptions.TPAServiceException;
import dev.localservicesreview.ratingservice.services.TPANotificationService;
import dev.localservicesreview.ratingservice.services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;
//    private final TPAServiceManagementService serviceManagementService;
//    private final TPAUserService userService;
    private final TPANotificationService notificationService;

    public RatingController(RatingService ratingService,
                            TPANotificationService notificationService) {
        this.ratingService = ratingService;
//        this.serviceManagementService = serviceManagementService;
//        this.userService = userService;
        this.notificationService = notificationService;
    }

    @GetMapping("/getTotalServiceRatings/{service_id}")
    public ResponseEntity<Long> getTotalRatingsByServiceId(
            @PathVariable("service_id") UUID service_id) throws TPAServiceException, InternalServerException, NotFoundException {
//        try {
            Long totalRatings = ratingService.getTotalRatingsByServiceId(service_id);
            return new ResponseEntity<>(totalRatings, HttpStatus.OK);
//        } catch (NotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @GetMapping("/getAverageServiceRatings/{service_id}")
    public ResponseEntity<Double> getAverageRatingOfService(
            @PathVariable("service_id") UUID service_id) throws TPAServiceException, InternalServerException, NotFoundException {
//        try {
            Double averageRating = ratingService.getAverageRatingOfService(service_id);
            return new ResponseEntity<>(averageRating, HttpStatus.OK);
//        } catch (NotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @PostMapping("/submit")
    public ResponseEntity<RatingDto> submitRating(@RequestBody RatingDto ratingDto) throws TPAServiceException, InternalServerException, NotFoundException, BadRequestException {

//        if(ratingDto.getRating() < 1 || ratingDto.getRating() > 5) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////            throw new BadRequestException("Rating must be in range 1 to 5.");
//        }

//        try {
            // Call the service to submit the rating
            RatingDto newRating = ratingService.submitRating(ratingDto);

            notificationService.sendNotification(
                    newRating.getService_id(),
                    "New rating " + newRating.getRating() +
                            " received for service.", null, null);

            // Return a response with the newly created rating
            return new ResponseEntity<>(newRating, HttpStatus.OK);
//        } catch (BadRequestException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        catch (NotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingDto> updateRating(
            @PathVariable("id") UUID id, @RequestBody RatingDto ratingDto) throws InternalServerException, NotFoundException, BadRequestException, TPAServiceException {

//        if(ratingDto.getRating() < 1 || ratingDto.getRating() > 5) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////            throw new BadRequestException("Rating must be in range 1 to 5.");
//        }

//        try {
            // Call the service to submit the rating
            RatingDto updatedRating = ratingService.updateRating(id, ratingDto.getRating());

        notificationService.sendNotification(
                updatedRating.getService_id(),
                "New rating " + updatedRating.getRating() + " received for service.", null, null);

            // Return a response with the newly created rating
            return new ResponseEntity<>(updatedRating, HttpStatus.OK);
//        } catch (BadRequestException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (NotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RatingDto> deleteRating(@PathVariable("id") UUID id)
            throws NotFoundException, TPAServiceException, InternalServerException{

//        try {
            // Call the service to submit the rating
            RatingDto deletedRating = ratingService.deleteRating(id);

            // Return a response with the newly created rating
            return new ResponseEntity<>(deletedRating, HttpStatus.OK);
//        } catch (NotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }
}
