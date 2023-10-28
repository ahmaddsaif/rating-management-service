package dev.localservicesreview.ratingservice.thirdpartyclients.notificationSvc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationResponseDto {
    private Long service_id;
    private NotificationDataDto[] notificationData;
}
