package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.utils.TestProperties;

public class BasePage {
	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	PageFactory.initElements(driver, this);

	}

	public void navigateToLoginURL() {
		driver.get(TestProperties.getProperty("LoginURL"));
	}
}
