package com.tests;

import org.testng.annotations.Test;

import com.pages.RegistrationPage;

public class RegistrationTest extends BaseTest {

	@Test(description = "Check whether user able to view the fields Full Name, Email address, Mobile, password and confirm password")
	public void TC_RP_01() {
		RegistrationPage register = new RegistrationPage(driver);
		register.navigateToLoginURL();
		register.clickAndVerifyRegistrationButton();
		register.verifyAllFieldsDisplayedInRegistrationPage();
	}

	@Test(description = "Check whether user able to enter values in all the fields.")
	public void TC_RP_02() {
		RegistrationPage register = new RegistrationPage(driver);
		register.navigateToLoginURL();
		register.clickAndVerifyRegistrationButton();
		register.verifyAllFieldsAreEditable();
	}

	@Test(description = "Check whether user able to register without the mandatory fields.")
	public void TC_RP_03() {
		RegistrationPage register = new RegistrationPage(driver);
		register.navigateToLoginURL();
		register.clickAndVerifyRegistrationButton();
		register.verifyRegistrationWithoutValuesInFields();
	}

	@Test(description = "Check whether user able to give invalid email address and register")
	public void TC_RP_04() {
		RegistrationPage register = new RegistrationPage(driver);
		register.navigateToLoginURL();
		register.clickAndVerifyRegistrationButton();
		register.verifyRegistrationWithInvalidEmail();
	}

	@Test(description = "Check whether user able to give less than 8 characters in the password field and register")
	public void TC_RP_05() {
		RegistrationPage register = new RegistrationPage(driver);
		register.navigateToLoginURL();
		register.clickAndVerifyRegistrationButton();
		register.verifyRegistrationWithInvalidPassword();
	}

	@Test(description = "Check whether user able to register by giving different values in the password and confirm password.")
	public void TC_RP_06() {
		RegistrationPage register = new RegistrationPage(driver);
		register.navigateToLoginURL();
		register.clickAndVerifyRegistrationButton();
		register.verifyRegistrationWithDifferentValuesInPasswordFields();
	}

	@Test(description = "check whether user able to register without checking the terms and conditions.")
	public void TC_RP_07() {
		RegistrationPage register = new RegistrationPage(driver);
		register.navigateToLoginURL();
		register.clickAndVerifyRegistrationButton();
		register.verifyRegistrationWithCheckingTermsAndConditions();
	}
	@Test(description = "check whether user able to register by giving valid values in the mandatory fields.")
	public void TC_RP_08() {
		RegistrationPage register = new RegistrationPage(driver);
		register.navigateToLoginURL();
		register.clickAndVerifyRegistrationButton();
		register.verifyRegistrationWithValidValues();
	}

}
