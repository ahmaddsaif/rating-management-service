package dev.localservicesreview.ratingservice.thirdpartyclients.serviceMgmtSvc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceMgmtSvcDto {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String[] images;
    private String[] tags;
    private String[] locations;
    private String[] services;
    private String[] ratings;
}
