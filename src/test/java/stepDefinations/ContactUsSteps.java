package stepDefinations;

import io.cucumber.java.en.*;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import pageObjects.ContactUsPage;

public class ContactUsSteps extends BaseClass {

    @Then("Click on Contact Us button")
    public void click_on_contact_us_button() {
        contactUsPage.clickContactUs();

    }
    @Then("Verify CONTACT US is visible")
    public void verify_get_in_touch_is_visible() {
        contactUsPage.verifyGetInTouchVisible();



    }
    @Then("Enter name, email, subject and message")
    public void enter_name_email_subject_and_message() {
        contactUsPage.enterContactName("user66");
        contactUsPage.enterContactEmail("user66@gmail.com");
        contactUsPage.enterContactSubject("Technical Issue");
        contactUsPage.enterContactMessage("Hello, I am having trouble accessing my account. Please help.");

    }
    @Then("Upload file")
    public void upload_file() {
        String path = "/Users/sankalp/Desktop/testscreenshot.png";
        contactUsPage.uploadDocument(path);

    }
    @Then("Click Submit button")
    public void click_submit_button() {
        contactUsPage.clickSubmit();

    }
    @Then("Click OK button")
    public void click_ok_button() {
        Alert alert = driver.switchTo().alert();
        alert.accept();

    }
    @Then("Verify success message Success! Your details have been submitted successfully. is visible")
    public void verify_success_message_success_your_details_have_been_submitted_successfully_is_visible() {
        System.out.println("verifying the success message of form submission");
        contactUsPage.verifySuccessMessage();

    }
    @Then("Click Home button and verify that landed to home page successfully")
    public void click_home_button_and_verify_that_landed_to_home_page_successfully() {
        contactUsPage.clickHomeButton();

        // Check if the URL contains the base domain, ignoring the #google_vignette suffix
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("automationexercise.com"),
                "Did not land on the home page! Actual URL: " + actualUrl);
    }
}
