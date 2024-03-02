package com.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.utils.Helper;
import com.utils.TestProperties;

public class ForgotPasswordPage extends BasePage {
	WebDriver driver;
	Helper helper;

	@FindBy(id = "Forgot-Password")
	private WebElement forgotPassword;

	@FindBy(id = "login-email")
	private WebElement loginEmailInputField;

	@FindBy(id = "Resetotp")
	private WebElement otpField;

	@FindBy(id = "Resetpassword")
	private WebElement resetPassword;

	@FindBy(id = "reset-login-otp-text")
	private WebElement changeButton;

	@FindBy(id = "ResetError")
	private WebElement resetError;

	@FindBy(id = "signinFormError")
	private WebElement signInFormError;

	@FindBy(xpath = "//div[@id='Resetotp']/input")
	private WebElement otpInputField;

	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		helper = new Helper(driver);
	}

	public void moveToForgotPasswordPage() {
		helper.sendKey(loginEmailInputField, TestProperties.getProperty("EmailAddress"));
		helper.clickElement(forgotPassword);
	}

	public void verifyAllFieldsAreDisplayed() {

		assertTrue(helper.isElementDisplayed(otpField));
		assertTrue(helper.isElementDisplayed(resetPassword));
	}

	public void verifyChangePasswordWithoutOtp() {
		helper.clickElement(changeButton);
		helper.isElementDisplayed(resetError);
	}

	public void verifyChangePasswordWithoutValues() {
		helper.clickElement(changeButton);
		helper.isElementDisplayed(resetError);
	}

	public void verifyChangePasswordWithWrongOTP() {
		helper.sendKey(otpInputField, "123456");
		helper.clickElement(changeButton);
		helper.isElementDisplayed(resetError);
	}

	public void verifyMoveToForgotPasswordWithUnregisteredEmail() {
		helper.sendKey(loginEmailInputField, "dhinesh123@gmail.com");
		helper.clickElement(forgotPassword);
		helper.isElementDisplayed(signInFormError);

	}

}
