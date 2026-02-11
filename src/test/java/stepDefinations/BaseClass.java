/*package stepDefinations;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import pageObjects.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;

    public static Logger logger;
    public static Properties configProp;

    public LoginPage lp;
    public AddCustomerPage addCust;
    public SearchCustomerPage searchCust;
    public UserRegister userReg;
    public UserLoginPage userLogin;
    public NewUserLoginPage newuserLoginPage;
    public NewRegisterPage newRegisterPage;
    public ContactUsPage contactUsPage;
    public TestCasePage testCasePage;
    public ProductListAndPage productListAndPage;
    public AddToCart addToCart;
    public HomePage homePage;
    public CheckoutPage checkoutPage;
    public PaymentPage paymentPage;
    public CategoryPage categoryPage;
    public BrandPage brandPage;
    public ProductDetailsPage productDetailsPage;

    static {
        try {
            logger = Logger.getLogger("AutomationExercise");
            PropertyConfigurator.configure(
                    System.getProperty("user.dir")
                            + "/src/test/resources/Log4j.properties"
            );

            configProp = new Properties();
            FileInputStream fis = new FileInputStream(
                    System.getProperty("user.dir")
                            + "/src/test/resources/Config.properties"
            );
            configProp.load(fis);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load Config or Log4j", e);
        }
    }

    public static String randomString() {
        return RandomStringUtils.randomAlphanumeric(5);
    }

    public void clickWithJS(WebElement element) {
        if (driver == null) {
            throw new RuntimeException("Driver is null! Check Hooks initialization.");
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void dismissAdIfPresent() {
        try {
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                driver.switchTo().frame("aswift_1");
                try {
                    driver.switchTo().frame("ad_iframe");
                } catch (Exception ignored) {}
                driver.findElement(By.id("dismiss-button")).click();
                driver.switchTo().defaultContent();
            }
        } catch (Exception ignored) {
            driver.switchTo().defaultContent();
        }
    }

    public void handleAd() {
        try {
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                driver.navigate().refresh();
                Thread.sleep(2000);
            }
        } catch (Exception ignored) {}
    }

    public void applyStealth(WebDriver driver) {
        String stealthScript = "Object.defineProperty(navigator, 'webdriver', {get: () => undefined});";
        ((JavascriptExecutor) driver).executeScript(stealthScript);
    }
}
*/

