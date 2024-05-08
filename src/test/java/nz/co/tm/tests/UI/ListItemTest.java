package nz.co.tm.tests.UI;

import nz.co.tm.UI.pages.*;
import nz.co.tm.UI.utils.ExcelReadUtil;
import nz.co.tm.utils.UI.TestData.ItemData;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * This test class intends to verify Listing an item under general classification through the selling interface
 * As a prerequisite user needs to log into the system before list an item
 * End-to-end process is divided into two different tests.
 * Listing is done only for one category
 */
public class ListItemTest extends BaseTest {
    Object[][] loginData = ExcelReadUtil.loginData();
    public ListItemTest() throws IOException {
    }

    @BeforeTest
    public void logUser(){
        HomePage.clickLoginLink();
        LoginPage.waitUntilLoginPageIsLoaded();
        LoginPage.enterEmail(loginData[0][1].toString());
        LoginPage.enterPassword(loginData[0][2].toString());
        LoginPage.clickLogin();
        LoginPage.waitTillLogoutLinkDisplayed();
    }

    @Test(description = "Verify user is able to successfully proceed to payment page once category and prices are selected")
    public void verifyItemCategoryAndPriceSelectedSuccessfully(){
        HomePage.navigateToMyTrademe();
        HomePage.selectSellGeneralItem();
        CategoryPage.selectCategoryDetails(ItemData.LISTING_TITLE,ItemData.CATEGORY_1 + " >",ItemData.CATEGORY_2);
        softAssert.assertTrue(ListingDetailsPage.isListingDetailsPageLoaded(), "Expected Listing Details page is not displayed\n");
        ListingDetailsPage.enterItemDesc(ItemData.ITEM_DESC);
        ListingDetailsPage.navigateToPricePage();
        softAssert.assertTrue(PricingPage.isPricingPageDisplayed());
        PricingPage.enterPricingDetailsAndProceed(ItemData.START_PRICE, ItemData.BUY_NOW_PRICE);
        softAssert.assertTrue(PaymentPage.isPaymentPageIsDisplayed(), "Expected Payment page is not displayed\n");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "verifyItemCategoryAndPriceSelectedSuccessfully", description = "Verify Selected details are correctly displayed in Confirm page and Auction number is returned once started")
    public void verifyPaymentDetailsAddedAndProceedToConfirmSuccessfully() throws InterruptedException {
        PaymentPage.clickNext();
        softAssert.assertTrue(PaymentPage.isShippingOptionErrorMessageDisplayed(), "Expected error message for unselected shipping option not displayed\n"); // verify error message is displayed if shipping option not selected
        PaymentPage.selectPaymentAndShipping(ItemData.PICKUPS, "Free");
        PaymentPage.continueWithoutPhoto();
        PaymentPage.selectPackage("feature");
        PaymentPage.navigateToConfirmPage();
        ConfirmPage.waitUntilConfirmPageLoaded();
        softAssert.assertEquals(ConfirmPage.getListingTitle(), ItemData.LISTING_TITLE, "Expected Listing Title is not matched\n");
        softAssert.assertEquals(ConfirmPage.getStartPrice(), "$"+ItemData.START_PRICE, "Expected start price is not matched\n");
        softAssert.assertEquals(ConfirmPage.getBuyNowPrice(), "$"+ItemData.BUY_NOW_PRICE, "Expected Buy now price is not matched");
        ConfirmPage.clickStartAuction();
        softAssert.assertTrue(ConfirmPage.isAuctionStartedMessageDisplayed(), "Auction is not started successfully");
        System.out.println("Auction number: "+ ConfirmPage.getAuctionNumber()); // print Auction number
        SearchPage.enterSearchItem(ItemData.LISTING_TITLE);
        softAssert.assertTrue(SearchPage.isSearchedResultsDisplayed(), "Listed item is not displayed" ); // verify newly listed item is displayed when searched
        softAssert.assertAll();

    }
}
