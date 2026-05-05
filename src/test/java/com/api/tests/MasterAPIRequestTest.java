package com.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

public class MasterAPIRequestTest {
	
	@Test
	public void verifyMasterAPI() {
		given()
			.spec(SpecUtil.requestspecWithAuth(FD))
		.when()
		 .post("master")
		.then()
		 .spec(SpecUtil.responseSpec_OK())
		 .body("message", equalTo("Success"))
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
			.spec(SpecUtil.requestSpec())
		.when()
		 .post("master")
		.then()
		 .spec(SpecUtil.responseSpec_TEXT(401));
	}
}
