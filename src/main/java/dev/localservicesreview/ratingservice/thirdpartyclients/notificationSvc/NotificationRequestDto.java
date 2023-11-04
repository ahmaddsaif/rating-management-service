package dev.localservicesreview.ratingservice.thirdpartyclients.notificationSvc;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class NotificationRequestDto {
    private UUID service_id;
    private NotificationDataDto[] notificationData;
}

