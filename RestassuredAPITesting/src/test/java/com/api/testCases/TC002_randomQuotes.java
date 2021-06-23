package com.api.testCases;

import org.json.simple.JSONObject;

import org.testng.Assert;
import org.testng.annotations.*;

import com.api.base.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_randomQuotes extends TestBase {

	String anime = "Bleach"; // We can populate this from external file as well if want to scale
	String charcterName = "Hatake Kakashi";// We can populate this from external file as well

	@BeforeClass
	void randomQuotesSetup() throws InterruptedException {
		logger.info("********* Started TC002_randomQuotes **********");

		RestAssured.baseURI = "https://animechan.vercel.app/api";
		httpRequest = RestAssured.given();

	}

	// Test Case - An E2E test case for randomQuotes API(Happy Path) checking for
	// the status code, time taken and response body
	@Test
	void checkResposeBodyForRandomQuotesE2E() {
		logger.info("********* Started_checkResposeBodyForRandomQuotesE2E **********");
		response = httpRequest.request(Method.GET, "/quotes");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		long responseTime = response.getTime();
		Assert.assertTrue(responseTime < 7000);
		JsonPath jsonPathEvaluator = response.jsonPath();
		int noOfQuotes = jsonPathEvaluator.getList("$").size();
		Assert.assertEquals(noOfQuotes, 10); // Asserts the no. of quotes returned
	}

	// Test Case - getQuotes by anime title
	@Test
	void checkResponseforQuotesbyTitle() {
		logger.info("********* checkResponseforQuotesbyTitle **********");
		response = httpRequest.request(Method.GET, ("/quotes/anime?title=" + anime));
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		JsonPath jsonPathEvaluator = response.jsonPath();
		String Anime = jsonPathEvaluator.getString("anime[0]");// To verify by that the quotes returned are of requested
																// anime
		Assert.assertEquals(Anime, anime);

	}

	// Test Case - getQuotes by anime character
	@Test
	void checkResponseforQuotesbyCharacter() {
		logger.info("********* checkResponseforQuotesbyCharacter **********");
		response = httpRequest.request(Method.GET, ("/quotes/character?name=" + charcterName));
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		JsonPath jsonPathEvaluator = response.jsonPath();
		String Character = jsonPathEvaluator.getString("character[0]");// To verify by that the quotes returned are of
																		// requested anime
		Assert.assertEquals(Character, charcterName);
	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished TC002_randomQuotes  **********");
	}

}
