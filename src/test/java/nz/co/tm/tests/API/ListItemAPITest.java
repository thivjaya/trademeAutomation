package nz.co.tm.tests.API;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import nz.co.tm.API.URIs;
import nz.co.tm.API.requestData.ListingRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ListItemAPITest extends BaseTest{

    /**
     * This class intends to automate API functionality of List an item.
     * Consumer key and access token is stored under env variables to
     * pass in the header of the API
     * Only Basic body parameters are used to send the request for the demo purpose
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
}
