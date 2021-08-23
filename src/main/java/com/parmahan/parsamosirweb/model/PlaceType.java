package com.parmahan.parsamosirweb.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class PlaceType extends BaseEntity {
    private String name;

    private PlaceCategory placeCategory;

    private String description;
}
