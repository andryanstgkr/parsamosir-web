package com.parmahan.parsamosirweb.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parmahan.parsamosirweb.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class District extends BaseEntity {

    @ToString.Include
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("code")
    private int code;

    @JsonProperty("regency")
    private Regency regency;

}
