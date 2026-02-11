package pageObjects;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import stepDefinations.BaseClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class ProductListAndPage extends BaseClass {

    public WebDriver driver;

    public ProductListAndPage(WebDriver rdriver) {
        driver = rdriver; // Update the local instance
        PageFactory.initElements(rdriver, this);
    }


    @FindBy(xpath = "//a[contains(text(), 'Products')]")
    @CacheLookup
    WebElement productsLink;

    @FindBy(css = "h2.title.text-center")
    @CacheLookup
    WebElement allProductsHeader;

    /*@FindBy(css = "a[href='/product_details/1']")
    @CacheLookup
    WebElement firstViewProductBtn;*/

    private By firstViewProductBtn =
            By.cssSelector("a[href='/product_details/1']");

    @FindBy(xpath = "//div[@class='product-information']/h2")
    WebElement productName;

    @FindBy(xpath = "//p[contains(text(), 'Category:')]")
    WebElement productCategory;

    @FindBy(xpath = "//span[contains(text(), 'Rs.')]")
    WebElement productPrice;

    @FindBy(xpath = "//p[b[contains(text(), 'Availability:')]]")
    WebElement productAvailability;

    @FindBy(xpath = "//p[b[contains(text(), 'Condition:')]]")
    WebElement productCondition;

    @FindBy(xpath = "//p[b[contains(text(), 'Brand:')]]")
    WebElement productBrand;

    //searchproduct elements
    @FindBy(id = "search_product")
    @CacheLookup
    WebElement searchInput;

    @FindBy(id = "submit_search")
    @CacheLookup
    WebElement searchButton;

    @FindBy(xpath = "//h2[@class='title text-center' and text()='Searched Products']")
    @CacheLookup
    WebElement searchedProductsHeader;

    @FindBy(css = ".single-products")
     List<WebElement> searchedProductList;


    //adding procuts tomcart
    // The container for the first product
    @FindBy(xpath = "(//div[@class='single-products'])[1]")
    WebElement firstProductContainer;

    // The 'Add to cart' button inside the overlay of the first product
    /*@FindBy(xpath = "(//div[@class='product-overlay']//a[contains(text(),'Add to cart')])[1]")
   WebElement firstProductOverlayAddToCartBtn;*/
    private By firstProductOverlayAddToCartBtn =
            By.xpath("(//div[@class='product-overlay']//a[contains(text(),'Add to cart')])[1]");

    @FindBy(xpath = "//button[text()='Continue Shopping']")
     WebElement continueShoppingBtn;


    // The container for the second product
    @FindBy(xpath = "(//div[@class='single-products'])[2]")
     WebElement secondProductContainer;

    // The 'Add to cart' button inside the second product's hover overlay
    /*@FindBy(xpath = "(//div[@class='product-overlay']//a[@data-product-id='2'])[1]")
     WebElement secondProductOverlayAddToCartBtn;*/

    @FindBy(xpath = "(//a[@data-product-id='2'])[1]")
    WebElement secondProductAddToCartBtn;

    @FindBy(xpath = "//div[@class='modal-content']//u[text()='View Cart']")
     WebElement viewCartBtn;

    @FindBy(xpath = "//div[@class='brands_products']//h2")
     WebElement brandsHeading;

    @FindBy(className = "brands-name")
     WebElement brandsList;

    @FindBy(id = "search_product")
   WebElement inputSearch;

    @FindBy(id = "submit_search")
    WebElement btnSearch;
    @FindBy(xpath = "//h2[@class='title text-center' and text()='All Products']")
     WebElement allProductsTitle;




    /*public void clickProducts() {
        productsLink.click();
        clickWithJS(productsLink);

        // Check if ad appeared
        if (ldriver.getCurrentUrl().contains("#google_vignette")) {
            // Instead of refreshing, try to click the body to dismiss
            Actions action = new Actions(ldriver);
            action.moveByOffset(0, 0).click().build().perform();

            // Alternative: Use JS to remove the overlay if it gets stuck
            ((JavascriptExecutor) ldriver).executeScript("const elements = document.getElementsByClassName('adsbygoogle'); while (elements.length > 0) elements[0].remove();");
        }

    }*/

    public void clickProducts() {

        logger.info("Clicking on Products button");

        safeClick(productsLink);

    }

    public String getAllProductsHeaderText() {
        // Returns the text "All Products" for the assertion
        return allProductsHeader.getText();
    }
    /*public void clickFirstViewProduct() throws InterruptedException {
        // Scroll into view if necessary, as these buttons are usually lower on the page
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstViewProductBtn);

        // Verify visibility before interaction
        Assert.assertTrue(firstViewProductBtn.isDisplayed(), "First View Product button is not visible!");

        // Perform the click
        firstViewProductBtn.click();
        if (driver.getCurrentUrl().contains("#google_vignette")) {
            // Instead of refreshing, try to click the body to dismiss
            Actions action = new Actions(driver);
            action.moveByOffset(0, 0).click().build().perform();

            // Alternative: Use JS to remove the overlay if it gets stuck
            ((JavascriptExecutor) driver).executeScript("const elements = document.getElementsByClassName('adsbygoogle'); while (elements.length > 0) elements[0].remove();");

        }

    }*/

    /*public void clickFirstViewProduct() {

        WebElement viewProduct =
                driver.findElement(By.cssSelector("a[href='/product_details/1']"));

        safeClick(viewProduct);

        logger.info("Clicked on View Product of first product");
    }*/

    public void clickFirstViewProduct() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement viewProduct =
                wait.until(ExpectedConditions.elementToBeClickable(firstViewProductBtn));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", viewProduct);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", viewProduct);

        logger.info("Clicked on View Product of first product");
    }


    public void verifyProductDetailsAreVisible() {
        Assert.assertTrue(productName.isDisplayed(), "Product Name is not visible");
        Assert.assertTrue(productCategory.isDisplayed(), "Category is not visible");
        Assert.assertTrue(productPrice.isDisplayed(), "Price is not visible");
        Assert.assertTrue(productAvailability.isDisplayed(), "Availability is not visible");
        Assert.assertTrue(productCondition.isDisplayed(), "Condition is not visible");
        Assert.assertTrue(productBrand.isDisplayed(), "Brand is not visible");
    }

    //search product actiuons
    /*public void searchProduct(String productName) throws InterruptedException {

        Thread.sleep(3000);


        // 1. Enter the product name into the search bar
        searchInput.sendKeys(productName);

        // 2. Ensure the search button is visible and click it
        Assert.assertTrue(searchButton.isDisplayed(), "Search button is not visible!");
        searchButton.click();



    }*/

    public String getSearchedProductsHeaderText() {
        // Wait briefly for visibility to ensure the search results have loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(searchedProductsHeader));

        return searchedProductsHeader.getText();
    }
    public String getSearchedHeaderText() {
        return searchedProductsHeader.getText();
    }
    public boolean isSearchedHeaderVisible() {
        return searchedProductsHeader.isDisplayed();
    }

    public boolean areProductsVisibleAfterSearch() {
        // Basic check to ensure the list is not empty
        return !searchedProductList.isEmpty();
    }

    public boolean verifyAllProductNamesContain(String keyword) {
        for (WebElement product : searchedProductList) {
            if (!product.getText().toLowerCase().contains(keyword.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    //addoimg profuvyt tp cart

    /*public void hoverAndClickFirstAddToCart() {
        if (this.driver == null) {
            // This helps you debug which variable is actually empty
            throw new RuntimeException("ldriver is null inside ProductListAndPage!");
        }

        // Find elements...
        WebElement firstProduct = driver.findElement(By.xpath("(//div[@class='single-products'])[1]"));

        // Scroll and Hover
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", firstProduct);

        // Use the BaseClass method you already have!
        clickWithJS(firstProductOverlayAddToCartBtn);
    }*/

    /*public void clickFirstAddToCart() {

        if (driver == null) {
            throw new RuntimeException("Driver is null inside ProductListAndPage!");
        }

        // Locate the first product Add to Cart button directly
        WebElement addToCartBtn = driver.findElement(
                By.xpath("(//a[contains(text(),'Add to cart')])[1]")
        );

        // Use centralized safe click
        safeClick(addToCartBtn);

        logger.info("Clicked Add to Cart for first product");
    }*/
    public void clickFirstAddToCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // ALWAYS locate fresh element (avoids stale)
        WebElement addToCartBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(firstProductOverlayAddToCartBtn)
        );

        // Scroll to center (overlay loads correctly)
        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                addToCartBtn
        );

        // Small stabilization pause
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // JS click (overlay + ad safe)
        js.executeScript("arguments[0].click();", addToCartBtn);

        logger.info("Clicked Add to Cart for first product");
    }

    public void clickContinueShopping() {
        // 1. Wait up to 5 seconds for the button to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingBtn));

        // 2. Click the button
        //continueShoppingBtn.click();
        safeClick(continueShoppingBtn);
    }
    /*public void hoverAndClickSecondAddToCart() {
        // 1. Scroll to the second product
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", secondProductContainer);

        // 2. Perform Hover
        Actions actions = new Actions(driver);
        actions.moveToElement(secondProductContainer).perform();

        // 3. Wait and Click (Using JS Click to avoid Ad-interception)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(secondProductOverlayAddToCartBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", secondProductOverlayAddToCartBtn);
    }*/

    /*public void clickSecondAddToCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Just wait and click — no hover, no JS, no scroll
        WebElement addToCartBtn =
                wait.until(ExpectedConditions.elementToBeClickable(secondProductOverlayAddToCartBtn));

        safeClick(addToCartBtn);

        logger.info("Clicked Add to Cart for second product");
    }*/

    public void clickSecondAddToCart() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll product into view
        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                secondProductAddToCartBtn
        );

        // Small wait for mobile layout
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // JS click bypasses overlay & ads
        safeClick(secondProductAddToCartBtn);

        logger.info("Clicked Add to Cart for second product");
    }

    public void clickViewCart() {
        // 1. Wait for the modal/link to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(viewCartBtn));

        // 2. Click using JS to ensure navigation and bypass any ad-layers
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].click();", viewCartBtn);
        safeClick(viewCartBtn);
    }

    public boolean isBrandsSectionVisible() throws InterruptedException {
        Thread.sleep(5000);
        try {
            // Scroll down slightly as Brands are usually below Categories
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", brandsHeading);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Ensure the 'BRANDS' text is actually visible
            return wait.until(ExpectedConditions.visibilityOf(brandsHeading)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /*public void clickBrandByName(String brandName) {
        // 1. Find the brand link
        String xpath = "//div[@class='brands-name']//a[contains(text(),'" + brandName + "')]";
        WebElement brandLink = driver.findElement(By.xpath(xpath));

        // 2. Use JS Click (it's more effective against ad overlays)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", brandLink);

        // 3. THE FIX: Check if we are stuck on the Ad URL
        if (driver.getCurrentUrl().contains("#google_vignette")) {
            System.out.println("AD DETECTED: Refreshing to bypass...");
            driver.navigate().refresh();

            // Sometimes after refresh, we need to click it again if the refresh
            // sent us back to the previous page
            if (!driver.getCurrentUrl().contains("brand_products")) {
                js.executeScript("arguments[0].click();", brandLink);
            }
        }
    }*/

    /*public void clickBrandByName(String brandName) {

        String xpath = "//div[@class='brands-name']//a[contains(text(),'" + brandName + "')]";
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 1. First attempt
        WebElement brandLink = driver.findElement(By.xpath(xpath));
        js.executeScript("arguments[0].scrollIntoView(true);", brandLink);
        js.executeScript("arguments[0].click();", brandLink);

        // 2. Detect mobile ad hijack
        if (driver.getCurrentUrl().contains("google_vignette")) {
            logger.info("Mobile Ad detected. Refreshing page...");

            driver.navigate().refresh();

            // 3. Re-locate element AFTER refresh (VERY IMPORTANT)
            WebElement brandLinkAfterRefresh = driver.findElement(By.xpath(xpath));
            js.executeScript("arguments[0].scrollIntoView(true);", brandLinkAfterRefresh);
            js.executeScript("arguments[0].click();", brandLinkAfterRefresh);
        }
    }*/

    public void clickBrandByName(String brandName) {

        String xpath = "//div[@class='brands-name']//a[contains(normalize-space(),'"
                + brandName + "')]";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement brandLink =
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        safeClick(brandLink);

        logger.info("Clicked on Brand: " + brandName);
    }

    /*public void searchProduct(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(inputSearch));
            searchInput.clear();
            searchInput.sendKeys(productName);
            btnSearch.click();
        } catch (Exception e) {
            // FALLBACK: Use JS to interact if an invisible ad layer is blocking the element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='Polo';", inputSearch);
            js.executeScript("arguments[0].click();", btnSearch);
        }
    }*/

    public void searchProduct(String productName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement searchInput =
                wait.until(ExpectedConditions.visibilityOf(inputSearch));

        searchInput.clear();
        searchInput.sendKeys(productName);

        safeClick(btnSearch);

        logger.info("Searched for product: " + productName);
    }

    public boolean isAllProductsPageVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(allProductsTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

