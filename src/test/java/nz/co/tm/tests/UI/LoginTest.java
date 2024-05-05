package nz.co.tm.tests.UI;

import nz.co.tm.UI.pages.HomePage;
import nz.co.tm.UI.pages.LoginPage;
import nz.co.tm.UI.utils.ExcelReadUtil;
import nz.co.tm.utils.UI.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Automation for customer login scenarios.
 * Trademe login page has a reCaptcha for user verification and it has not been covered with this automation
 * User data is read from loginData.xlsx
 */
public class LoginTest extends BaseTest {
    Object[][] userData = ExcelReadUtil.loginData();
    public LoginTest() throws IOException {
    }

    @Test(priority = 1, description = "Verify error message is displayed when invalid email address is entered")
    public void testVerifyErrorsForInvalidEmailAddress(){
        HomePage.clickLoginLink();
        LoginPage.waitUntilLoginPageIsLoaded();
        LoginPage.enterEmail(userData[1][1].toString());
        LoginPage.enterPassword(userData[1][2].toString());
        LoginPage.clickLogin();
        softAssert.assertEquals(LoginPage.getValidationErrorMsg(), "Invalid email or password", "Expected error message is not displayed\n");
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Verify error message is displayed when invalid password is entered")
    public void testVerifyErrorsForInvalidPassword(){
        LoginPage.enterEmail(userData[2][1].toString());
        LoginPage.enterPassword(userData[2][2].toString());
        LoginPage.clickLogin();
        softAssert.assertEquals(LoginPage.getValidationErrorMsg(), "Invalid email or password", "Expected error message is not displayed\n");
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Verify error message is displayed when user trying to log in with empty email address")
    public void testVerifyErrorsForEmptyEmailAddress(){
        LoginPage.enterEmail("");
        LoginPage.enterPassword(userData[3][2].toString());
        LoginPage.clickLogin();
        softAssert.assertEquals(LoginPage.getEmailValidationErrorMsg(), "This field is required", "Expected field validation is not displayed\n");
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Verify error message is displayed when user trying to log in with empty password")
    public void testVerifyErrorsForEmptyPassword(){
        LoginPage.enterEmail(userData[4][1].toString());
        LoginPage.enterPassword("");
        LoginPage.clickLogin();
        softAssert.assertEquals(LoginPage.getPasswordValidationErrorMsg(), "This field is required", "Expected field validation is not displayed\n");
        softAssert.assertAll();
    }

    @Test(priority = 5, description = "Verify user is able to successfully log in once valid username and password is entered")
    public void testVerifySuccessForValidLoginDetails(){
        LoginPage.enterEmail(userData[0][1].toString());
        LoginPage.enterPassword(userData[0][2].toString());
        LoginPage.clickLogin();
        softAssert.assertTrue(LoginPage.isLogoutLinkDisplayed(), "Expected logout link is not displayed after logged in\n");
        softAssert.assertAll();
    }

    @Test(description = "Verify error message is displayed for invalid format email address")
    public void testVerifyErrorForInvalidEmailFormat(){

    }

    @Test
    public void testVerifyForgotPasswordProcess() {

    }
}
