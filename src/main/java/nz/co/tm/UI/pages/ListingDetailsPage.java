package nz.co.tm.UI.pages;

import nz.co.tm.UI.utils.BasePage;
import org.openqa.selenium.By;

public class ListingDetailsPage extends BasePage {

    private static By hdrListingDetails = By.xpath("//h2[text()='Listing Details']");
    private static By txtItemDesc = By.xpath("//h2[text()='Item Details']/parent::div//textarea");
    private static By btnSubmit = By.id("submit1");
    public static boolean isListingDetailsPageLoaded()
    {
        return driver.findElement(hdrListingDetails).isDisplayed();
    }
    public static void enterItemDesc(String desc){
        driver.findElement(txtItemDesc).sendKeys(desc);
    }
    public static void navigateToPricePage(){
        driver.findElement(btnSubmit).click();
    }
}
