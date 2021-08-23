package com.parmahan.parsamosirweb.handler;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectHandler<T>{

   public List<T> handleObjectList(List<T> tList) {
      ObjectMapper mapper = new ObjectMapper();
      mapper.findAndRegisterModules();

      List<T> actualTList = mapper.convertValue(tList, new TypeReference<List<T>>() {
      });
      System.out.println("tList: "+tList);
      return actualTList;
   }
}
