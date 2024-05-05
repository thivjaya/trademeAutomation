package nz.co.tm.UI.pages;

import nz.co.tm.UI.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PaymentPage extends BasePage {

    private static By hdrPaymentShipping = By.xpath("//h2[text()='Payment & Shipping']");
    private static By drpDwnPickUp = By.id("pickup");
    private static String rdioShipping = "//input[@name='delivery' and @value='{1}']";
    private static By chkCashPayment = By.id("payment_cash_on_pickup");
    private static By btnNext = By.xpath("//input[@value='Next']");
    private static By btnErrorSelectShippingOption = By.xpath("//div[text()='Please select a shipping option.']");

    //-------------Add Photos Page------------------------------------------------------
    private static By btnContinueWithoutPhoto = By.id("ContinueUpload");
    private static By hdrAddPhotos = By.xpath("//div[contains(text(), 'Add photos')]");

    //-----------Extras Page------------------------------------------------------------
    private static String btnSelectPackage = "{1}-selector";
    private static By btnPromoSubmit = By.id("promo-submit");


    public static boolean isPaymentPageIsDisplayed(){
        return driver.findElement(hdrPaymentShipping).isDisplayed();
    }
    public static void selectPickUp(String pickup){
        Select select = new Select(driver.findElement(drpDwnPickUp));
        select.selectByVisibleText(pickup);
    }
    public static void selectShippingOption(String option){
        driver.findElement(By.xpath(rdioShipping.replace("{1}", option ))).click();
    }
    public static void selectCashPayment(){
        driver.findElement(chkCashPayment).click();
    }
    public static void clickNext(){
        driver.findElement(btnNext).click();
    }
    public static boolean isShippingOptionErrorMessageDisplayed(){
        return driver.findElement(btnErrorSelectShippingOption).isDisplayed();
    }

    public static void selectPaymentAndShipping(String pickup, String shippingOption){
        selectPickUp(pickup);
        selectShippingOption(shippingOption);
        selectCashPayment();
        clickNext();
    }

    public static void continueWithoutPhoto(){
        waitUntilElementLoaded(driver.findElement(hdrAddPhotos));
        driver.findElement(btnContinueWithoutPhoto).click();
    }
    public static void selectPackage(String expectedPackage){
        WebElement ele = driver.findElement(By.id(btnSelectPackage.replace("{1}", expectedPackage)));
        waitUntilElementLoaded(ele);
        ele.click();
    }

    public static void navigateToConfirmPage(){
        driver.findElement(btnPromoSubmit).click();
    }


}
