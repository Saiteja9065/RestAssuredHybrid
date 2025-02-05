package petstore_swagger_api.utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter extentSparkReporter; //responsible for creating the html file
    public ExtentReports extentReports; //responsible for generating the logs in the html file, explaining test device data, environment data, etc.
    public ExtentTest extentTest; //responsible for creating the test steps in the html file

    String repName;

    public void onStart(ITestContext testContext){

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        repName = "Test-Report-"+timeStamp+".html";

        extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+repName);

        extentSparkReporter.config().setDocumentTitle("Petstore API Test Automation Report");
        extentSparkReporter.config().setReportName("Petstore User API");
        extentSparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Application", "Petstore Users API");
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("user","Teja");
    }

    public void onTestSuccess(ITestResult result){
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        extentTest.pass("Test Passed");
    }

    public void onTestFailure(ITestResult result){
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        extentTest.log(Status.FAIL, "Test Failed");
        extentTest.log(Status.FAIL, result.getThrowable().getMessage());
        extentTest.fail(result.getThrowable());
    }
    public void onTestSkipped(ITestResult result){
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        extentTest.log(Status.SKIP, "Test Skipped");
    }
    public void onFinish(ITestContext testContext){
        extentReports.flush();
    }
}
