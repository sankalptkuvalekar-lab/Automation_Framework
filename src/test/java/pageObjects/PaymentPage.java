package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {

    public WebDriver driver;

    public PaymentPage(WebDriver rdriver){
        driver=rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver,this);


    }


    @FindBy(name = "name_on_card")
    WebElement txtNameOnCard;

    @FindBy(name = "card_number")
    WebElement txtCardNumber;

    @FindBy(name = "cvc")
     WebElement txtCvc;

    @FindBy(name = "expiry_month")
    WebElement txtExpiryMonth;

    @FindBy(name = "expiry_year")
     WebElement txtExpiryYear;

    @FindBy(id = "submit")
    WebElement btnPayAndConfirm;

    @FindBy(xpath = "//*[contains(text(),'Order Placed!')] | //p[contains(text(),'Congratulations! Your order has been confirmed!')]")
     WebElement msgOrderSuccess;

    @FindBy(xpath = "//h2[@data-qa='order-placed']/b")
     WebElement orderSuccessMsg;

    @FindBy(css = "a.check_out") // Selects the 'Download Invoice' button
     WebElement btnDownloadInvoice;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
     WebElement btnContinue;

    public void enterPaymentDetails(String name, String cardNum, String cvc, String month, String year) {
        txtNameOnCard.sendKeys(name);
        txtCardNumber.sendKeys(cardNum);
        txtCvc.sendKeys(cvc);
        txtExpiryMonth.sendKeys(month);
        txtExpiryYear.sendKeys(year);
    }

    public void clickPayAndConfirm() {
        // Safe JavaScript click to bypass any ad overlays on the 'Pay' button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", btnPayAndConfirm);
    }

    public boolean isSuccessMessageVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(msgOrderSuccess)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public String getOrderSuccessMessageText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(orderSuccessMsg)).getText().trim();
        } catch (Exception e) {
            return "Success message element not found";
        }
    }

    public void clickDownloadInvoice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnDownloadInvoice)).click();
    }

    public void clickContinue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for the button to be clickable before performing the action
        wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
    }

}
