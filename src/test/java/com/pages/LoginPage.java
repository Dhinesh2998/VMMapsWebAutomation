package com.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utils.Helper;
import com.utils.TestProperties;

public class LoginPage extends BasePage {

	WebDriver driver;
	Helper helper;

	@FindBy(id = "login-email")
	private WebElement loginEmailInputField;

	@FindBy(id = "login-password")
	private WebElement loginPasswordInputField;

	@FindBy(id = "login-submit-text")
	private WebElement loginSubmitButton;

	@FindBy(id = "registerHere")
	private WebElement registerHereButton;

	@FindBy(id = "signinFormError")
	private WebElement errorMessageElement;

	@FindBy(xpath = "//span[text()='Dashboard']")
	private WebElement dashboardElement;

	@FindBy(id = "signupToggle")
	private WebElement signUpToggle;

	@FindBy(xpath = "//div[@id='signupToggle'][contains(@class,'active')]")
	private WebElement signupToggleActive;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		helper = new Helper(driver);
	}

	public void verifyAllFieldsDisplayedInLoginPage() {
		boolean isEmailFieldDisplayed = helper.isElementDisplayed(loginEmailInputField);
		boolean isPasswordFieldDisplayed = helper.isElementDisplayed(loginPasswordInputField);
		assertTrue(isEmailFieldDisplayed);
		assertTrue(isPasswordFieldDisplayed);
	}

	public void clickSignUpToggle() {
		helper.clickElement(signUpToggle);
		assertTrue(helper.isElementDisplayed(signupToggleActive));
	}

	public void sendTextToEmailField(String text) {
		helper.sendKey(loginEmailInputField, text);
	}

	public void sendTextToPasswordField(String text) {
		helper.sendKey(loginPasswordInputField, text);
	}

	public void verifyEmailAndPasswordFieldsAreEditable() {
		sendTextToEmailField("DummyEmail");
		String emailValue = helper.getFieldValue(loginEmailInputField);
		assertEquals(emailValue, "DummyEmail", "Verifying Entered Text in Email field is displayed");

		sendTextToPasswordField("DummyPassword");
		String passwordValue = helper.getFieldValue(loginPasswordInputField);
		assertEquals(passwordValue, "DummyPassword", "Verifying Entered Text in Password field is displayed");

	}

	public void verifyLoginWithoutEmailAndPassword() {
		helper.clickElement(loginSubmitButton);
		String emailFieldErrorMessage = helper.getErrorMessage(loginEmailInputField);
		assertEquals(emailFieldErrorMessage, "Please fill out this field.",
				"Verifying Email field error message when empty");
		sendTextToEmailField("test@gmail.com");

		String passwordFieldErrorMessage = helper.getErrorMessage(loginPasswordInputField);
		assertEquals(passwordFieldErrorMessage, "Please fill out this field.",
				"Verifying password field error message when empty");
	}

	public void verifyLoginWithWrongEmailAddress() {
		sendTextToEmailField(TestProperties.getProperty("WrongEmail"));
		sendTextToPasswordField(TestProperties.getProperty("Password"));
		helper.clickElement(loginSubmitButton);
		assertTrue(helper.isElementDisplayed(errorMessageElement));
		String emailErrorText = helper.getText(errorMessageElement);
		assertEquals(emailErrorText, "Invalid email, please signup and try again");
	}

	public void verifyLoginWithWrongPassword() {
		sendTextToEmailField(TestProperties.getProperty("EmailAddress"));
		sendTextToPasswordField(TestProperties.getProperty("WrongPassword"));
		helper.clickElement(loginSubmitButton);
		assertTrue(helper.isElementDisplayed(errorMessageElement));
		String passwordErrorText = helper.getText(errorMessageElement);
		assertEquals(passwordErrorText, "Incorrect emailId or Password, Try again");

	}

	public void verifyLoginWithValidCredentials() {
		sendTextToEmailField(TestProperties.getProperty("EmailAddress"));
		sendTextToPasswordField(TestProperties.getProperty("Password"));
		helper.clickElement(loginSubmitButton);
		assertTrue(helper.isElementDisplayed(dashboardElement));
		assertEquals(driver.getCurrentUrl(), TestProperties.getProperty("loggedInURL"),
				"Verifying page navigated to Dashboard page");
	}

}
