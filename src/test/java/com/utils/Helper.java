package com.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
	WebDriver driver;

	public Helper(WebDriver driver) {
		this.driver = driver;
	}

	public void clickElement(WebElement element) {
		waitForElementVisible(element, 10);
		try {
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendKey(WebElement element, String value) {
		element.sendKeys(value);
	}

	public void waitForElementVisible(WebElement element, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotInteractableException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public boolean isElementDisplayed(WebElement element) {
		boolean isDisplayed = false;

		try {
			waitForElementVisible(element, 10);
			isDisplayed = element.isDisplayed();
			return isDisplayed;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}

	}

	public String getFieldValue(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String value = (String) jsExecutor.executeScript("var text = arguments[0].value; return text;", element);
		return value;
	}

	public String getErrorMessage(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String value = (String) jsExecutor.executeScript("var text = arguments[0].validationMessage; return text;",
				element);
		return value;
	}

	public String getRandomText(int numberOfCharacters) {
		String text = "";
		for (int i = 0; i < numberOfCharacters; i++) {
			char c = (char) (getRandomNumber(97, 122));
			text = text + c;
		}
		return text;

	}

	public int getRandomNumber(int min, int max) {
		int b = (int) (Math.random() * (max - min + 1) + min);
		return b;
	}

	public String getText(WebElement element) {
		String text = element.getText();
		return text;
	}
}
