package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static stepDefinations.BaseClass.logger;


public class HomePage {
    public WebDriver driver;

    public HomePage(WebDriver rdriver) {
        driver = rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver, this);


    }
    @FindBy(id = "footer")
    @CacheLookup
    WebElement footer;

    @FindBy(xpath = "//div[@class='single-widget']/h2")
     WebElement subscriptionHeader;

    @FindBy(id = "susbscribe_email")
     WebElement subscriptionEmailInput;

    @FindBy(id = "subscribe")
     WebElement subscriptionSubmitBtn;

    @FindBy(id = "success-subscribe")
     WebElement subscriptionSuccessMessage;

    @FindBy(xpath = "//h2[contains(text(),'Full-Fledged practice website')]")
     WebElement headMainText;

    @FindBy(id = "scrollUp")
    WebElement btnScrollUpArrow;

    @FindBy(xpath = "//h2[text()='recommended items']")
    WebElement headRecommendedItems;

    @FindBy(xpath = "//div[@id='recommended-item-carousel']//a[@data-product-id='1' and contains(@class,'add-to-cart')]")
     WebElement btnAddRecommendedProduct;


//category verifying'

    @FindBy(xpath = "//h2[text()='Category']")
     WebElement leftSidebarCategoryHeading;

    // Locator for the category items (optional, to be more specific)
    @FindBy(xpath = "//div[@class='panel-group category-products']")
     WebElement categoryPanelGroup;

    @FindBy(xpath = "//a[@href='#Women']")
    WebElement lnkWomenCategory;

    @FindBy(xpath = "//div[@id='Women']//a[contains(text(),'Dress')]")
     WebElement lnkWomenDress;

    // Locator for the 'Men' category link
    @FindBy(xpath = "//a[@href='#Men']")
     WebElement lnkMenCategory;

    // Locator for the 'Tshirts' sub-category under Men
    @FindBy(xpath = "//div[@id='Men']//a[contains(text(),'Tshirts')]")
     WebElement lnkMenTshirts;

    @FindBy(xpath = "//a[@href='/products']")
     WebElement btnProducts;

    //subscription

    @FindBy(id = "susbscribe_email")
    WebElement subscribeEmailInput;


    @FindBy(id = "subscribe")
    WebElement subscribeBtn;

    @FindBy(id = "success-subscribe")
    WebElement subscriptionSuccessMsg;





    public void scrollToFooter() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // 'true' aligns the top of the element to the top of the viewport
        js.executeScript("arguments[0].scrollIntoView(true);", footer);

        // Optional: Verify it is displayed after scrolling
        Assert.assertTrue(footer.isDisplayed(), "Footer is not visible after scrolling!");
    }

    /**
     * Verifies the Subscription header visibility and returns its text
     */
    public String getSubscriptionSectionText() {
        // Wait for the element to be visible to ensure the scroll finished
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(subscriptionHeader));

        return subscriptionHeader.getText();
    }
    public boolean isSubscriptionHeaderVisible() {
        try {
            // Returns true if the element is present AND visible
            return subscriptionHeader.isDisplayed();
        } catch (NoSuchElementException e) {
            // Returns false if the element is not found in the DOM at all
            return false;
        }
    }

    public void subscribeWithEmail(String email) {
        // 1. Ensure the field is visible (useful after scrolling)
        Assert.assertTrue(subscriptionEmailInput.isDisplayed(), "Subscription input not visible!");

        // 2. Type the email
        subscriptionEmailInput.sendKeys(email);

        // 3. Click the arrow button
        subscriptionSubmitBtn.click();
    }

    public String getSubscriptionSuccessText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Wait for the message to be visible after clicking the arrow
        wait.until(ExpectedConditions.visibilityOf(subscriptionSuccessMessage));

        return subscriptionSuccessMessage.getText();
    }

    public boolean isSuccessMessageVisible() {
        return subscriptionSuccessMessage.isDisplayed();
    }

    public void scrollToTop() {
        // Use the shared driver from the manager
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll to the absolute top (0, 0) of the document
        js.executeScript("window.scrollTo(0, 0)");
    }

    public boolean isMainHeadingVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Ensure the element is visible in the current viewport
            return wait.until(ExpectedConditions.visibilityOf(headMainText)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickScrollUpArrow() {
        // Wait for the arrow to be clickable (it usually fades in)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnScrollUpArrow));

        // JS Click is used as this is an overlay element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", btnScrollUpArrow);
    }

    public boolean isRecommendedItemsVisible() {
        try {
            // Use JavaScript to scroll it into view first, just in case
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", headRecommendedItems);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(headRecommendedItems)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void clickAddRecommendedProduct() {
        // Scroll to it first so the screenshot captures it if it fails
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", btnAddRecommendedProduct);

        // Wait and Click
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnAddRecommendedProduct));

        // Using JS click to avoid 'ElementClickIntercepted' from the carousel overlay
        js.executeScript("arguments[0].click();", btnAddRecommendedProduct);
    }

    public boolean isCategorySidebarVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Verify the heading is displayed
            return wait.until(ExpectedConditions.visibilityOf(leftSidebarCategoryHeading)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickWomenCategory() {
        // Ensure the element is visible and in view
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", lnkWomenCategory);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(lnkWomenCategory));

        // Use JS click to bypass potential "skyscraper" ads on the sidebar
        js.executeScript("arguments[0].click();", lnkWomenCategory);
    }

    public void clickWomenDressSubCategory() {
        // Wait for the 'Women' section to expand and the link to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(lnkWomenDress));

        // Use JS click to bypass potential sidebar ads and ensure navigation
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", lnkWomenDress);
    }

    public void clickMenCategory() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(lnkMenCategory));

        // Use JS Click to avoid sidebar ad interference
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", lnkMenCategory);
    }

    public void clickMenTshirtsSubCategory() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for the 'Men' section to expand so the sub-link is visible
        wait.until(ExpectedConditions.visibilityOf(lnkMenTshirts));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", lnkMenTshirts);
    }


    public void clickProducts() {
        // We use a wait here to make sure the page is ready before clicking
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnProducts));

        btnProducts.click();

        // AD HANDLING: If the URL changes to an ad, refresh the page
        if (driver.getCurrentUrl().contains("google_vignette")) {
            driver.navigate().refresh();
        }
    }

    //subscription
    public void enterSubscriptionEmail(String email) {
        // Optional: Use JS to scroll to the footer where the input resides
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscribeEmailInput);

        subscribeEmailInput.clear();
        subscribeEmailInput.sendKeys(email);
        logger.info("Entered subscription email: " + email);
    }

    public void clickSubscribeButton() {
        try {
            // Wait for visibility first
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(subscribeBtn));

            // Using JS Click for stability against ads/intercepted clicks
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", subscribeBtn);

            logger.info("Clicked on the subscribe button.");
        } catch (Exception e) {
            logger.error("Failed to click subscribe button: " + e.getMessage());
            throw e;
        }
    }

    public String getSubscriptionSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Wait for the message to be visible before grabbing text
        wait.until(ExpectedConditions.visibilityOf(subscriptionSuccessMsg));

        String message = subscriptionSuccessMsg.getText();
        logger.info("Subscription message displayed: " + message);
        return message;
    }




}



