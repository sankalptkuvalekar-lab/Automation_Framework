
package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/ExtentReport.html");
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Cucumber + Selenium + TestNG");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}
