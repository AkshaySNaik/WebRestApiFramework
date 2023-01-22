package com.webrestapi.testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC008_GETsoldTest extends BaseClass {

	@Test(priority = 1)
	public void GETSoldTest() {

		log.info("********** Started TC008_GetSoldDetails **************");

		RestAssured.baseURI = URI;
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET, "v2/pet/findByStatus?status=sold");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void checkResponseBody() {

		log.info("** Checking Response Body **");

		String responsebody = response.getBody().asString();
		log.info("Response Body ==>" + responsebody);
		Assert.assertTrue(responsebody != null);
		Assert.assertTrue(responsebody.contains("sold"));
		Assert.assertTrue(responsebody.contains("12393158"));
		Assert.assertTrue(responsebody.contains("quis sed"));
	}

	@Test(priority = 3)
	public void checkStatusCode() {

		log.info("** Checking Status Code **");

		int responsecode = response.getStatusCode();
		log.info("Response Status Code ==>" + responsecode);
		Assert.assertEquals(responsecode, 200);
	}

	@Test(priority = 4)
	public void checkResponseTime() {

		log.info("** Checking Response Time **");

		long responsetime = response.getTime();
		log.info("Response Time ==>" + responsetime);

		if (responsetime > 6000) {
			log.warn("Response Time Is Greater Than 6000");
		}
		Assert.assertTrue(responsetime < 6000);
	}

	@Test(priority = 5)
	public void checkStatusLine() {

		log.info("** Checking Status Line **");

		String responsestatusline = response.getStatusLine();
		log.info("Response Status Line ==>" + responsestatusline);
		Assert.assertEquals(responsestatusline, "HTTP/1.1 200 OK");

	}

	@Test(priority = 6)
	public void checkContentType() {

		log.info("** Checking Content Type **");

		String responsecontenttype = response.header("Content-Type");
		log.info("Response Content Type ==>" + responsecontenttype);
		Assert.assertEquals(responsecontenttype, "application/json");
	}

	@Test(priority = 7)
	public void checkServer() {

		log.info("** Checking Server Type **");

		String responseserver = response.header("Server");
		log.info("Response Server Type ==>" + responseserver);
		Assert.assertEquals(responseserver, "Jetty(9.2.9.v20150224)");

	}

	@Test(priority = 8, enabled = false)
	public void checkContentEncoding() {

		log.info("** Checking Content Encoding **");

		String responsecontencod = response.header("Content-Encoding");
		log.info("Response Content Encoding ==>" + responsecontencod);
		Assert.assertEquals(responsecontencod, "gzip");

	}

	@Test(priority = 9, enabled = false)
	public void checkContentLength() {

		log.info("** Checking Content Length **");

		String responsecontlength = response.header("Content-Length");
		log.info("Response Content Length ==>" + responsecontlength);

		if (Integer.parseInt(responsecontlength) < 1000) {
			log.warn("Content Length Is Less Than 1000");
		}

		Assert.assertTrue(Integer.parseInt(responsecontlength) > 1000);

	}

	@Test(priority = 10, enabled = false)
	public void checkCookie() {

		log.info("** Checking Cookie **");

		String responsecookie = response.getCookie("PHPSESSID");
		log.info("Response Cookie ==>" + responsecookie);
		// Assert.assertEquals(responsecookie, "");
	}

}
