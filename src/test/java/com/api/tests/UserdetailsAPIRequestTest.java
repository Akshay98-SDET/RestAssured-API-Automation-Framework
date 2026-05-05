package com.api.tests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import static com.api.utils.SpecUtil.*;

import io.restassured.module.jsv.JsonSchemaValidator;

public class UserdetailsAPIRequestTest 
{	
	@Test(description = "Fetching user details", groups = {"Sanity", "E2E"})
	public void userdetails() 
	{		
		given()
			.spec(requestspecWithAuth(FD))
		.when()
			.get("userdetails")
		.then()
			.spec(responseSpec_OK())
			.body("message", Matchers.equalTo("Success"))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/userDetailsReponseSchema.json"));
	}
}
