package com.parmahan.parsamosirweb.model;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Menu {
    private String id;
    private String name;
    private String price;
    private boolean isHalal;
    private ArrayList<String> picture;
}
