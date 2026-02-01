package stepDefinations;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObjects.PaymentPage;
import pageObjects.ProductDetailsPage;

public class ProductDetailsSteps extends BaseClass{

    @Then("Verify 'Write Your Review' is visible")
    public void verify_write_your_review_is_visible() {
        logger.info("********** Verifying Review Section Visibility **********");
        productDetailsPage= new ProductDetailsPage(driver);
        handleAd();
        boolean isVisible = productDetailsPage.isReviewSectionVisible();

        Assert.assertTrue(isVisible, "The 'Write Your Review' section is not visible on the page!");
        logger.info("Successfully verified 'Write Your Review' section.");

    }

    @Then("Enter name, email and review")
    public void enter_name_email_and_review() {
        logger.info("********** Filling out the Product Review form **********");

        // Ensure no ad is blocking the input fields
        handleAd();

        // You can pass hardcoded strings or use parameters from the feature file
        productDetailsPage.enterReviewDetails("Test User", "testuser@example.com", "This product has excellent quality and fits perfectly.");
    }

    @Then("Click on Submit button")
    public void click_on_submit_button() {
        productDetailsPage.clickSubmitReview();
        // After clicking submit, an ad might trigger or a success message appear
        handleAd();
        logger.info("********** Clicked Review Submit Button **********");
    }

    @Then("Verify success message {string}")
    public void verify_success_message(String expectedMessage) {
        logger.info("********** Verifying Review Success Message **********");

        // Clear any potential ad redirecting the page after the Submit click
        handleAd();

        String actualMessage = productDetailsPage.getSuccessMessageText();

        logger.info("Expected: " + expectedMessage);
        logger.info("Actual: " + actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage, "The success message text does not match!");
    }
}
