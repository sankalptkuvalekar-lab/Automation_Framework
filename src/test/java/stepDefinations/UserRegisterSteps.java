package stepDefinations;

import io.cucumber.java.en.And;
import pageObjects.UserRegister;

public class UserRegisterSteps extends BaseClass{

    @And("User Click on Register link")
    public void user_click_on_register_link() {
        userReg.clickRegisterlink();
    }

    @And("User enter his info")
    public void user_enter_his_info() {
        userReg.setGenderBullet();
        userReg.setFirstNametxt("user1");
        userReg.setLastNametxt("demo");
        userReg.setEmailtxt("user1@gmail.com");
        userReg.setCompanyNametxt("comapny");
        userReg.setPasswordtxt("user1@123");
        userReg.setConfirmPasswordtxt("user1@123");
    }
    @And("Click on Register button")
    public void click_on_register_button() {
        userReg.clickRegisaterbutton();
    }

    @And("click on Continue")
    public void click_on_continue() {
        userReg.clickContinueButton();
    }
}
