package dev.localservicesreview.ratingservice.thirdpartyclients.notificationSvc;

import dev.localservicesreview.ratingservice.exceptions.InternalServerException;
import dev.localservicesreview.ratingservice.exceptions.TPAServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationSvcClient {
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${notificationSvc.base.url}")
    private String notificationSvcBaseUrl = "https://demoNotificationSvc.com";

    @Value("${notificationSvc.send}")
    private String notificationSend = "/send";

    public NotificationSvcClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public NotificationResponseDto sendNotification(NotificationRequestDto notificationReqDto)
            throws TPAServiceException {
        try {
            RestTemplate restTemplate = restTemplateBuilder.build();
            NotificationResponseDto response =
                    restTemplate.postForObject(notificationSvcBaseUrl + notificationSend,
                            notificationReqDto, NotificationResponseDto.class);

            if(response == null)
                throw new TPAServiceException("Error at notification service.");

            return response;
        } catch (Exception e) {
            System.out.println(e);
            throw new TPAServiceException("Error occurred at notification service.");
        }
    }
}
