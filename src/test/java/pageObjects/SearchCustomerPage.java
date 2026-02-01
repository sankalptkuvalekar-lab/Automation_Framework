package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.util.List;

public class SearchCustomerPage {

    public WebDriver ldriver;
    WaitHelper waithelper;

    public SearchCustomerPage(WebDriver rdriver){
        ldriver=rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver,this);
       waithelper= new WaitHelper(ldriver);
    }

    //another approach in pom
    @FindBy(how = How.ID, using = "SearchEmail")
    @CacheLookup
    WebElement txtEmail;

    @FindBy(how = How.ID, using = "SearchFirstName")
    @CacheLookup
    WebElement txtFirstName;

    @FindBy(how = How.ID, using = "SearchLastName")
    @CacheLookup
    WebElement txtLastName;

    @FindBy(how = How.ID, using = "SearchMonthOfBirth")
    @CacheLookup
    WebElement drpdobMonth;

    @FindBy(how = How.ID, using = "SearchDayOfBirth")
    @CacheLookup
    WebElement drpdobDay;

    @FindBy(how = How.ID, using = "SearchCompany")
    @CacheLookup
    WebElement txtCompany;
    @FindBy(how = How.XPATH, using = "//div[@class='k-multiselect-wrap k-floatwrap']")
    @CacheLookup
    WebElement txtcustomerRoles;

    @FindBy(how = How.XPATH, using = "//li[contains(text(),'Administrators')]")
    @CacheLookup
    WebElement lstitemAdministrators;

    @FindBy(how = How.XPATH, using = "//li[contains(text(),'Registered')]")
    @CacheLookup
    WebElement lstitemRegistered;

    @FindBy(how = How.XPATH, using = "//li[contains(text(),'Guests')]")
    @CacheLookup
    WebElement lstitemGuests;

    @FindBy(how = How.XPATH, using = "//li[contains(text(),'Vendors')]")
    @CacheLookup
    WebElement lstitemVendors;

    @FindBy(how = How.ID, using = "search-customers")
    @CacheLookup
    WebElement btnSearch;

    @FindBy(how = How.XPATH, using = "//table[@role='grid']")
    @CacheLookup
    WebElement tblSearchResults;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
    WebElement table;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
    List<WebElement> tableRows;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
    List<WebElement> tableColumns;

    //action methods
    public void setEmail(String email){

        waithelper.WaitForElement(txtEmail,30);
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void setFirstName(String fname) {
        waithelper.WaitForElement(txtFirstName, 30);
        txtFirstName.clear();
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        waithelper.WaitForElement(txtLastName, 30);
        txtLastName.clear();
        txtLastName.sendKeys(lname);
    }

    public void clickSearch(){
        btnSearch.click();
        waithelper.WaitForElement(btnSearch,30);
    }

    public int getNoOfRows(){
        return (tableRows.size());
    }

    public int getNoOfColumns(){
        return (tableColumns.size());
    }

    public boolean searchCustomerByEmail(String email){
        boolean flag= false;

        for(int i=1; i<=getNoOfRows();i++){
            String emailId=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText() ;
            System.out.println(emailId);
            if(emailId.equals("victoria_victotia@nopCommerce.com")){
                flag=true;
            }
        }
        return flag;
    }

    public boolean searchCustomerByName(String Name)
    {
        boolean flag=false;

        for(int i=1;i<=getNoOfRows();i++)
        {
            String name=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText();

                    String names[]=name.split(" "); //separating first name and last name

            if(names[0].equals("Victoria") && names[1].equals("Terces"))
            {
                flag=true;
            }
        }

        return flag;
    }
}
