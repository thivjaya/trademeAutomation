package nz.co.tm.tests.API;

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

    @Test(description = "Verify invalid category returns error message")
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

}
