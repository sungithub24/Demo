package com.herokuapp.restfulbooker;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class HealthCheckTest {

	@Test
	public void healthCheckTest() {
		// in BDD style
		given().when().get("https://restful-booker.herokuapp.com/ping").then().assertThat().statusCode(201);
	}

	
	@Test
	public void printhealthCheckTest() {
		
		//create spec
		RequestSpecification spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();

		Response response = RestAssured.given(spec).get("/ping");
		
		System.out.println("Response: " + response);
		System.out.println();
		
		Headers headers = response.getHeaders();
		System.out.println("Headers: " + headers);
		System.out.println();
		
		Cookies cookies = response.getDetailedCookies();
		System.out.println("Cookies: " + cookies);
		System.out.println();
		
	}
}
