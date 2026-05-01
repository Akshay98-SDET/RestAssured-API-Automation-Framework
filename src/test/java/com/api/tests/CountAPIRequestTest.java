package com.api.tests;

import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.api.constants.Role;

public class CountAPIRequestTest {
	
	@Test
	public void verifyCountAPIResponse() {
		given()
		 .baseUri(getProperty("BASE_URI"))
		 .and()
		 .header("Authorization", getToken(Role.FD))
		 .log().uri()
		 .log().method()
		 .log().headers()
		.when()
		 .get("/dashboard/count")
		.then()
		 .log().all()
		 .body("message", equalTo("Success"))
		 .statusCode(200)
		 .time(lessThan(1000L))
		 .body("data", notNullValue())
		 .body("data.size()", equalTo(3))
		 .body("data.label", everyItem(not(blankOrNullString())))
		.body("data.key", containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
		 .body(matchesJsonSchemaInClasspath("responseSchema/CountAPIResponseSchema.json"));
	}
	
	@Test
	public void countAPI_MissingAUth() {
		given()
		 .baseUri(getProperty("BASE_URI"))
		 .log().uri()
		 .log().method()
		 .log().headers()
		 .and()
		.when()
		 .get("/dashboard/count")
		.then()
		 .log().all()
		 .statusCode(401);
	}
	
}
