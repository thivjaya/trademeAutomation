package nz.co.tm.UI.pages;

import nz.co.tm.UI.utils.BasePage;
import org.openqa.selenium.By;

public class PricingPage extends BasePage {
    private static By hdrPricing = By.xpath("//h2[text()='Pricing & Duration']");
    private static By txtStartPrice = By.id("startPriceInput");
    private static By txtBuyNow = By.name("buynowprice");
    private static By btnSubmit = By.id("submit1");

    public static boolean isPricingPageDisplayed()
    {
        return driver.findElement(hdrPricing).isDisplayed();
    }
    public static void enterStartPrice(String startPrice){
        driver.findElement(txtStartPrice).sendKeys(startPrice);
    }
    public static void enterBuyNowPrice(String buyNowPrice){
        driver.findElement(txtBuyNow).sendKeys(buyNowPrice);
    }
    public static void clickNextButton(){
        driver.findElement(btnSubmit).click();
    }
    public static void enterPricingDetailsAndProceed(String startPrice, String buyNowPrice){
        enterStartPrice(startPrice);
        enterBuyNowPrice(buyNowPrice);
        clickNextButton();
    }

}
