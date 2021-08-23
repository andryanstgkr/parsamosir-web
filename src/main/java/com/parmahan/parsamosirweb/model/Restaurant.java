package com.parmahan.parsamosirweb.model;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Restaurant extends GenericPlace {
    private String owner;
    private String type;
    private ArrayList<Menu> menus;
}
