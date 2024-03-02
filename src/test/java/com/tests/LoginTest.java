package com.tests;

import org.testng.annotations.Test;

import com.pages.LoginPage;

public class LoginTest extends BaseTest {

	@Test(description = "Check whether user able to view the fields Email address and password")
	public void TC_LP_01() {
		LoginPage login = new LoginPage(driver);
		login.navigateToLoginURL();
		login.verifyAllFieldsDisplayedInLoginPage();

	}

	@Test(description = "check whether user able to enter Email Address and Password")
	public void TC_LP_02() {
		LoginPage login = new LoginPage(driver);
		login.navigateToLoginURL();
		login.verifyEmailAndPasswordFieldsAreEditable();

	}

	@Test(description = "Check whether user able to click on submit and login without Email Address and Password")
	public void TC_LP_03() {
		LoginPage login = new LoginPage(driver);
		login.navigateToLoginURL();
		login.verifyLoginWithoutEmailAndPassword();

	}

	@Test(description = "Check whether user able to login by giving wrong email address and correct password")
	public void TC_LP_04() {
		LoginPage login = new LoginPage(driver);
		login.navigateToLoginURL();
		login.verifyLoginWithWrongEmailAddress();

	}
	@Test(description = "Check whether user able to login by giving correct email address and wrong password")
	public void TC_LP_05() {
		LoginPage login = new LoginPage(driver);
		login.navigateToLoginURL();
		login.verifyLoginWithWrongPassword();

	}
	@Test(description = "Check whether user able to login by giving correct email address and correct password")
	public void TC_LP_06() {
		LoginPage login = new LoginPage(driver);
		login.navigateToLoginURL();
		login.verifyLoginWithValidCredentials();

	}

}
