package com.test.stepdefs;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

//import io.github.artsok.RepeatedIfExceptionsTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;



import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


 import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

import org.json.*;

//import me.jaksa.Unreliable;
//import static me.jaksa.Unreliable.retryOn;

@io.cucumber.junit.platform.engine.Cucumber
public class secondIT {
    
    public static Response response;
	public static ValidatableResponse json;
	public static RequestSpecification request;
	public  static String id;
	 public static JsonPath jsonPathEvaluator;
	//Unreliable ur = new Unreliable();
	
  
	@Given("an employee exist in the database with id {string}")
	public void an_employee_exists_with_id(String ID){
		secondIT.id=ID;
		//RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employee/";
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		secondIT.request = RestAssured.given();
	}
	
	@When("user retrieves employee info by id")
	public void user_retrieves_employee_info_by_id(){
	    
		secondIT.response = secondIT.request.pathParam("id", secondIT.id).get("/employee/{id}");
		secondIT.jsonPathEvaluator = secondIT.response.jsonPath();
		assertNotNull(response);
		//System.out.println("response: " + secondIT.response.getBody().prettyPrint());
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
    Map<String, Object> actualFields =  ExpectedFields.keySet().stream().collect(Collectors.toMap(expectedKey -> expectedKey, expectedKey -> jsonPathEvaluator.get(expectedKey).toString()));

    assertThat(actualFields).containsAllEntriesOf(ExpectedFields); 
    
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

	}
//@Disabled	
@Given("an employee record is created with values")
	public void create_employee_record(Map<String, Object> ToBeCreatedFields){
	 	//to abort the test use this
	 	//Assumptions.assumeTrue((false));
	      JSONObject jsonObj = new JSONObject();
	      
	 	  for (Map.Entry<String, Object> entry : ToBeCreatedFields.entrySet()) {
				 jsonObj.put(entry.getKey(),entry.getValue());					 
             }
	 	  
                     Response  postResponse =   RestAssured.given()
                             .contentType("application/json")  //another way to specify content type
                             .body(jsonObj.toString())   // use jsonObj toString method
                             .when()
                             .post("/create");
	 	                    		 	  
	          assertThatJson(postResponse.getBody().prettyPrint())
					  .inPath("$.data.salary").isString().isEqualTo("320000");
	           assertThatJson(postResponse.getBody().prettyPrint())
					  .inPath("$.data.age").isString().isEqualTo("41");
	           assertThatJson(postResponse.getBody().prettyPrint())
					  .inPath("$.status").isEqualTo("success");
	            assertThatJson(postResponse.getBody().prettyPrint())
					  .inPath("$.message").isEqualTo("Successfully! Record has been added.");
	            
	 }
	
	 @Given("an employee record is created with other values")
	public void create_another_employee_record(Map<String, Object> ToBeCreatedFields){
	 	
	 	  JSONObject jsonObj = new JSONObject();
	 	  for (Map.Entry<String, Object> entry : ToBeCreatedFields.entrySet()) {
				 jsonObj.put(entry.getKey(),entry.getValue());					 
             }

                        Response  postResponse =   RestAssured.given()
                             .contentType("application/json")  //another way to specify content type
                             .body(jsonObj.toString())   // use jsonObj toString method
                             .when()
                             .post("/create");
	 	
	          assertThatJson(postResponse.getBody().prettyPrint())
					  .inPath("$.data.salary").isString().isEqualTo("500000");
	           assertThatJson(postResponse.getBody().prettyPrint())
					  .inPath("$.data.age").isString().isEqualTo("50");
	           assertThatJson(postResponse.getBody().prettyPrint())
					  .inPath("$.status").isEqualTo("success");
	            assertThatJson(postResponse.getBody().prettyPrint())
					  .inPath("$.message").isEqualTo("Successfully! Record has been added.");
	            
	 }

  
	@Given("test fails because we want to test that way")
	 
	   public void  TestFailingTests()throws IOException {
	 	
	 	throw new IOException("Exception in I/O operation");
	    }
	 
}
