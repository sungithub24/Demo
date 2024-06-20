package com.herokuapp.restfulbooker;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.List;

public class CreateBooking {

	@Test
	public void createBooking() {

		// Create JSON Body
		JSONObject body = new JSONObject();
		body.put("firstname", "John");
		body.put("lastname", "Lewis");
		body.put("totalprice", 10);
		body.put("depositpaid", true);

		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2024-01-01");
		bookingdates.put("checkout", "2024-01-05");
		body.put("bookingdates", bookingdates);

		body.put("additionalneeds", "Breakfast");

		// Get Response
		Response response = given().contentType(ContentType.JSON).body(body.toString())
				.post("https://restful-booker.herokuapp.com/booking");
		response.print();

		//Verify Response 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");
		
		//Verify all fields
		SoftAssert SoftAssert = new SoftAssert();

		String actualFirstName = response.jsonPath().getString("booking.firstname");
		SoftAssert.assertEquals(actualFirstName, "John", "firstname in response is different.");
				
		String actualLastName = response.jsonPath().getString("booking.lastname");
		SoftAssert.assertEquals(actualLastName, "Lewis", "lastname in response is different.");

		int totalprice = response.jsonPath().getInt("booking.totalprice");
		SoftAssert.assertEquals(totalprice, 10, "totalprice in response is different.");

		boolean depositpaid = response.jsonPath().getBoolean("booking.depositpaid");
		SoftAssert.assertTrue(depositpaid, "depositpaid in response is different.");

		String actualCheckin = response.jsonPath().getString("booking.bookingdates.checkin");
		SoftAssert.assertEquals(actualCheckin, "2024-01-01", "Checkin in response is different.");

		String actualCheckout = response.jsonPath().getString("booking.bookingdates.checkout");
		SoftAssert.assertEquals(actualCheckout, "2024-01-05", "Checkout in response is different.");

		SoftAssert.assertAll();
	}
}
