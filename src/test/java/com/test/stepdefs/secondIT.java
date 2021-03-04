package com.test.stepdefs;



import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.reflect.TypeToken;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import io.cucumber.messages.internal.com.google.gson.Gson;
import io.cucumber.messages.internal.com.google.gson.JsonElement;
import io.cucumber.messages.internal.com.google.gson.JsonObject;
import io.cucumber.messages.internal.com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.apache.commons.lang3.StringUtils;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.json.*;

@io.cucumber.junit.platform.engine.Cucumber
public class secondIT {
    
    public static Response response;
	public static ValidatableResponse json;
	public static RequestSpecification request;
	public  static String id;
	 public static JsonPath jsonPathEvaluator;
	 
	 
	@Given("an employee exist in the database with id {string}")
	public void an_employee_exists_with_id(String ID){
		secondIT.id=ID;
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employee/";
		secondIT.request = RestAssured.given();
	}
	
	@When("user retrieves employee info by id")
	public void user_retrieves_employee_info_by_id(){
	    
		secondIT.response = secondIT.request.pathParam("id", secondIT.id).get("/{id}");
		  secondIT.jsonPathEvaluator = secondIT.response.jsonPath();
		assertNotNull(response);
		System.out.println("response: " + secondIT.response.getBody().prettyPrint());
	}
	
	@Then("the status code for get employee is {int}")
	public void verify_status(int sc){
		System.out.println("status code check..  " );
		secondIT.json = secondIT.response.then().statusCode(sc);
		System.out.println("status code:  " + secondIT.response.getStatusCode());
		assertEquals(sc,secondIT.response.getStatusCode());
	}

	/*
	
	//creating a list of maps from data table and printing all values from each map
	  @And("response includes the following employee info$")
	public void employee_response_equals(DataTable responseFields){
		  List<Map<String,String>> fields = responseFields.asMaps(String.class,String.class);
		  Iterator<Map<String, String>> it = fields.iterator();
		  while (it.hasNext()) {
				 Map<String, String> map = it.next(); 
				for (Map.Entry<String, String> entry : map.entrySet()) {
				  System.out.println(entry.getKey() + " = " + entry.getValue());
				 // assertEquals(secondIT.jsonPathEvaluator.get(entry.getKey()), entry.getValue());
				
             }
         }
	
	
	}  */
	
	 @And("response includes the following employee info$")
	public void employee_response_equals(Map<String, Object> ExpectedFields){
       Map<String, Object> actualFields =  ExpectedFields.keySet().stream().collect(Collectors.toMap(expectedKey -> expectedKey, expectedKey -> jsonPathEvaluator.get(expectedKey)));

          for (Map.Entry<String, Object> ent : actualFields.entrySet()) {
				  System.out.println(ent.getKey() + " = " + ent.getValue());
             }
        
/*		 
Gson gson = new Gson();
JsonElement element = gson.fromJson (jsonPathEvaluator.prettyPrint(), JsonElement.class);
JsonObject jsonObj = element.getAsJsonObject();
   Map<String, Object> map = Helper.createMapFromJsonObject(jsonObj);
		   for (Map.Entry<String, Object> entry : map.entrySet()) {
				//  System.out.println(entry.getKey() + " ## " + entry.getValue());
		       System.out.println(entry.getKey()+"@@@"+ entry.getValue());
				 // assertEquals(secondIT.jsonPathEvaluator.get(entry.getKey()), entry.getValue());			  
             }
  */
	    for (Map.Entry<String, Object> entry : ExpectedFields.entrySet()) {
				  System.out.println(entry.getKey() + " ** " + entry.getValue());
             }
	     assertThat(actualFields).containsAllEntriesOf(ExpectedFields); 

		 
		 
	}
	

}
