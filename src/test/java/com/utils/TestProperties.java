package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
	
private static final Properties props = new Properties();

	public static void loadAllProperties() {
		try {
			FileInputStream Locator;
			Locator = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config/test.properties");
			props.load(Locator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static String getProperty(String key) {
		return props.getProperty(key);
	}

}
