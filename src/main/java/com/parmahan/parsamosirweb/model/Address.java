package com.parmahan.parsamosirweb.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {
    private String id;
    private String streetAddress;
    private String district;
    private String city;
    private String state;
    private String zipCode;
    private String addressOwner;
}
