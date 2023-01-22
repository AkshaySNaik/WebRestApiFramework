package com.webrestapi.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties prop;
	String filepath = System.getProperty("user.dir") + "//configration//config.properties";

	public ReadConfig() {

		prop = new Properties();
		File file = new File(filepath);

		try {
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getURL() {

		String url = prop.getProperty("URI");
		if (url != null)
			return url;
		else
			throw new RuntimeException("URL Not Found In Properties File");
	}

	public String getUserName() {

		String name = prop.getProperty("name");
		if (name != null)
			return name;
		else
			throw new RuntimeException("Name Not Found In Properties File");
	}
	
	public String getUserID() {

		String name = prop.getProperty("id");
		if (name != null)
			return name;
		else
			throw new RuntimeException("ID Not Found In Properties File");
	}
}
