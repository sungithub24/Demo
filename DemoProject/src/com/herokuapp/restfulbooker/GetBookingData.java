package com.herokuapp.restfulbooker;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingData {

	@Test
	public void getBookingData() {
		// in Non-BDD style

		// Get response with booking ids
		Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/1");
		response.print();

		// Verify response 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");

		// Verify all fields
		SoftAssert SoftAssert = new SoftAssert();

		String actualFirstName = response.jsonPath().getString("firstname");
		SoftAssert.assertEquals(actualFirstName, "Mary", "firstname in response is different.");

		String actualLastName = response.jsonPath().getString("lastname");
		SoftAssert.assertEquals(actualLastName, "Brown", "lastname in response is different.");

		String totalprice = response.jsonPath().getString("totalprice");
		SoftAssert.assertEquals(totalprice, 674, "totalprice in response is different.");

		boolean depositpaid = response.jsonPath().getBoolean("depositpaid");
		SoftAssert.assertTrue(depositpaid, "depositpaid in response is different.");

		String actualCheckin = response.jsonPath().getString("bookingdates.checkin");
		SoftAssert.assertEquals(actualCheckin, "2022-11-28", "Checkin in response is different.");

		String actualCheckout = response.jsonPath().getString("bookingdates.checkout");
		SoftAssert.assertEquals(actualCheckout, "2023-02-08", "Checkout in response is different.");

		SoftAssert.assertAll();
		
	}
}
