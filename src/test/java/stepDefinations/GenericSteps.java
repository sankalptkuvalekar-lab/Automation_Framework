package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GenericSteps extends BaseClass{

    @Given("User Launch Chrome Browser")
    public void user_launch_chrome_browser() {
        logger.info("********** Browser Already Launched **********");
    }
    @When("User open the URL {string}")
    public void user_open_url(String url) {
        logger.info("********** Opening URL **********");
        driver.get(url);

        driver.manage().window().maximize();
    }

    @Then("Close Browser")
    public void close_browser() {

        logger.info("********** Closing Browse r**********");
        driver.quit();
    }

}
