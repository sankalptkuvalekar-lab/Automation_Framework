package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinations.BaseClass;

import java.time.Duration;
import java.util.List;

public class BrandPage extends BaseClass {

    public WebDriver driver;

    public BrandPage(WebDriver rdriver) {
        driver = rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver, this);




    }

    // Locator for the main heading on the brand products page
    @FindBy(xpath = "//h2[@class='title text-center']")
     WebElement brandPageHeader;

    // Locator for the product grid container to ensure products are displayed
    @FindBy(className = "features_items")
     WebElement productGrid;

    @FindBy(xpath = "//div[@class='brands-name']//a")
     List<WebElement> allBrandLinks;

    @FindBy(xpath = "//h2[@class='title text-center']")
    WebElement brandHeader;

    // Locator for the individual product items in the grid
    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4']")
    List<WebElement> brandProducts;

    public boolean isBrandPageHeaderVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(brandPageHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getBrandHeaderText() {
        return brandPageHeader.getText();
    }

    public String getCurrentBrandUrl() {
        return driver.getCurrentUrl();
    }

    /*public void clickAnyOtherBrand() {
        String currentUrl = driver.getCurrentUrl();

        // Find a brand link that isn't the one we are currently viewing
        for (WebElement brand : allBrandLinks) {
            String brandHref = brand.getAttribute("href");

            if (!currentUrl.contains(brandHref)) {
                // Scroll into view
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", brand);

                // Click using JS to bypass sidebar ads
                js.executeScript("arguments[0].click();", brand);
                break; // Exit after clicking the first "other" brand found
            }
        }
    }*/

    public void clickAnyOtherBrand() {
        String currentUrl = driver.getCurrentUrl();

        for (WebElement brand : allBrandLinks) {
            String brandHref = brand.getAttribute("href");

            // Skip the currently selected brand
            if (!currentUrl.contains(brandHref)) {
                safeClick(brand);
                break;
            }
        }
    }

    public String getBrandHeaderText1() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for the header to be visible (handles page transition)
        return wait.until(ExpectedConditions.visibilityOf(brandHeader)).getText();
    }

    public boolean areProductsDisplayed() {
        // Return true if at least one product is found in the list
        return brandProducts.size() > 0;
    }



}
