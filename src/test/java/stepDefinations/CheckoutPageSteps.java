package stepDefinations;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObjects.CheckoutPage;


public class CheckoutPageSteps extends BaseClass{

    @Then("Click Proceed To Checkout")
    public void Click_proceed_to_checkout() {
        logger.info("********** Clicking Proceed To Checkout Button **********");
        // Ensure cartPage is initialized in your BaseClass or here
        addToCart.clickProceedToCheckout();
        handleAd();
    }

    @Then("Verify Address Details and Review Your Order")
    public void verify_address_details_and_review_your_order() {
        checkoutPage=new CheckoutPage(driver);
        logger.info("********** Verifying Checkout Information **********");

        // Check Address Section
        boolean isAddressVisible = checkoutPage.isAddressDetailsDisplayed();
        Assert.assertTrue(isAddressVisible, "Address Details section is not visible!");

        // Check Review Order Section
        boolean isReviewVisible = checkoutPage.isReviewOrderDisplayed();
        Assert.assertTrue(isReviewVisible, "Review Your Order section is not visible!");
        handleAd();

        logger.info("Address Details and Review Order are successfully verified.");
    }

    @Then("Enter description in comment text area and click Place Order")
    public void enter_description_in_comment_text_area_and_click_place_order() {
        logger.info("********** Finalizing Order with Comments **********");

        checkoutPage.enterComment("This is a test order for automation verification.");
        checkoutPage.clickPlaceOrder();
        handleAd();
    }

    @Then("Verify that the delivery address is same address filled at the time registration of account")
    public void verify_delivery_address_matches_registration() {
        checkoutPage = new CheckoutPage(driver);
        handleAd();

        String actualAddress = checkoutPage.getDeliveryCityStateZipText();
        String expectedAddress = "Los Angeles California 90001";

        // THE FIX:
        // 1. Replace Unicode non-breaking spaces (\u00A0) with standard spaces
        // 2. Replace all other whitespace (\s+) with a single space
        String cleanActual = actualAddress.replace("\u00A0", " ").replaceAll("\\s+", " ").trim();
        String cleanExpected = expectedAddress.replace("\u00A0", " ").replaceAll("\\s+", " ").trim();

        logger.info("Comparing Final Cleaned Actual: [" + cleanActual + "] with Cleaned Expected: [" + cleanExpected + "]");

        // Using contains is safer for address blocks with dynamic formatting
        Assert.assertTrue(cleanActual.contains(cleanExpected),
                "Mismatch found! Actual string contains hidden characters. Length Actual: "
                        + cleanActual.length() + " vs Expected: " + cleanExpected.length());
    }
    @Then("Verify that the billing address is same address filled at the time registration of account")
    public void verify_billing_address_matches_registration() {
        logger.info("********** Verifying Billing Address **********");

        // 1. Clear any ads that appeared during the transition to Checkout
        handleAd();

        // 2. Fetch the text using the flexible locator
        String actualBillingText = checkoutPage.getBillingCityStateZipText();

        // 3. Normalize whitespace to handle non-breaking spaces (&nbsp;) or tabs
        String cleanActual = actualBillingText.replace("\u00A0", " ").replaceAll("\\s+", " ").trim();
        String expectedBilling = "Los Angeles California 90001"; // Match your registration data
        String cleanExpected = expectedBilling.replaceAll("\\s+", " ").trim();

        logger.info("Comparing Actual: [" + cleanActual + "] with Expected: [" + cleanExpected + "]");

        // 4. Assertion
        Assert.assertTrue(cleanActual.contains(cleanExpected),
                "Billing address mismatch! Found: [" + cleanActual + "]");
    }

}
