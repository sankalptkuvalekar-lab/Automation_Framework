package stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pageObjects.UserLoginPage;

public class UserLoginPageSteps extends BaseClass{

    @When("User Click on Login link")
    public void user_click_on_login_link() {
        userLogin.clickLoginLink();
    }
    @And("User enter his username and password")
    public void user_enter_his_username_and_password() {
        userLogin.setEmailLogin("user1@gmail.com");
        userLogin.setPwdLogin("user123");
    }
    @And("Click on Login button")
    public void click_on_login_button() {
        userLogin.clickLoginButton();
    }


}
