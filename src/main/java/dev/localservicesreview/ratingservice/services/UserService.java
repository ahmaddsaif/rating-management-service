package dev.localservicesreview.ratingservice.services;

import dev.localservicesreview.ratingservice.exceptions.InternalServerException;
import dev.localservicesreview.ratingservice.exceptions.NotFoundException;
import dev.localservicesreview.ratingservice.exceptions.TPAServiceException;
import dev.localservicesreview.ratingservice.thirdpartyclients.userSvc.UserDto;

public interface UserService {
    public UserDto getUserById(Long id) throws TPAServiceException, NotFoundException, InternalServerException;
}
