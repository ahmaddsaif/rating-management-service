package dev.localservicesreview.ratingservice.services;

import dev.localservicesreview.ratingservice.exceptions.InternalServerException;
import dev.localservicesreview.ratingservice.exceptions.TPAServiceException;
import dev.localservicesreview.ratingservice.thirdpartyclients.notificationSvc.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("TPANotificationService")
@Primary
public class TPANotificationService implements NotificationService {
    private NotificationSvcClient notificationSvcClient;

    public TPANotificationService(NotificationSvcClient notificationSvcClient) {
        this.notificationSvcClient = notificationSvcClient;
    }

    public NotificationResponseDto sendNotification(Long service_id, String message,
                                                    String image, Channel channel)
    throws TPAServiceException, InternalServerException {
        try {
            NotificationRequestDto notificationReqDto = new NotificationRequestDto();
            notificationReqDto.setService_id(service_id);
            NotificationDataDto notificationDataDto = new NotificationDataDto();
            notificationDataDto.setImage(image);
            notificationDataDto.setMessage(message);
            notificationDataDto.setChannel(channel);
            notificationReqDto.setNotificationData(new NotificationDataDto[]{notificationDataDto});
            return notificationSvcClient.sendNotification(notificationReqDto);
        } catch (Exception e) {
            System.out.println(e);
            throw new InternalServerException("Error occurred at notification service.");
        }
    }
}
