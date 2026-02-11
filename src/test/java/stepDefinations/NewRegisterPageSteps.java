package stepDefinations;

import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.NewRegisterPage;

import java.time.Duration;

public class NewRegisterPageSteps extends BaseClass{

@Then("Verify New User Signup is visible")
    public void verify_new_user_signup_is_visible() {
        newRegisterPage.verifyRegisterPageTitle();

    }
    @Then("Enter name and already registered email address")
    public void enter_name_and_already_registered_email_address() {
        newRegisterPage.registerNameField("user67");
        newRegisterPage.registerEmaiField("user66@gmail.com");

    }
    @Then("Click Signup button")
    public void click_signup_button() {
        newRegisterPage.clickSignupButton();

    }
    @Then("Verify error Email Address already exist! is visible")
    public void verify_error_email_address_already_exist_is_visible() throws InterruptedException {
        System.out.println("Verify error Email Address already exist! is visible");
        Thread.sleep(3000);
        newRegisterPage.verifyEmailExistErrorIsVisible();

    }

    @Then("Enter name and email address")
    public void enter_name_and_email_address() {
        String name = "user66";
        String email = randomString() + "@gmail.com";

        // Call the method from your initialized Page Object
        newRegisterPage.enterNameAndEmail(name, email);
        handleAd();

        logger.info("********** Entered name and email for registration **********");
    }
    @Then("Verify that ENTER ACCOUNT INFORMATION is visible")
    public void verify_that_eis_visible( ) {

        boolean isVisible = newRegisterPage.isEnterAccountInfoVisible();

        // Logging failure or success for the report
        if (!isVisible) {
            logger.error("ENTER ACCOUNT INFORMATION header not found!");
        }

        Assert.assertTrue(isVisible, "'ENTER ACCOUNT INFORMATION' is not visible on the page.");
    }

    @Then("Fill details: Title, Name, Email, Password, Date of birth")
    public void fill_details_title_name_email_password_date_of_birth() {
        logger.info("********** Filling Account Information **********");

        newRegisterPage.fillAccountDetails("P@ssword123", "10", "May", "1990");
        handleAd();
    }
    @Then("Select checkbox Sign up for our newsletter!")
    public void select_checkbox_sign_up_for_our_newsletter() {
        logger.info("********** Selecting Newsletter Checkbox **********");
        newRegisterPage.clickNewsletterCheckbox();
        handleAd();

    }
    @Then("Select checkbox Receive special offers from our partners!")
    public void select_checkbox_receive_special_offers_from_our_partners() {
        logger.info("********** Selecting Special Offers Checkbox **********");
        newRegisterPage.selectSpecialOffers();
        handleAd();
    }
    @Then("Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number")
    public void fill_details_first_name_last_name_company_address_address2_country_state_city_zipcode_mobile_number() {
        newRegisterPage = new NewRegisterPage(driver);
        logger.info("********** Filling Address Information **********");

        newRegisterPage.fillAddressDetails(
                "John", "Doe", "Tech Corp", "123 Street", "Suite 100",
                "United States", "California", "Los Angeles", "90001", "1234567890"
        );
    }
    @Then("Click Create Account button")
    public void click_create_account_button() {
        logger.info("********** Clicking Create Account Button **********");
        newRegisterPage.clickCreateAccount();
        handleAd();

    }
    /*@Then("Verify that ACCOUNT CREATED is visible")
    public void verify_that_account_created_is_visible() {
        logger.info("********** Verifying Account Creation Success **********");

        boolean isVisible = newRegisterPage.isAccountCreatedVisible();

        // If this assertion fails, the @After hook takes over
        Assert.assertTrue(isVisible, "Success message 'ACCOUNT CREATED!' was not visible.");
        handleAd();
    }*/

    @Then("Verify that ACCOUNT CREATED is visible")
    public void verify_that_account_created_is_visible() {

        logger.info("********** Verifying Account Creation Success **********");

        // ✅ Handle ads FIRST
        handleAd();

        boolean isVisible = newRegisterPage.isAccountCreatedVisible();

        Assert.assertTrue(isVisible, "Success message 'ACCOUNT CREATED!' was not visible.");
    }

    @Then("Click Continue button")
    public void click_continue_button() {
        logger.info("********** Clicking Continue Button **********");
        newRegisterPage.clickContinue();
        handleAd();

    }
    @Then("Click Delete Account button")
    public void click_delete_account_button() {
        logger.info("********** Initiating Account Deletion **********");
        newRegisterPage.clickDeleteAccount();
        handleAd();
    }
    @Then("Verify that ACCOUNT DELETED is visible and click Continue button")
    /*public void verify_that_account_deleted_is_visible_and_click_button() {
        logger.info("********** Verifying Account Deletion and Closing Session **********");

        // 1. Verify Visibility
        boolean isVisible = newRegisterPage.isAccountDeletedVisible();
        Assert.assertTrue(isVisible, "Success message 'ACCOUNT DELETED!' was not visible.");

        // 2. Click Continue
        newRegisterPage.clickFinalContinue();
        handleAd();
    }*/

    public void verify_that_account_deleted_is_visible_and_click_button() {
        logger.info("********** Verifying Account Deletion and Closing Session **********");

        handleAd(); // ensure no ad is blocking the element

        // Retry mechanism + case-insensitive locator
        boolean isVisible = false;
        int attempts = 0;
        while (attempts < 3) { // try 3 times
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // Case-insensitive XPath for web and mobile
                WebElement deletedMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'account deleted')]")
                ));

                if (deletedMsg.isDisplayed()) {
                    isVisible = true;
                    logger.info("✅ 'ACCOUNT DELETED!' message is visible");
                    break;
                }
            } catch (Exception e) {
                logger.warn("Attempt " + (attempts + 1) + ": 'ACCOUNT DELETED!' not visible yet, retrying...");
                handleAd();
                sleep(1000); // small wait before retry
            }
            attempts++;
        }

        Assert.assertTrue(isVisible, "Success message 'ACCOUNT DELETED!' was not visible after retries.");

        // Click Continue safely
        try {
            newRegisterPage.clickFinalContinue();
            handleAd();
        } catch (Exception e) {
            logger.warn("Click Continue intercepted, using JS fallback");
            WebElement continueBtn = driver.findElement(By.xpath("//a[contains(text(),'Continue')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);
        }
    }

    // Helper sleep
    private void sleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { /* ignore */ }
    }
}
