package com.api.utils;

import static com.api.constants.Role.*;
import static io.restassured.RestAssured.*;
import com.api.constants.Role;
import com.api.pojo.LoginUserDetails;

import io.restassured.http.ContentType;


public class AuthTokenProvider {

	private AuthTokenProvider() {

	}

	
	public static String getToken(Role role) {
		
		LoginUserDetails loginUserDetails = null;
		
		if (role == FD) {
			loginUserDetails = new LoginUserDetails("iamfd", "password");
		}
		else if (role == SUP) {
			loginUserDetails = new LoginUserDetails("iamsup", "password");
		} else if (role == ENG) {
			loginUserDetails = new LoginUserDetails("iameng", "password");
		} else if (role == QC) {
			loginUserDetails = new LoginUserDetails("iamqc", "password");
		}

		String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON)
				.body(loginUserDetails).when().post("login").then().log().body().extract().jsonPath().getString("data.token");

		return token;
	}

}
