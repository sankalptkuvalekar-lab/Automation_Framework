package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class NewRegisterPage {

    public WebDriver driver;

    public NewRegisterPage(WebDriver rdriver) {
        driver = rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver, this);


    }

    @FindBy(css = ".signup-form h2")
    @CacheLookup
    WebElement signupHeader;

    @FindBy(css = "input[data-qa='signup-name']")
    @CacheLookup
     WebElement signupNameField;

    @FindBy( css = "input[data-qa='signup-email']")
    @CacheLookup
    WebElement signupEmailField;

    @FindBy(css = "button[data-qa='signup-button']")
    @CacheLookup
    WebElement signupBtn;

    @FindBy(xpath = "//p[contains(text(), 'already exist')]")
    @CacheLookup
    WebElement emailAlreadyExistError;

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    WebElement txtName;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement txtEmail;

    @FindBy(xpath = "//b[text()='Enter Account Information']")
     WebElement enterAccountInfoHeader;

    @FindBy(id = "id_gender1") // Mr.
    WebElement radioTitleMr;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(id = "days")
    WebElement drpDays;

    @FindBy(id = "months")
    WebElement drpMonths;

    @FindBy(id = "years")
    WebElement drpYears;

    @FindBy(id = "newsletter")
    WebElement chkNewsletter;
    @FindBy(id = "optin")
     WebElement chkSpecialOffers;

    @FindBy(id = "first_name")
    WebElement txtFirstName;

    @FindBy(id = "last_name")
    WebElement txtLastName;

    @FindBy(id = "company")
    WebElement txtCompany;

    @FindBy(id = "address1")
    WebElement txtAddress1;

    @FindBy(id = "address2")
    WebElement txtAddress2;

    @FindBy(id = "country")
    WebElement drpCountry;

    @FindBy(id = "state")
    WebElement txtState;

    @FindBy(id = "city")
    WebElement txtCity;

    @FindBy(id = "zipcode")
    WebElement txtZipcode;

    @FindBy(id = "mobile_number")
    WebElement txtMobileNumber;

    @FindBy(xpath = "//button[@data-qa='create-account']")
    WebElement btnCreateAccount;

    @FindBy(xpath = "//b[text()='Account Created!']")
    WebElement msgAccountCreated;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    WebElement btnContinue;

    @FindBy(xpath = "//a[contains(text(),'Delete Account')]")
     WebElement btnDeleteAccount;

    @FindBy(xpath = "//b[text()='Account Deleted!']")
     WebElement msgAccountDeleted;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
     WebElement btnFinalContinue;



    public void verifyRegisterPageTitle() {
        System.out.println("verifying the home page");
        Assert.assertEquals("New User Signup!", signupHeader.getText(), "Register Page Text Doesn't match");
    }

    public void registerNameField(String name ){
        signupNameField.sendKeys(name);
    }
    public void registerEmaiField(String email ){
        signupEmailField.sendKeys(email);
    }

    public void clickSignupButton( ){
        signupBtn.click();
    }

    public void verifyEmailExistErrorIsVisible() {
        Assert.assertTrue(emailAlreadyExistError.isDisplayed(), "Error message 'Email Address already exist!' is not visible!");
        Assert.assertEquals(emailAlreadyExistError.getText(), "Email Address already exist!");
    }
//register floe

    public void enterNameAndEmail(String name, String email) {
        txtName.sendKeys(name);
        txtEmail.sendKeys(email);
    }

    public boolean isEnterAccountInfoVisible() {
        try {
            return enterAccountInfoHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void fillAccountDetails(String password, String day, String month, String year) {
        // Add an Explicit Wait to ensure the element is ready
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(radioTitleMr));

        radioTitleMr.click();
        txtPassword.sendKeys(password);

        new Select(drpDays).selectByVisibleText(day);
        new Select(drpMonths).selectByVisibleText(month);
        new Select(drpYears).selectByVisibleText(year);
    }
  //clickNewsletterCheckbox
  public void clickNewsletterCheckbox() {
      // Cast the shared driver to JavascriptExecutor
      JavascriptExecutor js = (JavascriptExecutor) driver;

      if (!chkNewsletter.isSelected()) {
          // Use JavaScript to click the element directly
          js.executeScript("arguments[0].click();", chkNewsletter);
      }
  }
    public void selectSpecialOffers() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if (!chkSpecialOffers.isSelected()) {
            js.executeScript("arguments[0].click();", chkSpecialOffers);
        }
    }
    public void fillAddressDetails(String fName, String lName, String comp, String addr1, String addr2,
                                   String country, String state, String city, String zip, String mobile) {
        txtFirstName.sendKeys(fName);
        txtLastName.sendKeys(lName);
        txtCompany.sendKeys(comp);
        txtAddress1.sendKeys(addr1);
        txtAddress2.sendKeys(addr2);

        new Select(drpCountry).selectByVisibleText(country);

        txtState.sendKeys(state);
        txtCity.sendKeys(city);
        txtZipcode.sendKeys(zip);
        txtMobileNumber.sendKeys(mobile);
    }
    public void clickCreateAccount() {
        // Use JavaScript click to bypass any potential ad overlays
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", btnCreateAccount);
    }

    public boolean isAccountCreatedVisible() {
        try {
            // It's good practice to wait a second for the page transition
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(msgAccountCreated)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickContinue() {
        // Using JavaScript click to bypass full-screen ads or overlays
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", btnContinue);
    }

    public void clickDeleteAccount() {
        // Javascript click ensures we bypass any ad overlays in the header
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", btnDeleteAccount);
    }

    public boolean isAccountDeletedVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(msgAccountDeleted)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickFinalContinue() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", btnFinalContinue);
    }
}



