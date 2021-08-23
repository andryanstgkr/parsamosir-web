package com.parmahan.parsamosirweb.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GenericObject {
    private Timestamp createdDate;
    private Timestamp updateDate;
}
