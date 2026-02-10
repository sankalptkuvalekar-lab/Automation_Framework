package stepDefinations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.NewUserLoginPage;

public class NewUserLoginPageSteps extends BaseClass{

    @When("User Click on the Login link")
    public void user_click_on_the_login_link() {
        newuserLoginPage.loginLinkClick();
    }
    @When("User enter his Email and password")
    public void user_enter_his_email_and_password() {
        newuserLoginPage.emailEnter("user76@gmail.com");
        newuserLoginPage.passwordEnter("user@123");
    }
    @When("User Click on Login button")
    public void user_click_on_login_button() {
        newuserLoginPage.clickLoginButton();
    }
    @When("User enter his {string} and {string}")
    public void user_enter_his_and(String email, String password) {

        newuserLoginPage.emailEnter(email);
        newuserLoginPage.passwordEnter(password);

    }

    //logout button
    @When("click on Logout button")
    public void click_on_logout_button() {
        newuserLoginPage.clickLpogputLink();
    }

    @Then("Verify that home page is visible successfully")
    public void verify_that_home_page_is_visible_successfully() {
        System.out.println("Verify that the Home Page Header is Visible");
        newuserLoginPage = new NewUserLoginPage(driver);
        newuserLoginPage.verifyHomePage();

    }
    @Then("Verify Login to your account is visible")
    public void verify_is_visible() {
        newuserLoginPage = new NewUserLoginPage(driver);
        newuserLoginPage.verifyloginPage();
    }
    @Then("Verify that Logged in as username is visible")
    public void verify_that_is_visible() {
        newuserLoginPage = new NewUserLoginPage(driver);
        newuserLoginPage.verifyHomePageAfterLogin();

    }

    @Then("Verify error Your email or password is incorrect! is visible")
    public void verify_error_message(){
        newuserLoginPage = new NewUserLoginPage(driver);
        newuserLoginPage.verifyHErrorMeeageofInvalidCredentials();

    }

    @Then("Verify that the user is navigated to the login Page")
    public void verify_that_the_user_is_navigated_to_the_login_page() {

        newuserLoginPage = new NewUserLoginPage(driver);
        newuserLoginPage.verifyTheLoginButtonIsVisible();
    }
}
