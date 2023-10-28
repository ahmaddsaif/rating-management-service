package dev.localservicesreview.ratingservice.thirdpartyclients.notificationSvc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequestDto {
    private Long service_id;
    private NotificationDataDto[] notificationData;
}

