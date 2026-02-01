package hooks;


import org.openqa.selenium.edge.EdgeDriver;
import stepDefinations.BaseClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.*;
import utilities.ExtentReporterListener;
import utilities.ScreenshotUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Hooks extends BaseClass {

    @Before
    public void beforeScenario(Scenario scenario) {

        // 1️⃣ Init Extent reports
        ExtentReporterListener.initReports();

        // 2️⃣ Setup ChromeDriver
        String browser = configProp.getProperty("browser").toLowerCase();

        switch (browser) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException(
                        "Invalid browser in Config.properties: " + browser
                );
        }
        driver.manage().window().maximize();


            // 2️⃣ Initialize ALL page objects
            lp = new LoginPage(driver);
            addCust = new AddCustomerPage(driver);
            searchCust = new SearchCustomerPage(driver);
            userReg = new UserRegister(driver);
            userLogin = new UserLoginPage(driver);
            newuserLoginPage = new NewUserLoginPage(driver);
            newRegisterPage = new NewRegisterPage(driver);
            contactUsPage = new ContactUsPage(driver);
            testCasePage = new TestCasePage(driver);
            productListAndPage = new ProductListAndPage(driver);
            addToCart = new AddToCart(driver);
            homePage = new HomePage(driver);
            checkoutPage = new CheckoutPage(driver);
            paymentPage = new PaymentPage(driver);
            categoryPage = new CategoryPage(driver);
            brandPage = new BrandPage(driver);
            productDetailsPage = new ProductDetailsPage(driver);

            // 3️⃣ Create Extent test after driver & objects
            ExtentReporterListener.createTest(scenario.getName());
        }


    @After
    public void afterScenario(Scenario scenario) {

        try {
            if (scenario.isFailed()) {
                new File(System.getProperty("user.dir") + "/Screenshots").mkdirs();

                String screenshotPath = ScreenshotUtil.takeScreenshot(driver, scenario.getName());
                ExtentReporterListener.logFail("Scenario Failed: " + scenario.getName(), screenshotPath);
            } else {
                ExtentReporterListener.logPass("Scenario Passed: " + scenario.getName());
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
            ExtentReporterListener.flushReports();
        }
    }
}


