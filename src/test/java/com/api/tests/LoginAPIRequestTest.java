package com.api.tests;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.LoginUserDetails;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;


public class LoginAPIRequestTest 
{
	
@Test(description = "Login functionality with valid credentials", groups = {"Sanity", "E2E"})
public void loginTest() 
{
	LoginUserDetails loginUserDetails = new LoginUserDetails("iamfd", "password");
		
given()
	.baseUri(getProperty("BASE_URI"))
	.and()
	.contentType(ContentType.JSON)
	.and()
	.accept(ContentType.JSON)
	.body(loginUserDetails)
.when()
	.post("login")
.then()
	.log().all()
	.body("message", equalTo("Success"))
	.time(lessThan(2000L))
	.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginresponseSchema.json"))
	.body("data.token", Matchers.notNullValue());
}
}
