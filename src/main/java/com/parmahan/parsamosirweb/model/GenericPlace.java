package com.parmahan.parsamosirweb.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GenericPlace {
    private String id;
    private String name;
    private String placeType;
    private Address address;
}
