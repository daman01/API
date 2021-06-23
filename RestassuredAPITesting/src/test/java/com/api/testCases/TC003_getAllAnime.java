package com.api.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.api.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_getAllAnime extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	void getAllAnimeSetup() throws InterruptedException {
		logger.info("********* Started TC003_getAllAnime**********");

		RestAssured.baseURI = "https://animechan.vercel.app/api/available/anime";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET);
	}

	// Test Case - Status code validation
	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	// Test Case - Response Time check
	@Test
	void checkResponseTime() {
		long responseTime = response.getTime();
		Assert.assertTrue(responseTime < 7000);

	}

	// Test Case - Check status Line
	@Test
	void checkstatusLine() {
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	// Test Case - Check Content-Type
	@Test
	void checkContentType() {
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	// Test Case - AnimeList Validation
	@Test
	void checkResposeBody() {
		String responseBody = response.getBody().asString();
		JsonPath jsonPathEvaluator = response.jsonPath();
		System.out.println("****" + jsonPathEvaluator.getList("$").size() + "****");// We can Assert the length if we
																					// have groundtruth
	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished TC003_getAllAnime  **********");
	}

}
