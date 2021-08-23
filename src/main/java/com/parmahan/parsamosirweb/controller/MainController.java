package com.parmahan.parsamosirweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    public String index(Model model){
        
        model.addAttribute("header", "Berkunjung ke Samosir");
        return "index";
    }
}
