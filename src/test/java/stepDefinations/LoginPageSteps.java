package stepDefinations;

import io.cucumber.java.en.When;
import pageObjects.LoginPage;

public class LoginPageSteps extends BaseClass{

    public void user_enters_email_as_and_password_as(String email, String password) {
        logger.info("********** Providing Login Details **********");
        lp.setPassword(email);
        lp.setPassword(password);

    }

    @When("click on Login")
    public void click_on_login() throws InterruptedException {
        logger.info("********** Started Login Process **********");
        Thread.sleep(3000);
        lp.clickLogin();
        Thread.sleep(3000);

    }

    @When("user click on Logout link")
    public void user_click_on_logout_link() throws InterruptedException {
        logger.info("********** Click on Logout link **********");
        lp.clickLogout();
        Thread.sleep(3000);
    }
}
