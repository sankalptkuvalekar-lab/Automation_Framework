package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static Map<Long, ExtentTest> extentTestMap = new HashMap<>();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get(Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String name) {
        ExtentTest test = extent.createTest(name);
        extentTestMap.put(Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized void removeTest() {
        extentTestMap.remove(Thread.currentThread().getId());
    }
}
