package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

//    @Parameters("browser")
    public void onStart(ITestContext testContext) {


        String timespan = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName  = "Test-Report-"+timespan+".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); //location

        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setDocumentTitle("AutomationByHammad");
        sparkReporter.config().setReportName("Web Automation");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

//        extent.setSystemInfo("Browser Name:",System.getProperty("browser.name"));
        extent.setSystemInfo("App:","Web Automation");
        extent.setSystemInfo("Operating System",System.getProperty("os.name"));
        extent.setSystemInfo("User Name",System.getProperty("user.name"));
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("User","Hammmad");

    }


    public void onFinish(ITestContext context) {
        if (extent != null)
            extent.flush();
    }


    public void onTestSuccess(ITestResult result){
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS, "Test Passed");
    }


    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }


    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }


    public void onTestFinish(ITestResult result) {
        extent.flush();
    }




//    public void onTestStart(ITestResult result) {
//        ExtentTest test = extentReports.createTest("Test Name " + result.getTestClass().getName() + " - " + result.getMethod().getMethodName(),
//                result.getMethod().getDescription());
//        extentTest.set(test);
//    }

//    public void onTestFailure(ITestResult result) {
//        ExtentReportManager.logFailureDetails(result.getThrowable().getMessage());
//        String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
//        stackTrace = stackTrace.replaceAll(",", "<br>");
//        String formmatedTrace = "<details>\n" +
//                "    <summary>Click Here To See Exception Logs</summary>\n" +
//                "    " + stackTrace + "\n" +
//                "</details>\n";
//        ExtentReportManager.logExceptionDetails(formmatedTrace);
//    }

}
