package com.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

public class MasterAPIRequestTest {
	
	@Test
	public void verifyMasterAPI() {
		given()
		 .baseUri(getProperty("BASE_URI"))
		 .and()
		 .header("Authorization", getToken(FD))
		 .contentType("")
		 .log().all()
		.when()
		 .post("master")
		.then()
		 .log().all()
		 .statusCode(200)
		 .body("message", equalTo("Success"))
		 .time(lessThan(2000L))
		 .body("data", notNullValue())
		 .body("data", hasKey("mst_oem"))
		 .body("data", hasKey("mst_model"))
		 .body("$", hasKey("data"))
		 .body("data.mst_oem.size()", equalTo(2))
		 .body("data.mst_model.size()", greaterThan(0))
		 .body("data.mst_oem.id", everyItem(notNullValue()))
		 .body("data.mst_oem.name", everyItem(notNullValue()))
		 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema\\MasterAPIResponseSchema.json"));
	}
	
	@Test
	public void invalidTokenMasterAPI() {
		given()
		 .baseUri(getProperty("BASE_URI"))
		 .and()
		 //.header("Authorization", getToken(FD)) without header
		 .contentType("")
		 .log().all()
		.when()
		 .post("master")
		.then()
		 .statusCode(401);
	}
}
