package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public WebDriver ldriver;

    public LoginPage(WebDriver rdriver)  {

        ldriver=rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver,this);
    }

    @FindBy(id="Email")
    @CacheLookup
    WebElement txtEmail;

    @FindBy(id="Password")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(xpath = "//button[text()='Log in']")
    @CacheLookup
    WebElement btnLogin;

    @FindBy(linkText = "Logout")
    @CacheLookup
    WebElement lnkLogout;

    //above each elememnt we will write the action methods

    public void setUserName(String uname){
        txtEmail.clear();
        txtEmail.sendKeys(uname);
    }

    public void setPassword(String pwd){
        txtPassword.clear();
        txtPassword.sendKeys(pwd);
    }

    public void clickLogin() throws InterruptedException {
        btnLogin.click();
     /* WebElement checkbox=  ldriver.findElement(By.cssSelector("input[type='checkbox']"));
        Thread.sleep(3000);
       try {

           if (checkbox.isDisplayed()) {
               checkbox.click();
           }
       }
      catch(NoSuchElementException e){
           System.out.println("element not found");

           }*/
      }




    public void clickLogout(){
        lnkLogout.click();
    }



}
