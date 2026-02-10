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

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static stepDefinations.BaseClass.logger;

public class AddToCart {
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

    @FindBy(xpath = "//a[@class='cart_quantity_delete']")
    WebElement deleteProductBtn;

    // Locator for the empty cart message or the table
    @FindBy(xpath = "//span[@id='empty_cart']//b[text()='Cart is empty!']")
    WebElement emptyCartMessage;

    @FindBy(xpath = "//a[@class='cart_quantity_delete']")
    List<WebElement> allDeleteButtons;

     int initialCount;

    @FindBy(xpath = "//td[@class='cart_description']//a")
    WebElement productNameInCart;

    @FindBy(xpath = "//td[@class='cart_quantity']/button")
    WebElement cartQuantityDisplay;




    public void clickAddToCart() {
        addToCartBtn.click();
    }


    public void clickViewCart() {
        // Explicit wait to ensure the modal is fully visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink));

        // Click the link
        viewCartLink.click();



    }
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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", btnProceedToCheckout);
    }

    public void clickRegisterLogin() {
        // JS Click bypasses the "Element Click Intercepted" errors from ads
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", btnRegisterLogin);
    }


    public void clickCartButton() {
        // JS Click handles situations where a Google Ad might be overlaying the header
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", btnCart);
    }

    public void removeProductFromCart() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(deleteProductBtn));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", deleteProductBtn);

            logger.info("Clicked on remove button for product.");
        } catch (Exception e) {
            logger.error("Could not remove product: " + e.getMessage());
            throw e;
        }
    }

    public boolean isCartEmpty() {
        // Wait a moment for the item to disappear from the DOM
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.invisibilityOf(deleteProductBtn));
    }

    public boolean isCartEmptyMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Wait for the message to appear after deletion
            return wait.until(ExpectedConditions.visibilityOf(emptyCartMessage)).isDisplayed();
        } catch (Exception e) {
            logger.error("Empty cart message did not appear.");
            return false;
        }
    }

    public void storeInitialCount() {
        initialCount = allDeleteButtons.size();
        logger.info("Initial items in cart: " + initialCount);
    }

    public void removeFirstProduct() {
        if (!allDeleteButtons.isEmpty()) {
            // Use JS Click to ensure the click happens regardless of overlays
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allDeleteButtons.get(0));
        }
    }

    public boolean isItemCountDecreased() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait until the number of delete buttons is less than what we started with
        return wait.until(d -> allDeleteButtons.size() < initialCount);
    }

    public String getQuantityForProduct(String expectedProductName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // This XPath finds the row containing your product name, then finds the quantity button in that row
        String quantityXpath = "//a[text()='" + expectedProductName + "']/ancestor::tr//td[@class='cart_quantity']/button";

        try {
            WebElement qtyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(quantityXpath)));
            String actualQty = qtyElement.getText();
            logger.info("Found product '" + expectedProductName + "' with quantity: " + actualQty);
            return actualQty;
        } catch (Exception e) {
            logger.error("Product '" + expectedProductName + "' not found in cart.");
            return "0";
        }
    }

    public String getCartQuantity() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Ensuring the element is visible after the page/AJAX load
            wait.until(ExpectedConditions.visibilityOf(cartQuantityDisplay));
            return cartQuantityDisplay.getText();
        } catch (Exception e) {
            logger.error("Quantity display not found in cart: " + e.getMessage());
            return "0";
        }
    }





}
