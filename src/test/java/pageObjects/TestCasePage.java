package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import stepDefinations.BaseClass;

import java.time.Duration;



public class TestCasePage extends BaseClass {

    public WebDriver driver;

    public TestCasePage(WebDriver rdriver) {
        driver = rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver, this);

    }

    /*@FindBy(css = "a[href='/test_cases']")
    //@CacheLookup
    WebElement testCasesLink;

    @FindBy(xpath = "//h2[@class='title text-center']//b[normalize-space()='Test Cases']")
    //@CacheLookup
    WebElement testCasesHeader;*/

    @FindBy(css = "a[href='/test_cases']")
    WebElement testCasesLink;

    @FindBy(xpath = "//h2[@class='title text-center']//b[contains(text(),'Test')]")
    WebElement testCasesHeader;



    // Action Method
    /*public void clickTestCases() {
        // Verifying visibility before interaction
        testCasesLink.click();
        // Check if ad appeared
       if (ldriver.getCurrentUrl().contains("#google_vignette")) {
            // Instead of refreshing, try to click the body to dismiss
            Actions action = new Actions(ldriver);
            action.moveByOffset(0, 0).click().build().perform();

            // Alternative: Use JS to remove the overlay if it gets stuck
            ((JavascriptExecutor) ldriver).executeScript("const elements = document.getElementsByClassName('adsbygoogle'); while (elements.length > 0) elements[0].remove();");
        }

    }*/

    public void clickTestCases() {
        safeClick(testCasesLink);   // ✅ uses your BaseClass logic
        handleAd();
    }



    /*public void verifyTestCasesPageIsVisible() {
        // 1. Wait for the element (handles potential lag from ads)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(testCasesHeader));

        // 2. Perform the assertion
        String actualText = testCasesHeader.getText();
        Assert.assertEquals(actualText, "TEST CASES", "User is NOT on the Test Cases page!");
    }*/

    public void verifyTestCasesPageIsVisible() {

        logger.info("Verifying Test Cases page");

        // Always reset frame context (ads!)
        driver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // ✅ Wait by LOCATOR, not cached WebElement
        By testCasesHeaderBy =
                By.xpath("//h2[contains(@class,'title')]//b[contains(text(),'Test')]");

        WebElement header = wait.until(
                ExpectedConditions.visibilityOfElementLocated(testCasesHeaderBy)
        );

        Assert.assertTrue(
                header.isDisplayed(),
                "User is NOT on the Test Cases page!"
        );

        logger.info("User successfully navigated to Test Cases page");
    }

}
