package nz.co.tm.tests.UI;

import nz.co.tm.UI.pages.HomePage;
import nz.co.tm.UI.pages.SearchPage;
import nz.co.tm.UI.utils.DataProviderUtil;
import nz.co.tm.utils.UI.BaseTest;
import org.testng.annotations.Test;

/**
 * This test class automates the basic search functionality. Data provider is used to pass different
 * categories and item names for search purpose. Ex. Category: Computers
 */
public class SearchTest extends BaseTest {

    @Test(description = "Verification of basic search functionality with 'Computers' category", dataProvider = "itemData", dataProviderClass = DataProviderUtil.class)
    public void verifySearchFunctionality(String category, String itemName){
        HomePage.navigateToTheCategoriesWindow();
        HomePage.waitUntilCategoriesLoaded();
        HomePage.selectBrowseCategory(category);
        SearchPage.waitUntilCategoryPageIsLoaded(category);
        SearchPage.sendSearchItemWithinCategory(itemName);
        softAssert.assertTrue(SearchPage.isSearchedResultsDisplayed(itemName), "Expected item name is not displayed in results\n");
        softAssert.assertTrue(SearchPage.isSearchedItemDisplayed(itemName), "Expected item name is not listed under given category\n");
        softAssert.assertAll();
    }
}
