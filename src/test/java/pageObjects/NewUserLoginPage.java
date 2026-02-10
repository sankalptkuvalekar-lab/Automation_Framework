package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class NewUserLoginPage {

    public WebDriver driver;

    public NewUserLoginPage(WebDriver rdriver){
        driver=rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver,this);


    }

    @FindBy(linkText = "Signup / Login")
    @CacheLookup
    WebElement loginLink;

    @FindBy(name = "email")
    @CacheLookup
    WebElement emailTxt;

    @FindBy(name = "password")
    @CacheLookup
    WebElement passwordTxt;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    @CacheLookup
    WebElement loginBtn;

    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    @CacheLookup
    WebElement logoutLibnk;

   @FindBy(xpath = "//h1[contains(., 'Automation')]")
   @CacheLookup
   WebElement homePageIsDisplayed;

   @FindBy(css = ".login-form h2")
   @CacheLookup
   WebElement loginToAccountIsDisplayed;

   // @FindBy(css = "li a i.fa-user + b")
   @FindBy(xpath = "//a[contains(text(), 'Logged in as')]//b")
    @CacheLookup
    WebElement loggedInAsUser;

    @FindBy(css = ".login-form p[style*='color: red']")
    @CacheLookup
    WebElement incorrectLoginCredMessage;

    @FindBy(css = "a[href='/login']")
    @CacheLookup
    WebElement signupLoginLink;

    @FindBy(id = "newsletter")
    WebElement chkNewsletter;



    //action methods

    public void loginLinkClick()
    {
        loginLink.click();
    }

    public void emailEnter(String Email){
        emailTxt.sendKeys(Email);
    }

    public void passwordEnter(String Password){
        passwordTxt.sendKeys(Password);
    }
    public void clickLoginButton(){
        loginBtn.click();
    }

    public void clickLpogputLink() {
        logoutLibnk.click();
    }

    public void verifyHomePage(){
        System.out.println("verifying the home page");
        Assert.assertEquals("AutomationExercise", homePageIsDisplayed.getText(), "Home Page Text Doesn't match");
    }

    public void verifyloginPage(){
        System.out.println("verifying the login page");
        Assert.assertEquals("Login to your account",loginToAccountIsDisplayed .getText(), "Login To your Account Text Doesn't match");
    }

    public void verifyHomePageAfterLogin(){
        System.out.println("verifying the logged in as a user text");
        Assert.assertEquals("user66", loggedInAsUser.getText(), "Logged in as username Text Doesn't match");
    }

    public void verifyHErrorMeeageofInvalidCredentials(){
        System.out.println("verifying the error message when lopgged in with the invalid credentials");
        Assert.assertEquals("Your email or password is incorrect!", incorrectLoginCredMessage.getText(), "error message Doesn't match");
    }

    public void verifyTheLoginButtonIsVisible(){
        System.out.println("verifying the login button is visible");
        Assert.assertEquals("Signup / Login", signupLoginLink.getText(), "Login Button is not visible");
    }





}


