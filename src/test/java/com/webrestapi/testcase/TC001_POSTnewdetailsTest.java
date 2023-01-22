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

public class TC001_POSTnewdetailsTest extends BaseClass {

	String sname = "Ravi" + getRandomStringValue(2);
	String ssid = getRandomStringNumericValue(3);

	@SuppressWarnings("unchecked")
	@Test(priority = 1, dataProvider = "data")
	public void POSTDetailsTest(String datasupply) {

		log.info("************** Started TC001_PostDetailsTest Started *****************");

		String[] pstdata = datasupply.split(",");

		RestAssured.baseURI = URI;
		httprequest = RestAssured.given();

		JSONObject data = new JSONObject();
		data.put("id", pstdata[0]);
		data.put("username", pstdata[1]);
		data.put("email", pstdata[2]);
		data.put("password", pstdata[3]);
		data.put("phone", pstdata[4]);

		httprequest.header("Content-Type", "application/json");

		httprequest.body(data.toJSONString());

		response = httprequest.request(Method.POST, "v2/user");

		try {
			Thread.sleep(5000);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		log.info("** Checking Response Body **");

		String responsebody = response.getBody().asString();
		log.info("Response Body ==>" + responsebody);
		Assert.assertTrue(responsebody.contains(pstdata[0]));
		Assert.assertTrue(responsebody.contains("200"));
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
		JSONArray jsonarray = (JSONArray) jsonobject.get("postdata");
		Object[] arr = new Object[jsonarray.size()];

		for (int i = 0; i < jsonarray.size(); i++) {

			JSONObject data = (JSONObject) jsonarray.get(i);
			Object id = data.get("id");
			Object username = data.get("username");
			Object email = data.get("email");
			Object password = data.get("password");
			Object phone = data.get("phone");

			arr[i] = id + "," + username + "," + email + "," + password + "," + phone;
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

		if (responsetime > 5000) {
			log.warn("Response Time Is Greater Than 5000");
		}
		Assert.assertTrue(responsetime < 5000);
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

}
