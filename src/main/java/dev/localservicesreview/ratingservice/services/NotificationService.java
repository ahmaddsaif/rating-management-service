package dev.localservicesreview.ratingservice.services;

import dev.localservicesreview.ratingservice.exceptions.InternalServerException;
import dev.localservicesreview.ratingservice.exceptions.TPAServiceException;
import dev.localservicesreview.ratingservice.thirdpartyclients.notificationSvc.Channel;
import dev.localservicesreview.ratingservice.thirdpartyclients.notificationSvc.NotificationResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    public NotificationResponseDto sendNotification(Long serviceId, String message,
                                                    String image, Channel channel)
            throws TPAServiceException, InternalServerException;
}
