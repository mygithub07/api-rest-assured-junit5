package com.test.stepdefs;

import io.cucumber.messages.internal.com.google.gson.Gson;

import java.util.Map;

public  class ResponseContainer {
    
      private  Response response;

    class  Response {
         private Map<String, Map<String, Map<String, String>>> data;
         private String message;

         public String toString(){
          return "{data : " + data.toString() +"}, status: " + message + "}";
         }
    }

    public ResponseContainer fromJson(String jsonString) {
      return  new Gson().fromJson(jsonString, ResponseContainer.class);
    }

    public String toString(){
        return response.toString();
    }
}
