package com.parmahan.parsamosirweb.handler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parmahan.parsamosirweb.constant.ResponseConstant;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    static Logger logger = LoggerFactory.getLogger(ResponseHandler.class);

    public static List<Object> handleResponseEntityList(
            ResponseEntity<LinkedHashMap<String, Object>> responseEntity) {
        LinkedHashMap<String, Object> responseBody = responseEntity.getBody();
        List<Object> objectList = new ArrayList<Object>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            JSONObject jsonObject = new JSONObject(responseBody);
            JSONArray jsonArray = new JSONArray();
            jsonArray = jsonObject.getJSONArray(ResponseConstant.DATA);

            for (int i = 0; i < jsonArray.length(); i++) {
                String strObject = jsonArray.get(i).toString();

                Object object = mapper.readValue(strObject, Object.class);

                objectList.add(object);

            }

        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException: " + e.getMessage());

        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }

        return objectList;
    }

    public static Object handleResponseEntity(
            ResponseEntity<LinkedHashMap<String, Object>> responseEntity) {
        Object object = new Object();
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            object = mapper.convertValue(responseEntity.getBody().get(ResponseConstant.DATA),
                    Object.class);

        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }

        return object;
    }
}
