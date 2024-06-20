package Booking1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HealthCheck1 {
	
	@Test
	public void healthCheck1() {
		
		RequestSpecification spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
		
		Response response = RestAssured.given(spec).get("/ping");
		System.out.println("Response: " + response);
		//Response response = response.given()
	}

}
