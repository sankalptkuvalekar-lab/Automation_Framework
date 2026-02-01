
/*package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.IExecutionListener;

import java.io.IOException;

public class ExtentReporterListener implements IExecutionListener {

    public static ExtentReports extent;
    public static ExtentTest test;

    @Override
    public void onExecutionStart() {
        extent = ExtentManager.getInstance();
        System.out.println("Extent Reports initialized");
    }

    @Override
    public void onExecutionFinish() {
        if (extent != null) {
            extent.flush();
            System.out.println("Extent Report generated successfully!");
        }
    }

    // Create a new test for each scenario
    public static void createTest(String testName) {
        test = extent.createTest(testName);
    }

    // Log failure and attach screenshot
    public static void logFail(String testName, String screenshotPath) {
        if (test != null) {
            test.log(Status.FAIL, "Scenario Failed: " + testName);

            try {
                if (screenshotPath != null) {
                    test.addScreenCaptureFromPath(screenshotPath); // Must be absolute path
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void logPass(String message) {
        if (test != null) {
            test.log(Status.PASS, message);
        }
    }
}

*/

package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;

public class ExtentReporterListener {

    public static ExtentReports extent;
    public static ExtentTest test;

    // Initialize ExtentReports
    public static void initReports() {
        if (extent == null) {
            // Create folder if it doesn't exist
            String reportFolder = System.getProperty("user.dir") + "/ExtentReports";
            File folder = new File(reportFolder);
            if (!folder.exists()) folder.mkdirs();

            ExtentSparkReporter spark = new ExtentSparkReporter(reportFolder + "/ExtentReport.html");
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Cucumber + Selenium + TestNG");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            System.out.println("Extent Reports initialized successfully");
        }
    }

    // Create a test
    public static void createTest(String testName) {
        if (extent == null) {
            initReports();
        }
        test = extent.createTest(testName);
    }

    // Log pass
    public static void logPass(String message) {
        if (test != null) test.log(Status.PASS, message);
    }

    // Log fail with screenshot
    public static void logFail(String message, String screenshotPath) {
        if (test != null) {
            test.log(Status.FAIL, message);
            try {
                if (screenshotPath != null) {
                    test.addScreenCaptureFromPath(screenshotPath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Flush reports
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}