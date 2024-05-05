package nz.co.tm.UI.pages;

import nz.co.tm.UI.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    private static By hdrLogin = By.xpath("//h1[text()='Log in']");
    private static By ifrmLogin = By.xpath("//iframe[contains(@class,'login')]");
    private static By txtEmail = By.id("Email");
    private static By txtPassword = By.id("Password");
    private static By ifrmRecaptcha = By.xpath("//iframe[@title='reCAPTCHA']");
    private static By chkRecaptcha = By.id("recaptcha-anchor");
    private static By btnLogin = By.id("SignIn");
    private static By txtValidationError = By.xpath("//div[@class='validation-summary-errors']//li");
    private static By txtEmailValidationError = By.xpath("//label[text()='Email']/..//span[@class='field-validation-error']");
    private static By txtPasswordValidationError = By.xpath("//label[text()='Password']/..//span[@class='field-validation-error']");
    private static By lnkLogout = By.xpath("//a[contains(text(),'Log out')]");
    public static void waitUntilLoginPageIsLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(ifrmLogin)));
        driver.switchTo().frame(driver.findElement(ifrmLogin));
    }

    public static void enterEmail(String email){
        driver.findElement(txtEmail).click();
        driver.findElement(txtEmail).clear();
        driver.findElement(txtEmail).sendKeys(email);
    }
    public static void enterPassword(String password){
        driver.findElement(txtPassword).click();
        driver.findElement(txtPassword).clear();
        driver.findElement(txtPassword).sendKeys(password);
    }
    public static void clickRecaptcha(){
        driver.switchTo().frame(driver.findElement(ifrmRecaptcha));
        driver.findElement(chkRecaptcha).click();
        driver.switchTo().defaultContent();
    }
    public static void clickLogin(){
        driver.findElement(btnLogin).click();
    }
    public static String getValidationErrorMsg(){
        return driver.findElement(txtValidationError).getText();
    }
    public static String getEmailValidationErrorMsg(){
        return driver.findElement(txtEmailValidationError).getText();
    }
    public static String getPasswordValidationErrorMsg(){
        return driver.findElement(txtPasswordValidationError).getText();
    }

    public static boolean isLogoutLinkDisplayed(){
        return driver.findElement(lnkLogout).isDisplayed();
    }
    public static void waitTillLogoutLinkDisplayed(){
        waitUntilElementLoaded(driver.findElement(lnkLogout));
    }
}
