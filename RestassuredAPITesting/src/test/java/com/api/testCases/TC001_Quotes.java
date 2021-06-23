package com.api.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import io.restassured.path.json.JsonPath;
import com.api.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_Quotes extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	void quotesSetup() throws InterruptedException {

		logger.info("********* Started TC001_testing_AQuotesApi  **********");

		RestAssured.baseURI = "https://animechan.vercel.app/api";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/random");

	}

	// Test Case - Status code validation
	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode(); // Gettng status code
		Assert.assertEquals(statusCode, 200);
	}

	// Test Case - Status line validation
	@Test
	void checkstatusLine() {
		String statusLine = response.getStatusLine(); // get the status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	// Test Case - Content Type validation
	@Test
	void checkContentType() {
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	// Test Case - Response Body Validation. We can always write POJO classes for
	// the response under Base package if the response is complex
	@Test
	void checkResposeBody() {

		String responseBody = response.getBody().asString();
		JsonPath jsonPathEvaluator = response.jsonPath();
		String animeName = jsonPathEvaluator.get("anime");
		String characterName = jsonPathEvaluator.get("character");
		String quote = jsonPathEvaluator.get("quote");
		Assert.assertNotNull(animeName);
		Assert.assertNotNull(characterName);
		Assert.assertNotNull(quote);
	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished TC001_testing_AQuotesApi **********");
	}

}