package stepDefinations;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    // ---------- STATIC DRIVER AND PAGE OBJECTS ----------
    public static WebDriver driver;

    public static Logger logger;
    public static Properties configProp;

    public static LoginPage lp;
    public static AddCustomerPage addCust;
    public static SearchCustomerPage searchCust;
    public static UserRegister userReg;
    public static UserLoginPage userLogin;
    public static NewUserLoginPage newuserLoginPage;
    public static NewRegisterPage newRegisterPage;
    public static ContactUsPage contactUsPage;
    public static TestCasePage testCasePage;
    public static ProductListAndPage productListAndPage;
    public static AddToCart addToCart;
    public static HomePage homePage;
    public static CheckoutPage checkoutPage;
    public static PaymentPage paymentPage;
    public static CategoryPage categoryPage;
    public static BrandPage brandPage;
    public static ProductDetailsPage productDetailsPage;

    // ---------- STATIC LOGGER AND CONFIG ----------
    static {
        try {
            logger = Logger.getLogger("AutomationExercise");
            PropertyConfigurator.configure(
                    System.getProperty("user.dir") + "/src/test/resources/Log4j.properties"
            );

            configProp = new Properties();
            FileInputStream fis = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test/resources/Config.properties"
            );
            configProp.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Config or Log4j", e);
        }
    }

    // ---------- UTILITY METHODS ----------
    public static String randomString() {
        return RandomStringUtils.randomAlphanumeric(5);
    }

    /*public static void clickWithJS(org.openqa.selenium.WebElement element) {
        if (driver == null) {
            throw new RuntimeException("Driver is null! Check Hooks initialization.");
        }
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }*/

    public void dismissAdIfPresent() {
        try {
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                driver.switchTo().frame("aswift_1");
                try {
                    driver.switchTo().frame("ad_iframe");
                } catch (Exception ignored) {}
                driver.findElement(By.id("dismiss-button")).click();
                driver.switchTo().defaultContent();
            }
        } catch (Exception ignored) {
            driver.switchTo().defaultContent();
        }
    }

    public void handleAd() {
        try {
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                driver.navigate().refresh();
                Thread.sleep(2000);
            }
        } catch (Exception ignored) {}
    }

    /*public void handleAdIfPresentMobileSafe() {
        try {
            // Give ad a moment if it appears
            Thread.sleep(1500);

            // Case 1: Google vignette URL
            if (driver.getCurrentUrl().contains("google_vignette")) {
                logger.info("Mobile Ad detected via URL. Refreshing...");
                driver.navigate().refresh();
                Thread.sleep(2000);
            }

            // Case 2: Any iframe that looks like an ad
            driver.switchTo().defaultContent();
            if (driver.findElements(By.xpath("//iframe[contains(@src,'google')]")).size() > 0) {
                logger.info("Google Ad iframe detected. Refreshing page...");
                driver.navigate().refresh();
                Thread.sleep(2000);
            }

        } catch (Exception e) {
            logger.info("No mobile ad detected. Continuing test.");
        } finally {
            driver.switchTo().defaultContent();
        }
    }*/

    public void handleAdIfPresentMobileSafe() {
        try {
            Thread.sleep(1000);

            driver.switchTo().defaultContent();

            if (driver.findElements(By.xpath("//iframe[contains(@id,'aswift')]")).size() > 0) {
                logger.info("Ad iframe detected. Waiting for it to disappear...");
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.invisibilityOfElementLocated(
                                By.xpath("//iframe[contains(@id,'aswift')]")
                        ));
            }

        } catch (Exception e) {
            logger.info("No blocking ad detected.");
        }
    }

    /*public void removeGoogleAdsIfPresent() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript("""
               var ads = document.querySelectorAll(
                "iframe[id^='aswift'], iframe[src*='doubleclick'], div[id^='google_ads']"
            );
            ads.forEach(ad => ad.remove());
        """);

            logger.info("Google Ad iframe removed (if present)");

        } catch (Exception e) {
            logger.warn("No ad iframe found or unable to remove");
        }
    }*/

    public void applyStealth(WebDriver driver) {
        String stealthScript = "Object.defineProperty(navigator, 'webdriver', {get: () => undefined});";
        ((JavascriptExecutor) driver).executeScript(stealthScript);
    }

    public boolean isMobileExecution() {
        String executionType = configProp.getProperty("executionType");
        return executionType != null && executionType.equalsIgnoreCase("mobile");
    }


    /*public void safeClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementClickInterceptedException e) {

            logger.info("Click intercepted. Checking for ad iframe...");

            // STEP 1: If an ad iframe exists, refresh once
            if (isGoogleAdIframePresent()) {
                logger.info("Google Ad iframe detected. Refreshing page...");
                driver.navigate().refresh();
            }

            // STEP 2: Retry normal click
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            } catch (Exception retryException) {

                logger.info("Retry failed. Using JS click as FINAL fallback.");

                // STEP 3: Last-resort JS click (centralized & controlled)
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);
            }
        }
    }*/

    public void safeClick(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {

            driver.switchTo().defaultContent();

            handleAdIfPresentMobileSafe();

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

            wait.until(ExpectedConditions.elementToBeClickable(element)).click();

        } catch (ElementClickInterceptedException e) {

            logger.info("Click intercepted. Using JS click fallback.");

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }





    private boolean isGoogleAdIframePresent() {
        try {
            return !driver.findElements(By.cssSelector("iframe[id^='aswift']")).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }



}


