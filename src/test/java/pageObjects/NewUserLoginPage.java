package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import stepDefinations.BaseClass;

import java.time.Duration;
import java.util.List;

/*public class NewUserLoginPage {

    public WebDriver driver;

    public NewUserLoginPage(WebDriver rdriver){
        driver=rdriver;  //constructor which initiates the browser
        PageFactory.initElements(rdriver,this);


    }

    @FindBy(linkText = "Signup / Login")
    @CacheLookup
    WebElement loginLink;

    @FindBy(name = "email")
    @CacheLookup
    WebElement emailTxt;

    @FindBy(name = "password")
    @CacheLookup
    WebElement passwordTxt;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    @CacheLookup
    WebElement loginBtn;

    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    @CacheLookup
    WebElement logoutLibnk;

   @FindBy(xpath = "//h1[contains(., 'Automation')]")
   @CacheLookup
   WebElement homePageIsDisplayed;

    private By loggedInAsUser =
            By.xpath("//a[contains(text(),'Logged in as')]");

    @FindBy(css = ".login-form h2")
    @CacheLookup
    WebElement loginToAccountIsDisplayed;

    @FindBy(css = "li a i.fa-user + b")
    @CacheLookup
    WebElement loggedInAsUser;

    /*@FindBy(css = ".login-form p[style*='color: red']")
    @CacheLookup
    WebElement incorrectLoginCredMessage;
    private By incorrectLoginCredMessage =
            By.xpath("//p[contains(text(),'email or password is incorrect')]");

    @FindBy(css = "a[href='/login']")
    @CacheLookup
    WebElement signupLoginLink;

    @FindBy(id = "newsletter")
    WebElement chkNewsletter;



    //action methods

    public void loginLinkClick()
    {
        loginLink.click();
    }

    public void emailEnter(String Email){
        emailTxt.sendKeys(Email);
    }

    public void passwordEnter(String Password){
        passwordTxt.sendKeys(Password);
    }
    public void clickLoginButton(){
        loginBtn.click();
    }

    public void clickLpogputLink() {
        logoutLibnk.click();
    }

    public void verifyHomePage(){
        System.out.println("verifying the home page");
        Assert.assertEquals("AutomationExercise", homePageIsDisplayed.getText(), "Home Page Text Doesn't match");
    }

    public void verifyloginPage(){
        System.out.println("verifying the login page");
        Assert.assertEquals("Login to your account",loginToAccountIsDisplayed .getText(), "Login To your Account Text Doesn't match");
    }

    /*public void verifyHomePageAfterLogin(){
        System.out.println("verifying the logged in as a user text");
        Assert.assertEquals("user66", loggedInAsUser.getText(), "Logged in as username Text Doesn't match");
    }

    public void verifyHomePage() {

        System.out.println("Verifying home page after login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement loggedInText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(loggedInAsUser)
        );

        Assert.assertTrue(
                loggedInText.isDisplayed(),
                "Home page is NOT visible after login"
        );
    }

    /*public void verifyHErrorMeeageofInvalidCredentials(){
        System.out.println("verifying the error message when lopgged in with the invalid credentials");
        Assert.assertEquals("Your email or password is incorrect!", incorrectLoginCredMessage.getText(), "error message Doesn't match");
    }

    public void verifyHErrorMeeageofInvalidCredentials() {

        System.out.println("Verifying error message for invalid login credentials");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(incorrectLoginCredMessage)
        );

        String actualMessage = errorMsg.getText().trim();

        Assert.assertEquals(
                actualMessage,
                "Your email or password is incorrect!",
                "Error message doesn't match"
        );
    }

    public void verifyTheLoginButtonIsVisible(){
        System.out.println("verifying the login button is visible");
        Assert.assertEquals("Signup / Login", signupLoginLink.getText(), "Login Button is not visible");
    }





}*/

public class NewUserLoginPage extends BaseClass {

    WebDriver driver;

