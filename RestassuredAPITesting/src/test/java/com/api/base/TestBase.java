/*
 * summary: Used Test case for logging the status using Log4j

 */

package com.api.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response response;

	public Logger logger;

	@BeforeClass
	public void setup() {

		logger = Logger.getLogger("API");// added Logger
		PropertyConfigurator.configure("Log4j.properties"); // added logger
		logger.setLevel(Level.DEBUG);

	}

}
