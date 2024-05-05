package nz.co.tm.UI.pages;

import nz.co.tm.UI.utils.BasePage;
import org.openqa.selenium.By;

public class ConfirmPage extends BasePage {

    private static By hdrConfirmPage = By.xpath("//h2[text()='Listing Details']");
    private static By strCategory = By.xpath("//*[text()='Category ']/parent::tr//td[2]");
    private static By strListingTitle = By.xpath("//*[contains(text(),'Listing title')]/parent::tr//td[2]");
    private static By strStartPrice = By.xpath("//*[contains(text(),'Start price')]/parent::tr//div");
    private static By strBuyNowPrice = By.xpath("//*[contains(text(),'Buy Now')]/parent::tr//td[2]");
    private static By strPickups = By.xpath("//*[contains(text(),'Pick-ups')]/parent::tr//td[2]");
    private static By strShipping = By.xpath("//*[contains(text(),'Shipping')]/parent::tr//td[2]");
    private static By strAcceptedPaymentMethods = By.xpath("//*[contains(text(),'Accepted payment methods')]/parent::tr//td[2]");
    private static By btnStartAuction = By.id("submit_sell");
    private static By hdrAuctionStarted = By.xpath("//h1[text()='Your auction has started.']");
    private static By strAuctionNumber = By.xpath("//td//*[contains(text(),'Auction number')]/b[1]");
    public static void waitUntilConfirmPageLoaded(){
        waitUntilElementLoaded(driver.findElement(hdrConfirmPage));
    }
    public static String getCategory(){
        return driver.findElement(strCategory).getText();
    }
    public static String getListingTitle(){
        return driver.findElement(strListingTitle).getText();
    }
    public static String getStartPrice(){
        return driver.findElement(strStartPrice).getText();
    }
    public static String getBuyNowPrice(){
        return driver.findElement(strBuyNowPrice).getText();
    }
    public static String getPickupOption(){
        return driver.findElement(strPickups).getText();
    }
    public static String getShippingOption(){
        return driver.findElement(strShipping).getText();
    }
    public static String getPaymentMethod(){
        return driver.findElement(strAcceptedPaymentMethods).getText();
    }
    public static void clickStartAuction(){
        driver.findElement(btnStartAuction).click();
    }
    public static boolean isAuctionStartedMessageDisplayed(){
        return driver.findElement(hdrAuctionStarted).isDisplayed();
    }
    public static String getAuctionNumber(){
        return driver.findElement(strAuctionNumber).getText();
    }

}