    public NewUserLoginPage(WebDriver rdriver){
        driver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    // ======= LOCATORS (STABLE ELEMENTS) =======

    @FindBy(linkText = "Signup / Login")
    WebElement loginLink;

    @FindBy(name = "email")
    WebElement emailTxt;

    @FindBy(name = "password")
    WebElement passwordTxt;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    WebElement loginBtn;

    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    WebElement logoutLink;

    @FindBy(css = ".login-form h2")
    WebElement loginToAccountIsDisplayed;

    @FindBy(css = "a[href='/login']")
    WebElement signupLoginLink;

    // ======= DYNAMIC ELEMENTS (By + Wait) =======

    //private By loggedInAsUser =
            //By.xpath("//a[contains(text(),'Logged in as')]");

    private By loggedInAsUser =
            By.xpath("//a[.//text()[contains(.,'Logged in as')]]");

    private By incorrectLoginCredMessage =
            By.xpath("//p[contains(text(),'email or password is incorrect')]");

    // ======= ACTION METHODS =======

    /*public void loginLinkClick() {
        loginLink.click();
    }*/

    public void loginLinkClick() {

        // Handle mobile ad first (safe)
        handleAdIfPresentMobileSafe();

        // Use centralized safeClick (already built in BaseClass)
        safeClick(loginLink);

        logger.info("Clicked on Signup / Login link");

    }

    /*public void loginLinkClick() {

        logger.info("Clicking Signup / Login link");

        // Always clear ads first (mobile is aggressive)
        handleAd();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Wait until clickable
            wait.until(ExpectedConditions.elementToBeClickable(loginLink));

            // Try normal click first
            loginLink.click();

        } catch (ElementClickInterceptedException e) {

            logger.warn("Login click intercepted by ad. Falling back to JS click.");

            // Fallback: JS click bypasses iframe overlays
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", loginLink);
        }

        handleAd();
    }*/

    public void emailEnter(String email){
        emailTxt.clear();
        emailTxt.sendKeys(email);
    }

    public void passwordEnter(String password){
        passwordTxt.clear();
        passwordTxt.sendKeys(password);
    }

    public void clickLoginButton(){
        loginBtn.click();
    }

    public void clickLogoutLink() {
        logoutLink.click();
    }

    // ======= VERIFICATIONS =======

    public void verifyLoginPage(){
        Assert.assertEquals(
                loginToAccountIsDisplayed.getText().trim(),
                "Login to your account",
                "Login page text mismatch"
        );
    }

    /*public void verifyHomePageAfterLogin() {

        System.out.println("Verifying home page after login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loggedInText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(loggedInAsUser)
        );

        Assert.assertTrue(
                loggedInText.isDisplayed(),
                "'Logged in as user' text is not visible"
        );
    }*/

    /*public void verifyHomePageAfterLogin() {

        System.out.println("Verifying home page after login");

        // 1️⃣ Ensure we're in main page (not inside any iframe)
        driver.switchTo().defaultContent();

        // 2️⃣ Optional: Close any popups or ads if present
        try {
            List<WebElement> adIframes = driver.findElements(By.cssSelector("iframe[src*='ads']"));
            for (WebElement adFrame : adIframes) {
                driver.switchTo().frame(adFrame);
                WebElement closeBtn = driver.findElement(By.cssSelector("button, .close, .close-btn"));
                if (closeBtn.isDisplayed()) {
                    closeBtn.click();
                    System.out.println("Closed an ad popup");
                }
                driver.switchTo().defaultContent();
            }
        } catch (Exception e) {
            System.out.println("No ad popup detected or error closing ad: " + e.getMessage());
        }

        // 3️⃣ Wait for the "Logged in as" element to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 4️⃣ Mobile and Desktop locator handling
        By[] possibleLocators = new By[]{
                By.xpath("//a[contains(text(),'Logged in as')]"),           // desktop
                By.xpath("//div[contains(@class,'user-info')]//span"),      // mobile (adjust class if needed)
                By.xpath("//p[contains(text(),'Logged in as')]")            // fallback
        };

        WebElement loggedInText = null;
        for (By locator : possibleLocators) {
            try {
                loggedInText = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                if (loggedInText != null && loggedInText.isDisplayed()) {
                    System.out.println("Login verified using locator: " + locator);
                    break;
                }
            } catch (Exception ignored) {
                // Try next locator
            }
        }

        // 5️⃣ Assert user is logged in
        Assert.assertNotNull(loggedInText, "User login verification failed: 'Logged in as' element not found");
        Assert.assertTrue(loggedInText.isDisplayed(), "'Logged in as' text is not visible");
    }*/

    /*public void verifyHomePageAfterLogin() {

        System.out.println("Verifying home page after login");

        // Always reset frame context
        driver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Wait until Logout link is visible
            wait.until(ExpectedConditions.visibilityOf(logoutLink));

            Assert.assertTrue(
                    logoutLink.isDisplayed(),
                    "User login verification failed: Logout link is not visible"
            );

            System.out.println("User successfully logged in – Logout link is visible");

        } catch (TimeoutException e) {
            Assert.fail("User login verification failed: Logout link not found after login");
        }
    }*/

    /*public void verifyHomePageAfterLogin() {

        logger.info("Verifying home page after login");

        handleAd();

        // Safety refresh (AutomationExercise is flaky)
        driver.navigate().refresh();
        handleAd();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement loggedInText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[contains(text(),'Logged in as')]"))
        );

        Assert.assertTrue(loggedInText.isDisplayed(),
                "❌ User login verification failed: 'Logged in as' text not found after login");

        logger.info("✅ User successfully logged in");
    }*/

    public void verifyHomePageAfterLogin() {

        logger.info("Verifying home page after login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {

            // ✅ Handle vignette if it appears
            handleAd();

            // ✅ Wait until we are back on homepage (not vignette)
            wait.until(driver ->
                    !driver.getCurrentUrl().contains("google_vignette")
            );

            // ✅ Wait for page fully loaded
            wait.until(driver ->
                    ((JavascriptExecutor) driver)
                            .executeScript("return document.readyState")
                            .equals("complete")
            );

            driver.switchTo().defaultContent();

            // ✅ Now wait for Logged in text
            WebElement loggedInText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//a[contains(text(),'Logged in as')]"))
            );

            Assert.assertTrue(loggedInText.isDisplayed(),
                    "User login verification failed: 'Logged in as' text not found after login");

            logger.info("✅ User successfully logged in");

        } catch (Exception e) {

            logger.error("Login verification failed: " + e.getMessage());
            Assert.fail("User login verification failed due to exception.");
        }
    }

