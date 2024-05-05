package nz.co.tm.UI.utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviderUtil {

    @DataProvider(name = "itemData")
    public Object[][] itemData() throws IOException {
        return ExcelReadUtil.readUserDataFromExcel(System.getProperty("user.dir") + "/src/test/resources/testData/itemData.xlsx", "Sheet1");
    }
}
