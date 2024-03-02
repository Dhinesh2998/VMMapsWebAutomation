package com.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.utils.Helper;
import com.utils.TestProperties;

public class RegistrationPage extends BasePage {
	Helper helper;

	WebDriver driver;

	@FindBy(id = "signup-username")
	private WebElement fullNameInputField;

	@FindBy(id = "signup-email")
	private WebElement signUpEmailInputField;

	@FindBy(id = "signup-mobile")
	private WebElement mobileInputField;

	@FindBy(id = "signup-password")
	private WebElement signUpPasswordInputField;

	@FindBy(id = "signup-confirmPassword")
	private WebElement signUpConfirmationPasswordField;

	@FindBy(id = "terms-vms-policy")
	private WebElement termsAndPolicyCheckBox;

	@FindBy(id = "newsLetter")
	private WebElement newsLetterSignupCheckBox;

	@FindBy(id = "signup-submit-text")
	private WebElement registerButton;

	@FindBy(id = "signup-exisitingUser")
	private WebElement signInHereButton;

	@FindBy(id = "signupPasswordError")
	private WebElement signUpPasswordError;

	@FindBy(id = "conFirmPassError")
	private WebElement signUpConfirmPasswordError;

	@FindBy(xpath = "//span[contains(text(),'Verify Email Address')]")
	private WebElement verifyEmailAddress;

