package com.webrestapi.testcase;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC004_DELETEdetailsTest extends BaseClass {

	@Test(priority = 1, dataProvider = "data")
	public void DELETEDetailsTest(String data) {

		log.info("********** Started TC004_DeleteDetailsTest **************");

		String[] dltdata = data.split(",");

		RestAssured.baseURI = URI;
		httprequest = RestAssured.given();

		response = httprequest.request(Method.DELETE, "v2/user/" + dltdata[1]);

		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("** Checking Response Body **");

		String responsebody = response.getBody().asString();
		log.info("Response Body ==>" + responsebody);
		Assert.assertTrue(responsebody.contains("200"));
		Assert.assertTrue(responsebody.contains(dltdata[1]));
	}

	@DataProvider(name = "data")
	public Object[] dataSupplier() {

		Object object = null;

		JSONParser jsonparser = new JSONParser();
		String filename = System.getProperty("user.dir") + "//jsonfile//datasheet.json";
		try {

			FileReader reader = new FileReader(filename);
			object = jsonparser.parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonobject = (JSONObject) object;
		JSONArray jsonarray = (JSONArray) jsonobject.get("putdata");
		Object[] arr = new Object[jsonarray.size()];

		for (int i = 0; i < jsonarray.size(); i++) {

			JSONObject data = (JSONObject) jsonarray.get(i);
			Object id = data.get("id");
			Object username = data.get("username");
			Object frstname = data.get("firstname");
			Object email = data.get("email");
			Object password = data.get("password");
			Object phone = data.get("phone");

			arr[i] = id + "," + username + "," + frstname + "," + email + "," + password + "," + phone;
		}
		return arr;
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

		if (Integer.parseInt(responsecontlength) > 500) {
			log.warn("Content Length Is More Than 500");
		}

		Assert.assertTrue(Integer.parseInt(responsecontlength) < 500);

	}
}
