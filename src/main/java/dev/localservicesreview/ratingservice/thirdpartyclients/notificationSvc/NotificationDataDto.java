package dev.localservicesreview.ratingservice.thirdpartyclients.notificationSvc;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDataDto {
    private String image;
    private String message;
    private Channel channel;
}
