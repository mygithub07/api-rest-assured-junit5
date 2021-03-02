package com.test.stepdefs;



import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import io.restassured.RestAssured;
import org.apache.commons.lang3.StringUtils;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static org.junit.jupiter.api.Assertions.*;

@io.cucumber.junit.platform.engine.Cucumber
public class RestAssuredIT {
    
    public static Response response;
	public static ValidatableResponse json;
	public static RequestSpecification request;
	public  static String ISBN;
	//private String ENDPOINT_GET_BOOK_BY_ISBN = "https://www.googleapis.com/books/v1/volumes";

	
	@Given("a book exists with an isbn of {string}")
	public void a_book_exists_with_isbn(String isbn){
		RestAssuredIT.ISBN = isbn;
		RestAssured.baseURI = "https://www.googleapis.com/books/v1/volumes";
		RestAssuredIT.request = RestAssured.given();
		
	}
	
	@When("a user retrieves the book by isbn")
	public void a_user_retrieves_the_book_by_isbn(){
		RestAssuredIT.response =  RestAssuredIT.request.queryParam("q", RestAssuredIT.ISBN).get();
		assertNotNull(response);
		System.out.println("response: " + RestAssuredIT.response.getBody().prettyPrint());
	}
	
	@Then("the status code is {int}")
	public void verify_status_code(int statusCode){
		RestAssuredIT.json = RestAssuredIT.response.then().statusCode(statusCode);
		System.out.println("status code:  " + RestAssuredIT.response.getStatusCode());
		assertEquals(statusCode,RestAssuredIT.response.getStatusCode());
	}
	
	@And("response includes the following$")
	public void response_equals(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				RestAssuredIT.json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
			}
			else{
				RestAssuredIT.json.body(field.getKey(), equalTo(field.getValue()));
			}
		}
	}

}
