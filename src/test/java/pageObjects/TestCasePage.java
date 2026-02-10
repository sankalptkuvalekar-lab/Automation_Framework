package pageObjects;

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



public class TestCasePage {

    public WebDriver driver;

    public TestCasePage(WebDriver rdriver) {
        driver = rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver, this);

    }

    @FindBy(css = "a[href='/test_cases']")
    @CacheLookup
    WebElement testCasesLink;

   // @FindBy(xpath = "//h2[@class='title text-center']//b[normalize-space()='Test Cases']")
   @FindBy(xpath = "//b[contains(translate(text(), 'TESTCASES', 'testcases'), 'test cases')]")
    @CacheLookup
    WebElement testCasesHeader;


    // Action Method
    public void clickTestCases() {
        // Verifying visibility before interaction
        testCasesLink.click();

        // Check if ad appeared
       /* if (ldriver.getCurrentUrl().contains("#google_vignette")) {
            // Instead of refreshing, try to click the body to dismiss
            Actions action = new Actions(ldriver);
            action.moveByOffset(0, 0).click().build().perform();

            // Alternative: Use JS to remove the overlay if it gets stuck
            ((JavascriptExecutor) ldriver).executeScript("const elements = document.getElementsByClassName('adsbygoogle'); while (elements.length > 0) elements[0].remove();");
        }*/

    }


    public void verifyTestCasesPageIsVisible() {
        // 1. Wait for the element (handles potential lag from ads)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(testCasesHeader));

        // 2. Perform the assertion
        String actualText = testCasesHeader.getText();
        Assert.assertEquals(actualText, "TEST CASES", "User is NOT on the Test Cases page!");
    }


}
