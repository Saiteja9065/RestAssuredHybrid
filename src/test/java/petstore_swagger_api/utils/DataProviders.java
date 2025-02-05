package petstore_swagger_api.utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "userData")
    public String[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/java/petstore_swagger_api/testdata/UserData.xlsx";
        XLUtility xlutil = new XLUtility(path);
        int totalRows = xlutil.getRowCount("Sheet1");
        int totalCols = xlutil.getCellCount("Sheet1", 1);

        String[][] apiData = new String[totalRows][totalCols];

        for(int i=1; i<=totalRows; i++){
            for(int j=0; j<totalCols; j++){
                apiData[i-1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }
        return apiData;
    }

    @DataProvider(name = "username")
    public String[] getUsernames() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/java/petstore_swagger_api/testdata/UserData.xlsx";
        XLUtility xlutil = new XLUtility(path);
        int totalRows = xlutil.getRowCount("Sheet1");
        String[] usernames = new String[totalRows];
        for (int i = 1; i <= totalRows; i++) {
            usernames[i - 1] = xlutil.getCellData("Sheet1", i, 1);
        }
        return usernames;
    }


}