    public void verifyErrorMessageForInvalidCredentials() {

        System.out.println("Verifying error message for invalid login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(incorrectLoginCredMessage)
        );

        Assert.assertEquals(
                errorMsg.getText().trim(),
                "Your email or password is incorrect!",
                "Error message doesn't match"
        );
    }

    public void verifySignupLoginLinkVisible(){
        Assert.assertTrue(
                signupLoginLink.isDisplayed(),
                "Signup / Login link is not visible"
        );
    }

    // Home page loaded (BEFORE login)
    private By homePageSlider = By.id("slider-carousel");

    public void verifyHomePageLoaded() {

        System.out.println("Verifying home page is loaded");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement slider = wait.until(
                ExpectedConditions.visibilityOfElementLocated(homePageSlider)
        );

        Assert.assertTrue(slider.isDisplayed(), "Home page is NOT loaded");
    }

    public void verifyUserLoggedIn() {

        logger.info("Verifying 'Logged in as' text");

        driver.switchTo().defaultContent();
        handleAd();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement loggedInText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[contains(text(),'Logged in as')]")
                )
        );

        Assert.assertTrue(
                loggedInText.isDisplayed(),
                "❌ 'Logged in as' text is not visible"
        );

        logger.info("✅ 'Logged in as' text verified");
    }

    public void verifyLogoutLinkVisible() {

        logger.info("Verifying Logout link");

        driver.switchTo().defaultContent();
        handleAd();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOf(logoutLink));

        Assert.assertTrue(
                logoutLink.isDisplayed(),
                "❌ Logout link is not visible"
        );

        logger.info("✅ Logout link verified");
    }

}



