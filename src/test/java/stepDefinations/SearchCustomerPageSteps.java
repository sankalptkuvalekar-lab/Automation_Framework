package stepDefinations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SearchCustomerPageSteps extends BaseClass{

    @When("Enter customer Email")
    public void enter_customer_email() {
        logger.info("********** Searching customer by email id **********");
        searchCust.setEmail("victoria_victotia@nopCommerce.com");

    }
    @When("Click on Search button")
    public void click_on_search_button() throws InterruptedException {

        searchCust.clickSearch();
        Thread.sleep(3000);

    }
    @Then("User should found Email in the Search table")
    public void user_should_found_email_in_the_search_table() {

        boolean status= searchCust.searchCustomerByEmail("victoria_victotia@nopCommerce.com");
        Assert.assertEquals(status,true);
    }

    //steps for searchung a customer using email FirstName and LatNmae.........

    @When("Enter customer FirstName")
    public void enter_customer_first_name() {
        logger.info("********** Searching customer by Name*********");

        searchCust.setFirstName("victoria");
    }
    @When("Enter customer LastName")
    public void enter_customer_last_name() {
        searchCust.setLastName("Terces");
    }
    @Then("User should found Name in the Search table")
    public void user_should_found_name_in_the_search_table() {
        boolean status=searchCust.searchCustomerByName("Victoria Terces");
        Assert.assertEquals(status,true);
    }
}
