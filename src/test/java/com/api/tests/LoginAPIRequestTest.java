package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.pojo.LoginUserDetails;
import static com.api.utils.SpecUtil.*;

import io.restassured.module.jsv.JsonSchemaValidator;


public class LoginAPIRequestTest 
{
	
@Test(description = "Login functionality with valid credentials", groups = {"Sanity", "E2E"})
public void loginTest() 
	{
	  LoginUserDetails loginUserDetails = new LoginUserDetails("iamfd", "password");	
    given()
	  .spec(requestSpec(loginUserDetails))
	.when()
	  .post("login")
	.then()
	  .spec(responseSpec_OK())
	  .body("message", equalTo("Success"))
	  .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginresponseSchema.json"));
	}
}
