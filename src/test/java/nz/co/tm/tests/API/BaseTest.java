package nz.co.tm.tests.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import nz.co.tm.API.URIs;
import nz.co.tm.API.requestData.ListingRequest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    private static String CONSUMER_KEY = System.getenv("CONSUMER_KEY");
    private static String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
    private static String OAUTH_SIGNATURE = System.getenv("OAUTH_SIGNATURE");
    private static String SIGNATURE_METHOD = "PLAINTEXT";
    private static String generalItemJsonFilePath = System.getProperty("user.dir") + "/src/test/resources/testData/generalItem.json";

    public RequestSpecification specification;
    @BeforeTest
    public void setUp(){
        specification = new RequestSpecBuilder().setBaseUri(URIs.BASE_URI)
                .addHeader("Authorization",
                        "OAuth oauth_consumer_key="+ CONSUMER_KEY +",oauth_token=" + ACCESS_TOKEN + ",oauth_signature_method=" + SIGNATURE_METHOD + ",oauth_signature=" + OAUTH_SIGNATURE)
                .build();
    }
    public static ListingRequest listingRequest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ListingRequest lr = objectMapper.readValue(new File(generalItemJsonFilePath), ListingRequest.class);
        return lr;
    }
}
