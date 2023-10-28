package dev.localservicesreview.ratingservice.services;

import dev.localservicesreview.ratingservice.exceptions.InternalServerException;
import dev.localservicesreview.ratingservice.exceptions.NotFoundException;
import dev.localservicesreview.ratingservice.exceptions.TPAServiceException;
import dev.localservicesreview.ratingservice.thirdpartyclients.serviceMgmtSvc.ServiceMgmtSvcClient;
import dev.localservicesreview.ratingservice.thirdpartyclients.serviceMgmtSvc.ServiceMgmtSvcDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("TPAServiceManagementService")
@Primary
public class TPAServiceManagementService implements ServiceMgmtSvc {

    private ServiceMgmtSvcClient serviceManagementClient;

    public TPAServiceManagementService(ServiceMgmtSvcClient serviceManagementClient) {
        this.serviceManagementClient = serviceManagementClient;
    }

    public ServiceMgmtSvcDto getServiceById(Long id) throws NotFoundException, TPAServiceException, InternalServerException {
        try {
            return serviceManagementClient.getServiceById(id);
        } catch(NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch(TPAServiceException e) {
            throw new TPAServiceException(e.getMessage());
        } catch(Exception e) {
            throw new InternalServerException("Some error occurred.");
        }
    }
}
