package nz.co.tm.UI.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReadUtil {

    public static Object[][] readUserDataFromExcel(String filePath, String sheetName) throws IOException, IOException {
        FileInputStream inputStream = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1); // Start from 1 to skip header row
            for (int j = 0; j < columnCount; j++) {
                data[i][j] = row.getCell(j).toString();
            }
        }
        workbook.close();
        inputStream.close();

        return data;
    }

    public static Object[][] loginData() throws IOException {
        Object[][] userData = ExcelReadUtil.readUserDataFromExcel(System.getProperty("user.dir") + "/src/test/resources/testData/loginData.xlsx", "Sheet1");
        return userData;
    }

}
