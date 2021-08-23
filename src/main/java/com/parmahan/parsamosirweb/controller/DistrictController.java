package com.parmahan.parsamosirweb.controller;

import java.util.LinkedHashMap;
import java.util.List;

import com.parmahan.parsamosirweb.constant.PageConstant;
import com.parmahan.parsamosirweb.handler.ResponseHandler;
import com.parmahan.parsamosirweb.model.admin.District;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class DistrictController {
    Logger logger = LoggerFactory.getLogger(DistrictController.class);
    @Autowired
    private RestTemplate restTemplate;

    @Value("${backend-api.url}")
    private String BACKEND_API_URL;

    @GetMapping("/districts")
    @SuppressWarnings("unchecked")
    public String getDistricts(Model model) {

        ResponseEntity<LinkedHashMap<String, Object>> responseEntity = restTemplate.exchange(
                BACKEND_API_URL + "/admin/district/village-count-by-district", HttpMethod.GET, null,
                new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {});

        List<District> districtList =
                (List<District>) (Object) ResponseHandler.handleResponseEntityList(responseEntity);
        logger.info(districtList.toString());
        model.addAttribute("districtList", districtList);
        return PageConstant.ADMIN_DISTRICTS;
    }
}
