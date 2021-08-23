package com.parmahan.parsamosirweb.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerVisit {
    private String id;
    private Date date;
    private String comment;
    private int rate;
    private Date visitDate;
    private Customer customer;
    private GenericPlace place;
    private int lengthOfStay;
}
