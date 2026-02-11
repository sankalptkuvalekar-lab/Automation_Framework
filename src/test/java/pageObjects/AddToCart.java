package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinations.BaseClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AddToCart extends BaseClass {
    public WebDriver driver;

    public AddToCart(WebDriver rdriver) {
        driver = rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver, this);


    }

    @FindBy(xpath = "//button[contains(., 'Add to cart')]")
    @CacheLookup
    WebElement addToCartBtn;

    @FindBy(linkText = "View Cart")
    @CacheLookup
    WebElement viewCartLink;

    @FindBy(xpath = "//td[@class='cart_price']/p")
    private WebElement firstProductPrice;

    @FindBy(xpath = "//td[@class='cart_quantity']/button")
    private WebElement firstProductQuantity;

    @FindBy(xpath = "//td[@class='cart_total']/p")
    private WebElement firstProductTotal;

    //cart button on ho,e pafe
    @FindBy(xpath = "//ul[@class='nav navbar-nav']//a[@href='/view_cart']")
    @CacheLookup
    WebElement cartButton;

    // Locates all product rows in the cart
    @FindBy(xpath = "//table[@id='cart_info_table']/tbody/tr")
    List<WebElement> cartRows;

    // Optional: Locates specific product names to be more precise
    @FindBy(xpath = "//table[@id='cart_info_table']/tbody/tr/td[@class='cart_description']//a")
    List<WebElement> productNames;

    //checkout button
    @FindBy(css = ".btn.btn-default.check_out")
   WebElement btnProceedToCheckout;

    @FindBy(xpath = "//u[normalize-space()='Register / Login']")
    WebElement btnRegisterLogin;

    @FindBy(xpath = "//a[contains(text(),'Cart')]")
     WebElement btnCart;




    public void clickAddToCart() {
        addToCartBtn.click();
    }
    /*public void clickAddToCart() {

        if (isMobileExecution()) {
            removeGoogleAdsIfPresent();
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                addToCartBtn
        );

        js.executeScript("arguments[0].click();", addToCartBtn);

        logger.info("Clicked Add To Cart button");
    }*/



    public void clickViewCart() {
        // Explicit wait to ensure the modal is fully visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink));

        // Click the link
        viewCartLink.click();

    }

    /*public void clickViewCartFromPopup() {

        if (isMobileExecution()) {
            removeGoogleAdsIfPresent();
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                viewCartLink
        );

        js.executeScript("arguments[0].click();", viewCartLink);

        logger.info("Clicked View Cart from popup");
    }*/

    public String getFirstProductPrice() {
        return firstProductPrice.getText();
    }

    public String getFirstProductQuantity() {
        return firstProductQuantity.getText();
    }

    public String getFirstProductTotal() {
        return firstProductTotal.getText();
    }

    /**
     * Clicks the Cart button in the header
     */
    public void clickCart() {
        // Scroll to top just in case the footer is covering the header
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        cartButton.click();
    }

    public int getCartProductCount() {
        // It's good practice to wait a second for the table to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(cartRows));

        return cartRows.size();
    }
    public List<String> getAllProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement element : productNames) {
            names.add(element.getText());
        }
        return names;
    }

    //checkput
    public void clickProceedToCheckout() {
        // JavaScript click to bypass any potential ad overlays/iframes
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].click();", btnProceedToCheckout);
        safeClick(btnProceedToCheckout);
    }

    public void clickRegisterLogin() {
        // JS Click bypasses the "Element Click Intercepted" errors from ads
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].click();", btnRegisterLogin);
        safeClick(btnRegisterLogin);
    }


    public void clickCartButton() {
        // JS Click handles situations where a Google Ad might be overlaying the header
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].click();", btnCart);
        safeClick(btnCart);

    }





}
