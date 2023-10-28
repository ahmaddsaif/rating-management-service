package dev.localservicesreview.ratingservice.services;

import dev.localservicesreview.ratingservice.exceptions.InternalServerException;
import dev.localservicesreview.ratingservice.exceptions.NotFoundException;
import dev.localservicesreview.ratingservice.exceptions.TPAServiceException;
import dev.localservicesreview.ratingservice.thirdpartyclients.serviceMgmtSvc.ServiceMgmtSvcDto;

public interface ServiceMgmtSvc{
    public ServiceMgmtSvcDto getServiceById(Long id) throws NotFoundException, TPAServiceException, InternalServerException;
}