	public RegistrationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		helper = new Helper(driver);
	}

	public void clickAndVerifyRegistrationButton() {
		LoginPage login = new LoginPage(driver);
		login.clickSignUpToggle();
	}

	public void verifyAllFieldsDisplayedInRegistrationPage() {
		assertTrue(helper.isElementDisplayed(fullNameInputField));
		assertTrue(helper.isElementDisplayed(signUpEmailInputField));
		assertTrue(helper.isElementDisplayed(mobileInputField));
		assertTrue(helper.isElementDisplayed(signUpPasswordInputField));
		assertTrue(helper.isElementDisplayed(signUpConfirmationPasswordField));
		assertTrue(helper.isElementDisplayed(termsAndPolicyCheckBox));
		assertTrue(helper.isElementDisplayed(newsLetterSignupCheckBox));

	}

	public void verifyAllFieldsAreEditable() {
		helper.sendKey(fullNameInputField, TestProperties.getProperty("fullName"));
		String fullNameValue = helper.getFieldValue(fullNameInputField);
		assertEquals(fullNameValue, TestProperties.getProperty("fullName"));

		helper.sendKey(signUpEmailInputField, TestProperties.getProperty("EmailAddress"));
		String EmailValue = helper.getFieldValue(signUpEmailInputField);
		assertEquals(EmailValue, TestProperties.getProperty("EmailAddress"));

		helper.sendKey(mobileInputField, TestProperties.getProperty("MobileNumber"));
		String mobileNumberValue = helper.getFieldValue(mobileInputField);
		assertEquals(mobileNumberValue, TestProperties.getProperty("MobileNumber"));

		helper.sendKey(signUpPasswordInputField, TestProperties.getProperty("Password"));
		String passwordValue = helper.getFieldValue(signUpPasswordInputField);
		assertEquals(passwordValue, TestProperties.getProperty("Password"));

		helper.sendKey(signUpConfirmationPasswordField, TestProperties.getProperty("Password"));
		String confirmPasswordValue = helper.getFieldValue(signUpConfirmationPasswordField);
		assertEquals(confirmPasswordValue, TestProperties.getProperty("Password"));

		helper.clickElement(termsAndPolicyCheckBox);
		assertTrue(termsAndPolicyCheckBox.isSelected());
		helper.clickElement(newsLetterSignupCheckBox);
		assertTrue(newsLetterSignupCheckBox.isSelected());

	}

	public void verifyRegistrationWithoutValuesInFields() {
		helper.clickElement(registerButton);
		String fullNameFieldErrorMessage = helper.getErrorMessage(fullNameInputField);
		assertEquals(fullNameFieldErrorMessage, "Please fill out this field.");
		helper.sendKey(fullNameInputField, TestProperties.getProperty("fullName"));
		helper.clickElement(registerButton);

		String emailFieldErrorMessage = helper.getErrorMessage(signUpEmailInputField);
		assertEquals(emailFieldErrorMessage, "Please fill out this field.");
		helper.sendKey(signUpEmailInputField, TestProperties.getProperty("EmailAddress"));
		helper.clickElement(registerButton);

		String passwordFieldErrorMessage = helper.getErrorMessage(signUpPasswordInputField);
		assertEquals(passwordFieldErrorMessage, "Please fill out this field.");
		helper.sendKey(signUpPasswordInputField, TestProperties.getProperty("Password"));
		helper.clickElement(registerButton);

		String confirmPasswordFieldErrorMessage = helper.getErrorMessage(signUpConfirmationPasswordField);
		assertEquals(confirmPasswordFieldErrorMessage, "Please fill out this field.");

	}

	public void verifyRegistrationWithInvalidEmail() {
		helper.sendKey(fullNameInputField, TestProperties.getProperty("fullName"));
		helper.sendKey(signUpEmailInputField, TestProperties.getProperty("fullName"));
		helper.sendKey(mobileInputField, TestProperties.getProperty("MobileNumber"));
		helper.sendKey(signUpPasswordInputField, TestProperties.getProperty("Password"));
		helper.sendKey(signUpConfirmationPasswordField, TestProperties.getProperty("Password"));
		helper.clickElement(registerButton);
		String InvalidEmailErrorMessage = helper.getErrorMessage(signUpEmailInputField);
		assertEquals(InvalidEmailErrorMessage,
				"Please include an '@' in the email address. 'testing' is missing an '@'.");
	}

	public void verifyRegistrationWithInvalidPassword() {
		helper.sendKey(fullNameInputField, TestProperties.getProperty("fullName"));
		helper.sendKey(signUpEmailInputField, TestProperties.getProperty("EmailAddress"));
		helper.sendKey(mobileInputField, TestProperties.getProperty("MobileNumber"));
		helper.sendKey(signUpPasswordInputField, TestProperties.getProperty("InvalidPassword"));
		helper.sendKey(signUpConfirmationPasswordField, TestProperties.getProperty("Password"));
		helper.clickElement(termsAndPolicyCheckBox);
		helper.clickElement(newsLetterSignupCheckBox);
		helper.clickElement(registerButton);
		assertTrue(helper.isElementDisplayed(signUpPasswordError));
	}

	public void verifyRegistrationWithDifferentValuesInPasswordFields() {
		helper.sendKey(fullNameInputField, TestProperties.getProperty("fullName"));
		helper.sendKey(signUpEmailInputField, TestProperties.getProperty("EmailAddress"));
		helper.sendKey(mobileInputField, TestProperties.getProperty("MobileNumber"));
		helper.sendKey(signUpPasswordInputField, TestProperties.getProperty("WrongPassword"));
		helper.sendKey(signUpConfirmationPasswordField, TestProperties.getProperty("Password"));
		helper.clickElement(termsAndPolicyCheckBox);
		helper.clickElement(newsLetterSignupCheckBox);
		helper.clickElement(registerButton);
		assertTrue(helper.isElementDisplayed(signUpPasswordError));
		assertTrue(helper.isElementDisplayed(signUpConfirmPasswordError));
	}

	public void verifyRegistrationWithCheckingTermsAndConditions() {
		helper.sendKey(fullNameInputField, TestProperties.getProperty("fullName"));
		helper.sendKey(signUpEmailInputField, TestProperties.getProperty("EmailAddress"));
		helper.sendKey(mobileInputField, TestProperties.getProperty("MobileNumber"));
		helper.sendKey(signUpPasswordInputField, TestProperties.getProperty("WrongPassword"));
		helper.sendKey(signUpConfirmationPasswordField, TestProperties.getProperty("Password"));
		helper.clickElement(registerButton);
		String uncheckedErrorMessage = helper.getErrorMessage(termsAndPolicyCheckBox);
		assertEquals(uncheckedErrorMessage, "Please check this box if you want to proceed.");
	}

	public void verifyRegistrationWithValidValues() {
		helper.sendKey(fullNameInputField, helper.getRandomText(7));
		helper.sendKey(signUpEmailInputField, helper.getRandomText(6) + "@gmail.com");
		helper.sendKey(mobileInputField, helper.getRandomNumber(911111111, 999999999) + "1");
		String samePassword = helper.getRandomText(8);
		helper.sendKey(signUpPasswordInputField, samePassword);
		helper.sendKey(signUpConfirmationPasswordField, samePassword);
		helper.clickElement(termsAndPolicyCheckBox);
		helper.clickElement(registerButton);
		assertTrue(helper.isElementDisplayed(verifyEmailAddress));
	}
}
