package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinations.BaseClass;

import java.time.Duration;

public class ProductDetailsPage extends BaseClass {


    public WebDriver driver;

    public ProductDetailsPage(WebDriver rdriver) {
        driver = rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver, this);


    }

    @FindBy(xpath = "//a[@href='#reviews' and text()='Write Your Review']")
    WebElement reviewHeading;

    @FindBy(id = "name")
     WebElement inputReviewName;

    @FindBy(id = "email")
     WebElement inputReviewEmail;

    @FindBy(id = "review")
  WebElement txtAreaReview;

    @FindBy(id = "button-review")
     WebElement btnSubmitReview;

    @FindBy(xpath = "//div[@class='alert-success alert']/span")
    WebElement successAlert;



    public boolean isReviewSectionVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Scroll to the element first to ensure it's in the DOM viewport
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", reviewHeading);

            return wait.until(ExpectedConditions.visibilityOf(reviewHeading)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterReviewDetails(String name, String email, String reviewText) {
        inputReviewName.sendKeys(name);
        inputReviewEmail.sendKeys(email);
        txtAreaReview.sendKeys(reviewText);
    }

    public void clickSubmitReview() {
        btnSubmitReview.click();
    }

    public String getSuccessMessageText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(successAlert)).getText();
        } catch (Exception e) {
            return "Success message not found";
        }
    }


}
