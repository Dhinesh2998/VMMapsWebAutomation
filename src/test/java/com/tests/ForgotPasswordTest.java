package com.tests;

import org.testng.annotations.Test;

import com.pages.ForgotPasswordPage;

public class ForgotPasswordTest extends BaseTest {

	@Test(description = "Check whether user able to view the fields Email address and password")
	public void TC_FPP_01() {
		ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver);
		forgotPassword.navigateToLoginURL();
		forgotPassword.moveToForgotPasswordPage();
		forgotPassword.verifyAllFieldsAreDisplayed();
	}

	@Test(description = "Check whether user able to change password without OTP")
	public void TC_FPP_02() {
		ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver);
		forgotPassword.navigateToLoginURL();
		forgotPassword.moveToForgotPasswordPage();
		forgotPassword.verifyChangePasswordWithoutOtp();
	}

	@Test(description = "Check whether user able to change password without entering value in the fields.")
	public void TC_FPP_03() {
		ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver);
		forgotPassword.navigateToLoginURL();
		forgotPassword.moveToForgotPasswordPage();
		forgotPassword.verifyChangePasswordWithoutValues();
	}

	@Test(description = "Check whether user able to change password with wrong OTP")
	public void TC_FPP_04() {
		ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver);
		forgotPassword.navigateToLoginURL();
		forgotPassword.moveToForgotPasswordPage();
		forgotPassword.verifyChangePasswordWithWrongOTP();
	}

	@Test(description = "Check whether user able to move to forgot password page with not registered email Id")
	public void TC_FPP_05() {
		ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver);
		forgotPassword.navigateToLoginURL();
		forgotPassword.verifyMoveToForgotPasswordWithUnregisteredEmail();
	}

}
