package nz.co.tm.UI.pages;

import nz.co.tm.UI.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    private static By lnkLogin = By.xpath("//a[contains(text(),'Log in')]");
    private static By btnBrowse = By.xpath("//button[text()=' Browse ']");
    private static By drpDwnPaneBrowse = By.xpath("//*[text()='Browse Marketplace']/ancestor::div[2]");
    private static String txtCategory = "//ul[contains(@class,'browse-dropdown')]//li//a[contains(text(),'*')]";
    private static By lnkMyTrademe = By.xpath("//a[contains(text(),'My Trade Me')]");
    private static By lnkViewMyTradeMe = By.id("SiteHeader_SiteTabs_mtmViewMtmLink");
    private static By hdrMyTrademe = By.xpath("//h1[text()='My Trade Me']");
    private static By drpDwnSell = By.id("SellDropDown");
    private static By lnkGeneralItem = By.id("SiteHeader_SiteTabs_sellGeneralItem");


    public static void clickLoginLink(){
        driver.findElement(lnkLogin).click();
    }

    public static void navigateToTheCategoriesWindow(){
        driver.findElement(btnBrowse).click();
    }
    public static void waitUntilCategoriesLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(drpDwnPaneBrowse)));
    }
    public static void selectBrowseCategory(String category){
        driver.findElement(By.xpath(txtCategory.replace("*", category))).click();
    }
    public static void navigateToMyTrademe(){
        driver.findElement(lnkMyTrademe).click();
//        waitUntilElementLoaded(driver.findElement(lnkViewMyTradeMe));
//        driver.findElement(lnkViewMyTradeMe).click();
        waitUntilElementLoaded(driver.findElement(hdrMyTrademe));
    }

    public static void selectSellGeneralItem(){
        driver.findElement(drpDwnSell).click();
        driver.findElement(lnkGeneralItem).click();
    }


}
