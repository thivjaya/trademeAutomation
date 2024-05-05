package nz.co.tm.utils.UI;

import nz.co.tm.UI.utils.BasePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

public class BaseTest {

    protected SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void setup(){
       BasePage.navigateToSystem();
    }


    @AfterTest
    public void quitDriver(){
        BasePage.quitDriver();
    }
}
