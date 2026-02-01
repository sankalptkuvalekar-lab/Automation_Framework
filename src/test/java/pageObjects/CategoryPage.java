package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryPage {
    public WebDriver driver;

    public CategoryPage(WebDriver rdriver) {
        driver = rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver, this);


    }
    @FindBy(xpath = "//h2[@class='title text-center']")
    WebElement categoryTitle;





    public String getCategoryTitleText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Ensure the heading is visible before getting text
            return wait.until(ExpectedConditions.visibilityOf(categoryTitle)).getText();
        } catch (Exception e) {
            return "Title not found";
        }
    }
    public String getCategoryPageUrl() {
        return driver.getCurrentUrl();
    }



}
