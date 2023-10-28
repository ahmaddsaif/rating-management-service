package dev.localservicesreview.ratingservice.services;

import dev.localservicesreview.ratingservice.exceptions.InternalServerException;
import dev.localservicesreview.ratingservice.exceptions.NotFoundException;
import dev.localservicesreview.ratingservice.exceptions.TPAServiceException;
import dev.localservicesreview.ratingservice.thirdpartyclients.userSvc.UserDto;
import dev.localservicesreview.ratingservice.thirdpartyclients.userSvc.UserSvcClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("TPAUserService")
@Primary
public class TPAUserService implements UserService {
    private UserSvcClient userSvcClient;

    public TPAUserService(UserSvcClient userSvcClient) {
        this.userSvcClient = userSvcClient;
    }

    public UserDto getUserById(Long id) throws TPAServiceException, NotFoundException, InternalServerException {
        try {
            return userSvcClient.getUserById(id);
        } catch (Exception e) {
            System.out.println(e);
            throw new InternalServerException("Some error occurred at user service.");
        }
    }
}
