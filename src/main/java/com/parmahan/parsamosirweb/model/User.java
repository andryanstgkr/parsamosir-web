package com.parmahan.parsamosirweb.model;

import lombok.Setter;

import java.util.Date;

import lombok.Getter;

@Setter
@Getter
public class User {

    private String id;
    private String userName;
    private String email;
    private String password;
    private Date lastUpdated;
    private Date dateCreated;
    
}
