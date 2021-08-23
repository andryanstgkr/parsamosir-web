package com.parmahan.parsamosirweb.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parmahan.parsamosirweb.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Regency extends BaseEntity {

    @JsonProperty("name")
    private String name;
}
