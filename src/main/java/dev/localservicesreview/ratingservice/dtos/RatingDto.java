package dev.localservicesreview.ratingservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {
    private Long id;
    private Long service_id;
    private Long user_id;
    private Integer rating;
}
