package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinations.BaseClass;

import java.time.Duration;

public class CategoryPage extends BaseClass {
    public WebDriver driver;

    public CategoryPage(WebDriver rdriver) {
        driver = rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver, this);


    }
    @FindBy(xpath = "//h2[@class='title text-center']")
    WebElement categoryTitle;





    /*public String getCategoryTitleText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Ensure the heading is visible before getting text
            return wait.until(ExpectedConditions.visibilityOf(categoryTitle)).getText();
        } catch (Exception e) {
            return "Title not found";
        }
    }*/

    public String getCategoryTitleText() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {

            // ✅ Wait until URL contains category
            wait.until(ExpectedConditions.urlContains("/category_products/"));

            // ✅ Wait until correct header is visible
            By categoryHeaderLocator = By.xpath("//h2[@class='title text-center']");

            WebElement header = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(categoryHeaderLocator)
            );

            return header.getText().trim();

        } catch (Exception e) {
            logger.info("Category title not found: " + e.getMessage());
            return "Title not found";
        }
    }

    public String getCategoryPageUrl() {
        return driver.getCurrentUrl();
    }



}
