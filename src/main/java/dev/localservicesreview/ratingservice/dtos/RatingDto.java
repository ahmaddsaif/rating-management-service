package dev.localservicesreview.ratingservice.dtos;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {
    private Long id;
    private UUID service_id;
    private Long user_id;
    private Integer rating;
}
