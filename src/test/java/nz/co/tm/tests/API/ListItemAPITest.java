package nz.co.tm.tests.API;

import com.beust.ah.A;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import nz.co.tm.API.URIs;
import nz.co.tm.API.requestData.ListingRequest;
import nz.co.tm.API.responseData.ErrorData;
import nz.co.tm.UI.utils.BasePage;
import nz.co.tm.UI.utils.ExcelReadUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class ListItemAPITest extends BaseTest {

    /**
     * This class intends to automate API functionality of List an item.
     * Consumer key and access token is stored under env variables to
     * pass in the header of the API
     * Only Basic body parameters are used to send the request for the demo purpose
     *
     * @throws IOException
     */
    @Test(description = "Verify List an item with /v1/Selling.JSON")
    public void testListItemUnderSelling() throws IOException {
        ListingRequest listItem = listingRequest();
        listItem.setCategory("6911");
        listItem.setTitle("Valuable Monitor");
        Response response = given().log().all().spec(specification).contentType(ContentType.JSON).body(listItem)
                .when().post(URIs.END_POINT_LIST_ITEM);
        Assert.assertEquals(response.statusCode(), 200); // verify the status code in the response
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        Assert.assertEquals(jsonPath.get("Success"), true);// verify the success messge in the response body
        System.out.println("Listing Id" + jsonPath.getInt("ListingId"));// print the listing id
    }

    @Test(description = "Verify invalid category returns error message")
    public void testInvalidCategoryListItemReturns400() throws IOException {
        ListingRequest listItem = listingRequest();
        listItem.setCategory("691100");
        Response response = given().log().all().spec(specification).contentType(ContentType.JSON).body(listItem)
                .when().post(URIs.END_POINT_LIST_ITEM);
        Assert.assertEquals(response.statusCode(), 400);
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        Assert.assertEquals(jsonPath.get("ErrorDescription"), "Category "+ listItem.getCategory()+" does not exist.");
    }

    @Test(description = "Verify sending empty title returns error message")
    public void testEmptyTitleReturnsError() throws IOException {
        ListingRequest listItem = listingRequest();
        listItem.setTitle("");
        Response response = given().log().all().spec(specification).contentType(ContentType.JSON).body(listItem)
                .when().post(URIs.END_POINT_LIST_ITEM);
        Assert.assertEquals(response.statusCode(), 200);
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        Assert.assertEquals(jsonPath.get("Success"), false);
        Assert.assertEquals(jsonPath.get("Description"), "Please enter a title");
    }
    @Test(description = "Verify when buy now price is less than start price, returns error message")
    public void testVerifyThatBuyNowPriceShouldBeGreaterThanStartPrice() throws IOException {
        ListingRequest listItem = listingRequest();
        listItem.setStartPrice("200");
        listItem.setBuyNowPrice("100");
        Response response = given().log().all().spec(specification).contentType(ContentType.JSON).body(listItem)
                .when().post(URIs.END_POINT_LIST_ITEM);
        Assert.assertEquals(response.statusCode(), 200);
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        Assert.assertEquals(jsonPath.get("Success"), false);
        Assert.assertEquals(jsonPath.get("Description"), "The Buy Now price must be greater than or equal to the start price.");
    }

    @Test(description = "Verify pickup sets 0 returns error message")
    public void testVerifyErrorMessageWhenNoPickupOptionIsSet() throws IOException {
        ListingRequest listItem = listingRequest();
        listItem.setPickup(0);
        Response response = given().log().all().spec(specification).contentType(ContentType.JSON).body(listItem)
                .when().post(URIs.END_POINT_LIST_ITEM);
        Assert.assertEquals(response.statusCode(), 200);
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        Assert.assertEquals(jsonPath.get("Success"), false);
        Assert.assertEquals(jsonPath.get("Description"), "Please select a pickup option.");
        }

    @Test(description = "Verify when no payment method selected returns error message")
    public void testVerifyPaymentMethodIsSentReturnsError() throws IOException {
        ListingRequest listItem = listingRequest();
        ArrayList<Integer> payment = new ArrayList<>();
        listItem.setPaymentMethods(payment);
        Response response = given().log().all().spec(specification).contentType(ContentType.JSON).body(listItem)
                .when().post(URIs.END_POINT_LIST_ITEM);
        Assert.assertEquals(response.statusCode(), 200);
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        Assert.assertEquals(jsonPath.get("Success"), false);
        Assert.assertEquals(jsonPath.get("Description"), "Please select the payment methods you will accept.");
    }

    /*
    Duration field
     - Boundary value analysis : -1,0,1,3,6,8,9,11
     - Equivalance partitioning : 1,5,8,11
     */
    @Test(description = "Verify when duration is set to a value which is greater than maximum allowed duration returns error")
    public void testVerifyWhenDurationIsGreaterThanMaximumAllowedReturnsError() throws IOException {
        ListingRequest listItem = listingRequest();
        listItem.setDuration(11);
        Response response = given().log().all().spec(specification).contentType(ContentType.JSON).body(listItem)
                .when().post(URIs.END_POINT_LIST_ITEM);
        Assert.assertEquals(response.statusCode(), 200);
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        Assert.assertEquals(jsonPath.get("Success"), false);
        String description = jsonPath.get("Description").toString();
        Assert.assertEquals(description.split("\r\n")[0], "The selected duration is not allowed in this category");
        Assert.assertEquals(description.split("\r\n")[1], "The selected duration is greater than the maximum allowed");
    }



}
