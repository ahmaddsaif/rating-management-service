package dev.localservicesreview.ratingservice.thirdpartyclients.userSvc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String email;
}