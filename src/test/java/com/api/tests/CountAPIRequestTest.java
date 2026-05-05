package com.api.tests;


import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

public class CountAPIRequestTest {
	
	@Test
	public void verifyCountAPIResponse() {
		given()
			.spec(SpecUtil.requestspecWithAuth(Role.FD))
		.when()
		 .get("/dashboard/count")
		.then()
		 .spec(SpecUtil.responseSpec_OK())
		 .body("message", equalTo("Success"))
		 .body("data", notNullValue())
		 .body("data.size()", equalTo(3))
		 .body("data.label", everyItem(not(blankOrNullString())))
		.body("data.key", containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
		 .body(matchesJsonSchemaInClasspath("responseSchema/CountAPIResponseSchema.json"));
	}
	
	@Test
	public void countAPI_MissingAUth() {
		given()
		 .spec(SpecUtil.requestSpec())
		.when()
		 .get("/dashboard/count")
		.then()
		 .spec(SpecUtil.responseSpec_TEXT(401));
	}
	
}
