package com.parmahan.parsamosirweb.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Place extends BaseEntity {
    private String name;
    private String owner;
    private String phoneNumber;
    private String email;
    private String description;
    private String relatedLink;
    private String villageName;
    private String street;
    private String districtName;
    private String regencyName;
    private String postalCode;
    private PlaceType placeType;
}
