package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ContactUsPage {

    public WebDriver driver;

    public ContactUsPage(WebDriver rdriver) {
        driver = rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver, this);


    }

    @FindBy(xpath = "//a[contains(text(), 'Contact us')]")
    @CacheLookup
    WebElement contactUsLink;

    @FindBy(css = "h2.title.text-center")
    @CacheLookup
    WebElement getInTouchHeader;

    @FindBy(css = "input[data-qa='name']")
    @CacheLookup
    WebElement contactNameField;

    @FindBy(css = "input[data-qa='email']")
    @CacheLookup
    WebElement contactEmailField;

    @FindBy(css = "input[data-qa='subject']")
    @CacheLookup
    WebElement contactSubjectField;

    @FindBy(css = "textarea[data-qa='message']")
    @CacheLookup
    WebElement contactMessageField;

    @FindBy(name = "upload_file")
    @CacheLookup
    WebElement uploadFileField;

    @FindBy(css = "input[data-qa='submit-button']")
    @CacheLookup
    WebElement contactSubmitBtn;

    @FindBy(css = "div.status.alert-success")
    @CacheLookup
    WebElement contactSuccessMsg;

    @FindBy(css = "a.btn-success[href='/']")
    @CacheLookup
    WebElement contactHomeBtn;


public void clickContactUs(){
    contactUsLink.click();

}


    public void verifyGetInTouchVisible() {

        Assert.assertTrue(getInTouchHeader.isDisplayed(), "'Get In Touch' header is not visible!");


        String actualText = getInTouchHeader.getText();
        Assert.assertEquals(actualText, "CONTACT US", "Header text mismatch!");

    }


    public void enterContactName(String name) {
        contactNameField.clear();
        contactNameField.sendKeys(name);
    }

    // Action Method
    public void enterContactEmail(String email) {
        contactEmailField.clear();
        contactEmailField.sendKeys(email);
    }


    public void enterContactSubject(String subject) {
        contactSubjectField.clear();
        contactSubjectField.sendKeys(subject);
    }


    public void enterContactMessage(String message) {
        contactMessageField.clear();
        contactMessageField.sendKeys(message);
    }



    public void uploadDocument(String filePath) {
        // We use sendKeys to provide the path directly to the input tag
        uploadFileField.sendKeys(filePath);
    }
      public void clickSubmit() {
         contactSubmitBtn.click();

      }

    public void verifySuccessMessage() {

        Assert.assertTrue(contactSuccessMsg.isDisplayed(), "Success message is not visible!");

        String expected = "Success! Your details have been submitted successfully.";
        Assert.assertEquals(contactSuccessMsg.getText(), expected);
    }

    public void clickHomeButton() {

       // Assert.assertTrue(contactHomeBtn.isDisplayed(), "Home button is not visible!");
        contactHomeBtn.click();
    }

}


