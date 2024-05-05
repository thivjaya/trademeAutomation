package nz.co.tm.UI.pages;

import nz.co.tm.UI.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage extends BasePage {

    private static String hdrCategory = "//h1[contains(text(),'*')]";
    private static By bxSearch = By.xpath("//*[@label='Add Search keywords']");
    private static By txtSearch = By.xpath("//*[@label='Add Search keywords']//input");
    private static By btnSearch = By.xpath("//button[@type='submit']");
    private static String crdSearchedItem = "//div[contains(@aria-label,'1')]";
    private static String txtSearchResult ="//*[contains(text(), \"results for '1'\")]";
    private static By txtSearchBox = By.id("searchString");
    private static By hdrSearchResult = By.xpath("//h1[contains(text(),'Search results')]");

    public static void waitUntilCategoryPageIsLoaded(String category){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(hdrCategory.replace("*",category).replace("\\","")))));
    }
    public static void sendSearchItemWithinCategory(String item){
        driver.findElement(bxSearch).click();
        driver.findElement(txtSearch).sendKeys(item);
        driver.findElement(btnSearch).click();
    }
    public static boolean isSearchedItemDisplayed(String item){
        return driver.findElement(By.xpath(crdSearchedItem.replace("1",item))).isDisplayed();
    }

    public static boolean isSearchedResultsDisplayed(String item){
        return driver.findElement(By.xpath(txtSearchResult.replace("1", item))).isDisplayed();
    }
    public static void enterSearchItem(String item) throws InterruptedException {
        Thread.sleep(5000);
        WebElement searchElement = driver.findElement(txtSearchBox);
        searchElement.click();
        searchElement.clear();
        searchElement.sendKeys(item);
        searchElement.sendKeys(Keys.ENTER);
    }
    public static boolean isSearchedResultsDisplayed(){
        return driver.findElement(hdrSearchResult).isDisplayed();
    }
}
