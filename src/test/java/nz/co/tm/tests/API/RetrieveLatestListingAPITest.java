package nz.co.tm.tests.API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import nz.co.tm.API.URIs;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * This class intends to automate API functionality of Retrieve latest listing.
 * Consumer key and access token is stored under env variables to
 * pass in the header of the API
 */
public class RetrieveLatestListingAPITest extends BaseTest {

    @Test(description = "Retrieve latest listing filtered by Buy Now")
    public void testRetrieveLatestListingByBuyNowOption(){
        Response response = given().log().all().spec(specification).queryParams("buy", "BuyNow")
                .when().get(URIs.END_POINT_RETRIEVE_LATEST_LISTING);
        Assert.assertEquals(response.statusCode(), 200);
        JsonPath js = new JsonPath(response.getBody().asString());
        int totalCount = js.getInt("TotalCount");
        Assert.assertTrue(totalCount > 0);
    }

    @Test(description = "Retrieve latest listing filtered by Region")
    public void testRetrieveLatestListingByNorthlandRegion(){
        Response response = given().log().all().spec(specification).queryParams("region", "1")
                .when().get(URIs.END_POINT_RETRIEVE_LATEST_LISTING);
        Assert.assertEquals(response.statusCode(), 200);
        JsonPath js = new JsonPath(response.getBody().asString());
        int totalCount = js.getInt("TotalCount");
        Assert.assertTrue(totalCount > 0);
    }
}
