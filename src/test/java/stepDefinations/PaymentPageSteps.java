package stepDefinations;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObjects.PaymentPage;
import java.io.File;

public class PaymentPageSteps extends BaseClass{

    @Then("Enter payment details: Name on Card, Card Number, CVC, Expiration date")
    public void enter_payment_details_name_on_card_card_number_cvc_expiration_date() {
        logger.info("********** Entering Payment Information **********");

        // You can also pass these as parameters if using a Scenario Outline
        paymentPage.enterPaymentDetails("John Doe", "4242424242424242", "311", "12", "2028");
        handleAd();

    }

    @Then("Click Pay and Confirm Order button")
    public void click_pay_and_confirm_order_button() {
        logger.info("********** Confirming the Order **********");
        paymentPage.clickPayAndConfirm();
        handleAd();
    }

    @Then("Verify success message Your order has been placed successfully")
    public void verify_success_message_your_order_has_been_placed_successfully() {
        logger.info("********** Verifying Order Success Message **********");

        boolean isVisible = paymentPage.isSuccessMessageVisible();

        // This assertion triggers the screenshot in Hooks if it fails
        Assert.assertTrue(isVisible, "The order success message was not displayed!");


        logger.info("Order successfully verified!");
        handleAd();
    }

    @Then("Click 'Download Invoice' button and verify invoice is downloaded successfully")
    public void click_download_invoice_and_verify() throws InterruptedException {
        logger.info("********** Downloading and Verifying Invoice **********");

        // 1. Clear ads before interacting with the button
        handleAd();

        // 2. Click the download button
        paymentPage.clickDownloadInvoice();

        // 3. Wait for the download to complete (files take a moment to write to disk)
        Thread.sleep(3000);

        // 4. Verify file exists in the default download directory
        String downloadPath = System.getProperty("user.home") + "/Downloads";
        String fileName = "invoice.txt"; // The site typically downloads a .txt invoice

        File file = new File(downloadPath + "/" + fileName);

        Assert.assertTrue(file.exists(), "Invoice file was not found in: " + downloadPath);
        logger.info("Invoice downloaded successfully: " + file.getAbsolutePath());

        // 5. Clean up: Delete the file after verification so next test starts fresh
        if (file.exists()) {
            file.delete();
        }
    }

    @Then("Click 'Continue' button")
    public void click_continue_button1() {
        logger.info("********** Clicking Continue Button **********");

        // 1. Clear any current ads before clicking
        handleAd();

        // 2. Perform the click
        paymentPage.clickContinue();

        // 3. Clear the ad again if it was triggered by the button click
        handleAd();

        logger.info("Successfully clicked Continue and cleared potential ads.");
    }

}
