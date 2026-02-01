/*package pageObjects;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserLoginPage {

    public WebDriver ldriver;

    public UserLoginPage(WebDriver rdriver){
        ldriver=rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver,this);

    }
    @FindBy(className = "ico-login")
    @CacheLookup
    WebElement loginLink;

    @FindBy(id = "Email")
    @CacheLookup
    WebElement emailTxt;


    @FindBy(id = "Password")
    @CacheLookup
    WebElement passwordTxt;


  @FindBy(className = "login-button")
    @CacheLookup
    WebElement loginButtton;


  public void clickLoginLink(){
      loginLink.click();
  }
  public void setEmailLogin(String email){
      emailTxt.sendKeys(email);
  }
public void setPwdLogin(String password){
      passwordTxt.sendKeys(password);
}
public void clickLoginButton(){
      loginButtton.click();

}

}

 */

//added by me
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserLoginPage {

    public WebDriver driver;

    // Constructor: accept driver from BaseClass (singleton)
    public UserLoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "ico-login")
    @CacheLookup
    WebElement loginLink;

    @FindBy(id = "Email")
    @CacheLookup
    WebElement emailTxt;

    @FindBy(id = "Password")
    @CacheLookup
    WebElement passwordTxt;

    @FindBy(className = "login-button")
    @CacheLookup
    WebElement loginButton;

    public void clickLoginLink(){
        loginLink.click();
    }

    public void setEmailLogin(String email){
        emailTxt.sendKeys(email);
    }

    public void setPwdLogin(String password){
        passwordTxt.sendKeys(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }
}


