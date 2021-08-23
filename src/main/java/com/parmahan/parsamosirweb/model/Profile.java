package com.parmahan.parsamosirweb.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profile {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Address address;
}
