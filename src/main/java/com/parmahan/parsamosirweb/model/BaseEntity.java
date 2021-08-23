package com.parmahan.parsamosirweb.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseEntity {

    @JsonProperty("id")
    private String id;

    @JsonProperty("createdDate")
    private LocalDateTime createdDate;

    @JsonProperty("updatedDate")
    private LocalDateTime updatedDate;

    @ToString.Exclude
    @JsonProperty("updatedBy")
    private String updatedBy = "Admin";

    @ToString.Exclude
    @JsonProperty("createdBy")
    private String createdBy = "Admin";

    @JsonProperty("isActive")
    private Boolean isActive = true;

    @JsonProperty("isDeleted")
    private Boolean isDeleted = false;

}
