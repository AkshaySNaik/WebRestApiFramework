package com.webrestapi.testcase;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.webrestapi.utility.ReadConfig;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	public ReadConfig config = new ReadConfig();
	String URI = config.getURL();

	public static RequestSpecification httprequest;
	public static Response response;
	public static Logger log = LogManager.getLogger(BaseClass.class.getName());

	public String getRandomStringValue(int number) {

		return (RandomStringUtils.randomAlphabetic(number));
	}

	// Get Random String Numeric Data
	public String getRandomStringNumericValue(int number) {

		return (RandomStringUtils.randomNumeric(number));
	}

	// Get Random Integer Value
	public int getRandomIntValue(int number) {

		Random random = new Random();
		return (random.nextInt(number));
	}
}
