package dev.localservicesreview.ratingservice.thirdpartyclients.serviceMgmtSvc;

import java.util.UUID;
import dev.localservicesreview.ratingservice.exceptions.NotFoundException;
import dev.localservicesreview.ratingservice.exceptions.TPAServiceException;
import dev.localservicesreview.ratingservice.models.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Service
public class ServiceMgmtSvcClient {
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${serviceMgmtSvc.base.url}")
    private String svcMgmtSvcBaseUrl = "https://demoServiceMgtmSvc.com";

    @Value("${serviceMgmtSvc.services}")
    private String svcMgmtServices = "/services";

    public ServiceMgmtSvcClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public ServiceMgmtSvcDto getServiceById(UUID id) throws NotFoundException, TPAServiceException {
        try {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<ServiceMgmtSvcDto> response =
                    restTemplate.getForEntity(svcMgmtSvcBaseUrl + svcMgmtServices, ServiceMgmtSvcDto.class, id);
            ServiceMgmtSvcDto serviceMgmtSvcDto = response.getBody();

            if(serviceMgmtSvcDto == null)
                throw new NotFoundException("Service with id: " + id + " not found");

            return serviceMgmtSvcDto;
        } catch (Exception e) {
            System.out.println(e);
            throw new TPAServiceException("Error occurred while calling service management service.");
        }
    }
}
