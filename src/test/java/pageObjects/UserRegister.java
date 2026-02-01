package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserRegister {
    public WebDriver ldriver;

    public UserRegister(WebDriver rdriver){
        ldriver=rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver,this);

    }

    @FindBy(className = "ico-register")
    @CacheLookup
    WebElement registerLink;

    @FindBy(id= "gender-male")
    @CacheLookup
    WebElement genderBullet;

    @FindBy(id= "FirstName")
    @CacheLookup
    WebElement firstNametxt;

    @FindBy(id= "LastName")
    @CacheLookup
    WebElement lastNametxt;

    @FindBy(id= "Email")
    @CacheLookup
    WebElement emailtxt;

    @FindBy(id= "Company")
    @CacheLookup
    WebElement companyNametxt;

    @FindBy(name= "Password")
    @CacheLookup
    WebElement passwordtxt;


    @FindBy(name= "ConfirmPassword")
    @CacheLookup
    WebElement confirmPasswordtxt;



    @FindBy(name= "register-button")
    @CacheLookup
    WebElement regisaterbutton;



    @FindBy(css= ".register-continue-button")
    @CacheLookup
    WebElement continueButton;

    //action methods

    public void clickRegisterlink(){
        registerLink.click();
    }

    public void setGenderBullet(){
        genderBullet.click();
    }

    public void setFirstNametxt(String Fname){
        firstNametxt.sendKeys(Fname);
    }

    public void setLastNametxt(String Lname){
        lastNametxt.sendKeys(Lname);
    }

    public void setEmailtxt(String Email){
       emailtxt.sendKeys(Email);
    }
    public void setCompanyNametxt(String Company){
        companyNametxt.sendKeys(Company);
    }
    public void setPasswordtxt(String Passsword){
        passwordtxt.sendKeys(Passsword);
    }
    public void setConfirmPasswordtxt(String CPasswpord){
        lastNametxt.sendKeys(CPasswpord);
    }
    public void clickRegisaterbutton()
    {
        regisaterbutton.click();
    }
    public void clickContinueButton(){
        continueButton.click();
    }


}
