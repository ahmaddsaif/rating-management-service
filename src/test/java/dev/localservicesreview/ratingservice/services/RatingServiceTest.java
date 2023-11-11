package dev.localservicesreview.ratingservice.services;

import dev.localservicesreview.ratingservice.dtos.RatingDto;
import dev.localservicesreview.ratingservice.exceptions.BadRequestException;
import dev.localservicesreview.ratingservice.exceptions.InternalServerException;
import dev.localservicesreview.ratingservice.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

    @Mock
    private RatingService ratingService;

    @Test
    public void testGetRatingByIdWhenValidIdThenReturnRatingDto() throws NotFoundException, InternalServerException {
        UUID ratingId = UUID.randomUUID();
        RatingDto expectedRatingDto = new RatingDto();
        expectedRatingDto.setId(ratingId);

        when(ratingService.getRatingById(ratingId)).thenReturn(expectedRatingDto);

        RatingDto actualRatingDto = ratingService.getRatingById(ratingId);

        assertEquals(expectedRatingDto, actualRatingDto);
    }

    @Test
    public void testGetRatingByIdWhenNonExistentIdThenThrowNotFoundException() throws NotFoundException, InternalServerException {
        UUID ratingId = UUID.randomUUID();

        when(ratingService.getRatingById(ratingId)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> ratingService.getRatingById(ratingId));
    }

    @Test
    public void testGetRatingByIdWhenInternalServerExceptionThenThrowInternalServerException() throws NotFoundException, InternalServerException {
        UUID ratingId = UUID.randomUUID();

        when(ratingService.getRatingById(ratingId)).thenThrow(InternalServerException.class);

        assertThrows(InternalServerException.class, () -> ratingService.getRatingById(ratingId));
    }

    @Test
    public void testGetTotalRatingsByServiceIdWhenValidIdThenReturnTotalRatings() throws NotFoundException, InternalServerException {
        UUID serviceId = UUID.randomUUID();
        Long expectedTotalRatings = 10L;

        when(ratingService.getTotalRatingsByServiceId(serviceId)).thenReturn(expectedTotalRatings);

        Long actualTotalRatings = ratingService.getTotalRatingsByServiceId(serviceId);

        assertEquals(expectedTotalRatings, actualTotalRatings);
    }

    @Test
    public void testGetTotalRatingsByServiceIdWhenNonExistentIdThenThrowNotFoundException() throws NotFoundException, InternalServerException {
        UUID serviceId = UUID.randomUUID();

        when(ratingService.getTotalRatingsByServiceId(serviceId)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> ratingService.getTotalRatingsByServiceId(serviceId));
    }

    @Test
    public void testGetTotalRatingsByServiceIdWhenInternalServerExceptionThenThrowInternalServerException() throws NotFoundException, InternalServerException {
        UUID serviceId = UUID.randomUUID();

        when(ratingService.getTotalRatingsByServiceId(serviceId)).thenThrow(InternalServerException.class);

        assertThrows(InternalServerException.class, () -> ratingService.getTotalRatingsByServiceId(serviceId));
    }

    @Test
    public void testGetAverageRatingOfServiceWhenValidIdThenReturnAverageRating() throws NotFoundException, InternalServerException {
        UUID serviceId = UUID.randomUUID();
        Double expectedAverageRating = 4.5;

        when(ratingService.getAverageRatingOfService(serviceId)).thenReturn(expectedAverageRating);

        Double actualAverageRating = ratingService.getAverageRatingOfService(serviceId);

        assertEquals(expectedAverageRating, actualAverageRating);
    }

    @Test
    public void testGetAverageRatingOfServiceWhenNonExistentIdThenThrowNotFoundException() throws NotFoundException, InternalServerException {
        UUID serviceId = UUID.randomUUID();

        when(ratingService.getAverageRatingOfService(serviceId)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> ratingService.getAverageRatingOfService(serviceId));
    }

    @Test
    public void testGetAverageRatingOfServiceWhenInternalServerExceptionThenThrowInternalServerException() throws NotFoundException, InternalServerException {
        UUID serviceId = UUID.randomUUID();

        when(ratingService.getAverageRatingOfService(serviceId)).thenThrow(InternalServerException.class);

        assertThrows(InternalServerException.class, () -> ratingService.getAverageRatingOfService(serviceId));
    }

    @Test
    public void testSubmitRatingWhenValidRatingDtoThenReturnRatingDto() throws NotFoundException, InternalServerException, BadRequestException {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setRating(5);

        RatingDto expectedRatingDto = new RatingDto();
        expectedRatingDto.setId(UUID.randomUUID());
        expectedRatingDto.setRating(5);

        when(ratingService.submitRating(ratingDto)).thenReturn(expectedRatingDto);

        RatingDto actualRatingDto = ratingService.submitRating(ratingDto);

        assertEquals(expectedRatingDto, actualRatingDto);
    }

    @Test
    public void testSubmitRatingWhenNonExistentIdThenThrowNotFoundException() throws NotFoundException, InternalServerException, BadRequestException {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setRating(5);

        when(ratingService.submitRating(ratingDto)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> ratingService.submitRating(ratingDto));
    }

    @Test
    public void testSubmitRatingWhenInternalServerExceptionThenThrowInternalServerException() throws NotFoundException, InternalServerException, BadRequestException {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setRating(5);

        when(ratingService.submitRating(ratingDto)).thenThrow(InternalServerException.class);

        assertThrows(InternalServerException.class, () -> ratingService.submitRating(ratingDto));
    }

    @Test
    public void testUpdateRatingWhenValidRatingDtoThenReturnRatingDto() throws NotFoundException, InternalServerException, BadRequestException {
        UUID ratingId = UUID.randomUUID();
        RatingDto ratingDto = new RatingDto();
        ratingDto.setRating(4);

        RatingDto expectedRatingDto = new RatingDto();
        expectedRatingDto.setId(ratingId);
        expectedRatingDto.setRating(4);

        UUID serviceId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        when(ratingService.updateRating(serviceId, userId, ratingDto.getRating())).thenReturn(expectedRatingDto);

        RatingDto actualRatingDto = ratingService.updateRating(serviceId, userId, ratingDto.getRating());

        assertEquals(expectedRatingDto, actualRatingDto);
    }

    @Test
    public void testUpdateRatingWhenNonExistentIdThenThrowNotFoundException() throws NotFoundException, InternalServerException, BadRequestException {
        UUID ratingId = UUID.randomUUID();
        RatingDto ratingDto = new RatingDto();
        ratingDto.setRating(4);

        UUID serviceId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        when(ratingService.updateRating(serviceId, userId, ratingDto.getRating())).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> ratingService.updateRating(serviceId, userId, ratingDto.getRating()));
    }

    @Test
    public void testUpdateRatingWhenInternalServerExceptionThenThrowInternalServerException() throws NotFoundException, InternalServerException, BadRequestException {
        UUID ratingId = UUID.randomUUID();
        RatingDto ratingDto = new RatingDto();
        ratingDto.setRating(4);

        UUID serviceId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        when(ratingService.updateRating(serviceId, userId, ratingDto.getRating())).thenThrow(InternalServerException.class);

        assertThrows(InternalServerException.class, () -> ratingService.updateRating(serviceId, userId, ratingDto.getRating()));
    }

    @Test
    public void testDeleteRatingWhenValidIdThenReturnRatingDto() throws NotFoundException, InternalServerException {
        UUID ratingId = UUID.randomUUID();
        RatingDto expectedRatingDto = new RatingDto();
        expectedRatingDto.setId(ratingId);

        when(ratingService.deleteRating(ratingId)).thenReturn(expectedRatingDto);

        RatingDto actualRatingDto = ratingService.deleteRating(ratingId);

        assertEquals(expectedRatingDto, actualRatingDto);
    }

    @Test
    public void testDeleteRatingWhenNonExistentIdThenThrowNotFoundException() throws NotFoundException, InternalServerException {
        UUID ratingId = UUID.randomUUID();

        when(ratingService.deleteRating(ratingId)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> ratingService.deleteRating(ratingId));
    }

    @Test
    public void testDeleteRatingWhenInternalServerExceptionThenThrowInternalServerException() throws NotFoundException, InternalServerException {
        UUID ratingId = UUID.randomUUID();

        when(ratingService.deleteRating(ratingId)).thenThrow(InternalServerException.class);

        assertThrows(InternalServerException.class, () -> ratingService.deleteRating(ratingId));
    }
}
