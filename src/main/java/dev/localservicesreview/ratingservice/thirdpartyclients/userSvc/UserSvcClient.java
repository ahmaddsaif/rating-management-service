package dev.localservicesreview.ratingservice.thirdpartyclients.userSvc;

import dev.localservicesreview.ratingservice.exceptions.NotFoundException;
import dev.localservicesreview.ratingservice.exceptions.TPAServiceException;
import dev.localservicesreview.ratingservice.models.User;
import dev.localservicesreview.ratingservice.thirdpartyclients.serviceMgmtSvc.ServiceMgmtSvcDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserSvcClient {
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${userSvc.base.url}")
    private String userSvcBaseUrl = "https://demoUserSvc.com";

    @Value("${userSvc.users}")
    private String userSvcUsers = "/users";

    public UserSvcClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public UserDto getUserById(Long id) throws NotFoundException, TPAServiceException {
        try {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<UserDto> response =
                    restTemplate.getForEntity(userSvcBaseUrl + userSvcUsers, UserDto.class, id);
            UserDto userDto = response.getBody();

            if(userDto == null)
                throw new NotFoundException("User with id: " + id + " not found");

            return userDto;
        } catch (Exception e) {
            System.out.println(e);
            throw new TPAServiceException("Error occurred at user service.");
        }
    }
}
