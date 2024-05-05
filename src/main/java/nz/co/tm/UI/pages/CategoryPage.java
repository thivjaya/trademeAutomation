package nz.co.tm.UI.pages;

import nz.co.tm.UI.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class CategoryPage extends BasePage {
    private static By txtListingTitle = By.id("listing-title");
    private static By drpdwnSelectCategory = By.xpath("//td/*[text()='Category']/following-sibling::div");
    private static By optBrowseAll = By.xpath("//li[@data-index='1']");
    private static By drpdwnCategory0 = By.id("selector_0");
    private static By drpdwnCategory1 = By.id("selector_1");
    private static By drpdwnCategory2 = By.id("selector_2");
    private static By drpdwnCategory3 = By.id("selector_3");
    private static By btnNext = By.id("submit_button");
    private static By radioOneCategory = By.id("one-category-radio");
    public static void enterListingItem(String item){
        driver.findElement(txtListingTitle).sendKeys(item);
    }
    public static void selectCategory(){
        waitUntilElementLoaded(driver.findElement(optBrowseAll));
        driver.findElement(optBrowseAll).click();
    }
    public static void selectFirstCategoryOption1(String category){
        Select select = new Select(driver.findElement(drpdwnCategory0));
        select.selectByVisibleText(category);
    }
    public static void selectFirstCategoryOption2(String category){
        Select select = new Select(driver.findElement(drpdwnCategory1));
        select.selectByVisibleText(category);
    }
    public static void selectFirstCategoryOption3(String category){
        Select select = new Select(driver.findElement(drpdwnCategory2));
        select.getOptions().contains(category);
    }
    public static void selectFirstCategoryOption4(String category){
        Select select = new Select(driver.findElement(drpdwnCategory3));
        select.getOptions().contains(category);
    }
    public static void selectListInOnlyOneCategory(){
        driver.findElement(radioOneCategory).click();
    }
    public static void clickNext(){
        driver.findElement(btnNext).click();
    }
    public static void selectCategoryDetails(String item, String categoryOption1, String categoryOption2){
        enterListingItem(item);
        selectCategory();
        selectFirstCategoryOption1(categoryOption1);
        selectFirstCategoryOption2(categoryOption2);
        selectListInOnlyOneCategory();
        clickNext();
    }
}
