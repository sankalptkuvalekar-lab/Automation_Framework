package stepDefinations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.AddCustomerPage;

public class AddCustomerPageSteps extends BaseClass {

    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
        Assert.assertEquals(addCust.getPageTitle(),"Dashboard / nopCommerce administration");
    }
    @When("User click on customers Menu")
    public void user_click_on_customers_menu() throws InterruptedException {
        Thread.sleep(3000);
        addCust.clickOnCustomersMenu();
    }
    @When("click on customers Menu Item")
    public void click_on_customers_menu_item() throws InterruptedException {
        Thread.sleep(2000);
        addCust.clickOnCustomersMenuItem();
    }
    @When("click on Add new button")
    public void click_on_add_new_button() throws InterruptedException {
        addCust.clickOnAddnew();
        Thread.sleep(2000);
    }

    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() {
        Assert.assertEquals(addCust.getPageTitle(),"Add a new customer/ nopCommerce administration");
    }

    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {

        logger.info("********** Adding New Customer **********");
        logger.info("********** Providing Customer Details **********");
        String email=randomString()+"@gmail.com";

        addCust.setEmail(email);
        addCust.setPassword("test123");

        addCust.setCustomerRoles("Guest");
        Thread.sleep(3000);

        addCust.setManagerOfVendor("Vendor 2");
        addCust.setGender("Male");
        addCust.setFirstName("Pavan");
        addCust.setLastName("Kumar");
        addCust.setDob("7/05/1985"); // Format: D/MM/YYY
        addCust.setCompanyName("busyQA");
        addCust.setAdminContent("This is for testing........");
    }

    @When("click on Save button")
    public void click_on_save_button() throws InterruptedException {
        logger.info("********** Saving Customer Data **********");
        addCust.clickOnSave();
        Thread.sleep(3000);
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String msg) {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
                .contains("The ew customerhas been added successfully"));
    }


}
