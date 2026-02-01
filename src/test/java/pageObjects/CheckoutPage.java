package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    public WebDriver driver;

    public CheckoutPage(WebDriver rdriver) {
        driver = rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver, this);


    }
    @FindBy(xpath = "//h2[text()='Address Details']")
   WebElement headAddressDetails;

    @FindBy(xpath = "//h2[text()='Review Your Order']")
     WebElement headReviewOrder;

    // Delivery Address List
    @FindBy(id = "address_delivery")
     WebElement listDeliveryAddress;

    // Billing Address List
    @FindBy(id = "address_invoice")
     WebElement listBillingAddress;

    @FindBy(xpath = "//textarea[@name='message']")
     WebElement txtCommentArea;

    @FindBy(xpath = "//a[@href='/payment']")
     WebElement btnPlaceOrder;

    //verify address
    // Locators for Delivery Address elements
    @FindBy(xpath = "//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][1]")
     WebElement deliveryAddressLine1;

    @FindBy(xpath = "//ul[@id='address_delivery']//li[contains(@class, 'address_city')]")
    WebElement deliveryCityStateZip;

    @FindBy(xpath = "//ul[@id='address_invoice']//li[@class='address_address1 address_address2'][1]")
     WebElement billingAddressLine1;

    @FindBy(xpath = "//ul[@id='address_invoice']//li[contains(@class, 'address_city')]")
   WebElement billingCityStateZip;

    public boolean isAddressDetailsDisplayed() {
        return headAddressDetails.isDisplayed() && listDeliveryAddress.isDisplayed();
    }

    public boolean isReviewOrderDisplayed() {
        return headReviewOrder.isDisplayed();
    }

    public void enterComment(String comment) {
        // Ensuring the element is visible before typing
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(txtCommentArea)).sendKeys(comment);
    }

    public void clickPlaceOrder() {
        // Using JS click because this button is often at the very bottom,
        // frequently obstructed by floating "Join Newsletter" ads or Google Ads.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", btnPlaceOrder);
    }

    public String getDeliveryAddressText() {
        return deliveryAddressLine1.getText().trim();
    }

    public String getDeliveryCityStateZipText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Wait specifically for the element to be present and visible
            return wait.until(ExpectedConditions.visibilityOf(deliveryCityStateZip)).getText().trim();
        } catch (Exception e) {
            return "Element not found after wait";
        }
    }
    public String getBillingAddressText() {
        return billingAddressLine1.getText().trim();
    }

    public String getBillingCityStateZipText() {
        try {
            // Use an explicit wait to allow the page to settle after any ad refresh
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            return wait.until(ExpectedConditions.visibilityOf(billingCityStateZip)).getText().trim();
        } catch (Exception e) {
            return "Billing element not found. URL: " + driver.getCurrentUrl();
        }
    }
}
