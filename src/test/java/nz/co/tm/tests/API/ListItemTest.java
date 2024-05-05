package nz.co.tm.tests.API;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import nz.co.tm.API.URIs;
import nz.co.tm.API.requestData.ListingRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ListItemTest extends BaseTest{

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
        Assert.assertEquals(response.statusCode(), 200);
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        Assert.assertEquals(jsonPath.get("Success"), true);
        System.out.println("Listing Id" + jsonPath.getInt("ListingId"));
    }
}
