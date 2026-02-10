package stepDefinations;

import io.cucumber.java.en.*;
import org.testng.Assert;

public class SubscriptionsSteps extends BaseClass {

    @When("User enters email {string} in subscription input")
    public void user_enters_email_in_subscription_input(String email) {
        // homePage is your initialized Page Object instance
        homePage.enterSubscriptionEmail(email);
    }

    @When("User clicks on subscribe button")
    public void user_clicks_on_subscribe_button() {
        // Calling the method from your page object instance
        homePage.clickSubscribeButton();
    }

    @Then("User should see success message {string}")
    public void user_should_see_success_message(String expectedMsg) {
        String actualMsg = homePage.getSubscriptionSuccessMessage();

        // Using Assert to verify the actual text matches the feature file
        Assert.assertEquals(actualMsg, expectedMsg, "Subscription success message mismatch!");
    }
}
