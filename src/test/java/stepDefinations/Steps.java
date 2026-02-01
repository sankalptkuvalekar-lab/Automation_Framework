/*package stepDefinations;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.File;
import org.openqa.selenium.Alert;
import utilities.ExtentReporterListener;

public class Steps extends BaseClass {

    // Logger and config properties
    private Logger logger;
    private Properties configProp;

    // Page objects
    private LoginPage lp;
    private UserRegister userReg;
    private UserLoginPage userLogin;
    private NewUserLoginPage newuserLoginPage;
    private NewRegisterPage newRegisterPage;
    private ContactUsPage contactUsPage;
    private TestCasePage testCasePage;
    private ProductListAndPage productListAndPage;
    private AddToCart addToCart;
    private HomePage homePage;
    private AddCustomerPage addCust;
    private SearchCustomerPage searchCust;

    public Steps() throws IOException {
        // Logger setup
        logger = Logger.getLogger("nopCommerce");
        PropertyConfigurator.configure(
                "/Users/sankalp/IdeaProjects/nopCommerce_Cucumberframework/src/test/resources/Log4j.properties"
        );

        // Config properties
        configProp = new Properties();
        FileInputStream configPropfile = new FileInputStream(
                "/Users/sankalp/IdeaProjects/nopCommerce_Cucumberframework/src/test/resources/Config.properties"
        );
        configProp.load(configPropfile);

        // Initialize page objects using driver from BaseClass
        lp = new LoginPage(driver);
        userReg = new UserRegister(driver);
        userLogin = new UserLoginPage(driver);
        newuserLoginPage = new NewUserLoginPage(driver);
        newRegisterPage = new NewRegisterPage(driver);
        contactUsPage = new ContactUsPage(driver);
        testCasePage = new TestCasePage(driver);
        productListAndPage = new ProductListAndPage(driver);
        addToCart = new AddToCart(driver);
        homePage = new HomePage(driver);
        addCust = new AddCustomerPage(driver);
        searchCust = new SearchCustomerPage(driver);
    }

   //global variable required accross all the methods

    @Given("User Launch Chrome Browser")
    public void user_launch_chrome_browser() {
        logger.info("********** Browser Already Launched **********");
    }

    @When("User open URL {string}")
    public void user_open_url(String url) {
        logger.info("********** Opening URL **********");
        driver.get(url);

        driver.manage().window().maximize();
    }
    public void applyStealth(WebDriver driver) {
        String stealthScript = "Object.defineProperty(navigator, 'webdriver', {get: () => undefined});";
        ((JavascriptExecutor) driver).executeScript(stealthScript);
    }



    public void user_enters_email_as_and_password_as(String email, String password) {
        logger.info("********** Providing Login Details **********");
        lp.setPassword(email);
        lp.setPassword(password);

    }

    @When("click on Login")
    public void click_on_login() throws InterruptedException {
        logger.info("********** Started Login Process **********");
        Thread.sleep(3000);
        lp.clickLogin();
        Thread.sleep(3000);

    }

    @Then("Page Title should be {string}")
    public void page_title_should_be(String title) throws InterruptedException {
        //to check title of the page
        if(driver.getPageSource().contains("Login was unsuccessful.")){
            driver.close();
            logger.info("********** Login Passed **********");
            Assert.assertTrue(false);
        }else{
            logger.info("********** Login Failed **********");
            Assert.assertEquals(title,driver.getTitle());
        }
        Thread.sleep(3000);

    }

    @When("user click on Logout link")
    public void user_click_on_logout_link() throws InterruptedException {
        logger.info("********** Click on Logout link **********");
   lp.clickLogout();
   Thread.sleep(3000);
    }


    @Then("Close Browser")
    public void close_browser() {

        logger.info("********** Closing Browse r**********");
        driver.quit();
    }

    //Customer feature step definations.....................................

    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
     addCust=new AddCustomerPage(driver);
     Assert.assertEquals(addCust.getPageTitle(),"Dashboard / nopCommerce administration");
    }
    @When("User click on customers Menu")
    public void user_click_on_customers_menu() throws InterruptedException {
        Thread.sleep(3000);
      addCust.clickOnCustomersMenu();
    }
    @When("click on customers Menu Item")
    public void click_on_customers_menu_item() throws InterruptedException {
        Thread.sleep(2000);
       addCust.clickOnCustomersMenuItem();
    }
    @When("click on Add new button")
    public void click_on_add_new_button() throws InterruptedException {
     addCust.clickOnAddnew();
        Thread.sleep(2000);
    }
    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() {
    Assert.assertEquals(addCust.getPageTitle(),"Add a new customer/ nopCommerce administration");
    }
    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {

        logger.info("********** Adding New Customer **********");
        logger.info("********** Providing Customer Details **********");
       String email=randomString()+"@gmail.com";

       addCust.setEmail(email);
       addCust.setPassword("test123");

        // Registered - default
        // The customer cannot be in both 'Guests' and 'Registered' customer roles
        // Add the customer to 'Guests' or 'Registered' customer role
        addCust.setCustomerRoles("Guest");
        Thread.sleep(3000);

        addCust.setManagerOfVendor("Vendor 2");
        addCust.setGender("Male");
        addCust.setFirstName("Pavan");
        addCust.setLastName("Kumar");
        addCust.setDob("7/05/1985"); // Format: D/MM/YYY
        addCust.setCompanyName("busyQA");
        addCust.setAdminContent("This is for testing........");

    }
    @When("click on Save button")
    public void click_on_save_button() throws InterruptedException {
        logger.info("********** Saving Customer Data **********");
     addCust.clickOnSave();
     Thread.sleep(3000);
    }
    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String msg) {
       Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
               .contains("The ew customerhas been added successfully"));
    }

    //steps for searchung a customer using email id.........

    @When("Enter customer Email")
    public void enter_customer_email() {

        logger.info("********** Searching customer by email id **********");

        searchCust= new SearchCustomerPage(driver);
        searchCust.setEmail("victoria_victotia@nopCommerce.com");

    }
    @When("Click on Search button")
    public void click_on_search_button() throws InterruptedException {

        searchCust.clickSearch();
        Thread.sleep(3000);

    }
    @Then("User should found Email in the Search table")
    public void user_should_found_email_in_the_search_table() {

       boolean status= searchCust.searchCustomerByEmail("victoria_victotia@nopCommerce.com");
        Assert.assertEquals(status,true);
    }

    //steps for searchung a customer using email FirstName and LatNmae.........

    @When("Enter customer FirstName")
    public void enter_customer_first_name() {
        logger.info("********** Searching customer by Name*********");

        searchCust= new SearchCustomerPage(driver);
        searchCust.setFirstName("victoria");
    }
    @When("Enter customer LastName")
    public void enter_customer_last_name() {
       searchCust.setLastName("Terces");
    }
    @Then("User should found Name in the Search table")
    public void user_should_found_name_in_the_search_table() {
     boolean status=searchCust.searchCustomerByName("Victoria Terces");
     Assert.assertEquals(status,true);
    }

//user register steps

@When("User open the URL {string}")
public void user_open_the_url(String url) {
    logger.info("********** Opening URL **********");
    driver.get(url);
    driver.manage().window().maximize();

}
@And("User Click on Register link")
public void user_click_on_register_link() {
    userReg = new UserRegister(driver);
    userReg.clickRegisterlink();

}
    @And("User enter his info")
    public void user_enter_his_info() {
       userReg.setGenderBullet();
     userReg.setFirstNametxt("user1");
     userReg.setLastNametxt("demo");
     userReg.setEmailtxt("user1@gmail.com");
     userReg.setCompanyNametxt("comapny");
     userReg.setPasswordtxt("user1@123");
        userReg.setConfirmPasswordtxt("user1@123");


    }
    @And("Click on Register button")
    public void click_on_register_button() {
      userReg.clickRegisaterbutton();
    }
    @And("click on Continue")
    public void click_on_continue() {
     userReg.clickContinueButton();
    }


    //user login steps

    @When("User Click on Login link")
    public void user_click_on_login_link() {
        userLogin = new UserLoginPage(driver);
       userLogin.clickLoginLink();
    }
    @And("User enter his username and password")
    public void user_enter_his_username_and_password() {
       userLogin.setEmailLogin("user1@gmail.com");
       userLogin.setPwdLogin("user123");
    }
    @And("Click on Login button")
    public void click_on_login_button() {
        userLogin.clickLoginButton();
    }


    //new user login

    @When("User Click on the Login link")
public void user_click_on_the_login_link() {
     newuserLoginPage = new NewUserLoginPage(driver);
       newuserLoginPage.loginLinkClick();
}
@When("User enter his Email and password")
public void user_enter_his_email_and_password() {
       newuserLoginPage.emailEnter("user66@gmail.com");
        newuserLoginPage.passwordEnter("user@123");
}
@When("User Click on Login button")
public void user_click_on_login_button() {
    newuserLoginPage.clickLoginButton();
}
    @When("User enter his {string} and {string}")
    public void user_enter_his_and(String email, String password) {

     newuserLoginPage.emailEnter(email);
     newuserLoginPage.passwordEnter(password);

    }

    //logout button
    @When("click on Logout button")
    public void click_on_logout_button() {
    newuserLoginPage.clickLpogputLink();
    }

    @Then("Verify that home page is visible successfully")
    public void verify_that_home_page_is_visible_successfully() {
        System.out.println("Verify that the Home Page Header is Visible");
        newuserLoginPage = new NewUserLoginPage(driver);
        newuserLoginPage.verifyHomePage();

    }
    @Then("Verify Login to your account is visible")
    public void verify_is_visible() {
        newuserLoginPage = new NewUserLoginPage(driver);
        newuserLoginPage.verifyloginPage();
    }
    @Then("Verify that Logged in as username is visible")
    public void verify_that_is_visible() {
        newuserLoginPage = new NewUserLoginPage(driver);
       newuserLoginPage.verifyHomePageAfterLogin();

    }

    @Then("Verify error Your email or password is incorrect! is visible")
    public void verify_error_message(){
    newuserLoginPage = new NewUserLoginPage(driver);
    newuserLoginPage.verifyHErrorMeeageofInvalidCredentials();

    }

    @Then("Verify that the user is navigated to the login Page")
    public void verify_that_the_user_is_navigated_to_the_login_page() {

        newuserLoginPage = new NewUserLoginPage(driver);
        newuserLoginPage.verifyTheLoginButtonIsVisible();
    }


    //Existing Registration

    @Then("Verify New User Signup is visible")
    public void verify_new_user_signup_is_visible() {
       newRegisterPage = new NewRegisterPage(driver);
       newRegisterPage.verifyRegisterPageTitle();

    }
    @Then("Enter name and already registered email address")
    public void enter_name_and_already_registered_email_address() {
       newRegisterPage.registerNameField("user67");
       newRegisterPage.registerEmaiField("user66@gmail.com");

    }
    @Then("Click Signup button")
    public void click_signup_button() {
       newRegisterPage.clickSignupButton();

    }
    @Then("Verify error Email Address already exist! is visible")
    public void verify_error_email_address_already_exist_is_visible() throws InterruptedException {
        System.out.println("Verify error Email Address already exist! is visible");
       Thread.sleep(3000);
       newRegisterPage.verifyEmailExistErrorIsVisible();

    }


    //cCONTACT US FORM

    @Then("Click on Contact Us button")
    public void click_on_contact_us_button() {
     contactUsPage = new ContactUsPage(driver);
     contactUsPage.clickContactUs();

    }
    @Then("Verify CONTACT US is visible")
    public void verify_get_in_touch_is_visible() {
       contactUsPage=new ContactUsPage(driver);
       contactUsPage.verifyGetInTouchVisible();



    }
    @Then("Enter name, email, subject and message")
    public void enter_name_email_subject_and_message() {
       contactUsPage.enterContactName("user66");
       contactUsPage.enterContactEmail("user66@gmail.com");
       contactUsPage.enterContactSubject("Technical Issue");
       contactUsPage.enterContactMessage("Hello, I am having trouble accessing my account. Please help.");

    }
    @Then("Upload file")
    public void upload_file() {
        String path = "/Users/sankalp/Desktop/testscreenshot.png";
        contactUsPage.uploadDocument(path);

    }
    @Then("Click Submit button")
    public void click_submit_button() {
       contactUsPage.clickSubmit();

    }
    @Then("Click OK button")
    public void click_ok_button() {
        Alert alert = driver.switchTo().alert();
        alert.accept();

    }
    @Then("Verify success message Success! Your details have been submitted successfully. is visible")
    public void verify_success_message_success_your_details_have_been_submitted_successfully_is_visible() {
      System.out.println("verifying the success message of form submission");
       contactUsPage.verifySuccessMessage();

    }
    @Then("Click Home button and verify that landed to home page successfully")
    public void click_home_button_and_verify_that_landed_to_home_page_successfully() {
        contactUsPage.clickHomeButton();

        // Check if the URL contains the base domain, ignoring the #google_vignette suffix
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("automationexercise.com"),
                "Did not land on the home page! Actual URL: " + actualUrl);
    }

    //test case page

    @Then("Click on Test Cases button")
    public void click_on_test_cases_button() {
       testCasePage= new TestCasePage(driver);
       testCasePage.clickTestCases();
        handleAd();

    }
    @Then("Verify user is navigated to test cases page successfully")
    public void verify_user_is_navigated_to_test_cases_page_successfully() {

      testCasePage= new TestCasePage(driver);
        testCasePage.verifyTestCasesPageIsVisible();
    }


    //product list amnd product page

    @Then("Click on Products button")
    public void click_on_products_button() {
     productListAndPage= new ProductListAndPage(driver);
     productListAndPage.clickProducts();
     //productListAndPage.dismissAdIfPresent();
        handleAd();

    }
    @Then("Verify user is navigated to ALL PRODUCTS page successfully")
    public void verify_user_is_navigated_to_all_products_page_successfully() {
        // 1. Get the actual text from the Page Object method
        String actualHeader = productListAndPage.getAllProductsHeaderText();

        // 2. Define what you expect to see
        String expectedHeader = "ALL PRODUCTS";

        // 3. Perform the assertion (using .equalsIgnoreCase to avoid case sensitivity issues)
        Assert.assertTrue(actualHeader.equalsIgnoreCase(expectedHeader),
                "Expected header 'ALL PRODUCTS' but found: " + actualHeader);
    }


    @Then("Click on View Product of first product")
    public void click_on_of_first_product() throws InterruptedException {
        // Call the simple click method from your Page Object
        productListAndPage.clickFirstViewProduct();
  handleAd();


    }


    @Then("User is landed to product detail page")
    public void user_is_landed_to_product_detail_page() throws InterruptedException {
        // Optional: Add a simple verification that the URL changed
        Thread.sleep(3000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("product_details"), "Navigation to product details failed!");
    }

    @Then("Verify that detail detail is visible: product name, category, price, availability, condition, brand")
    public void verify_that_detail_detail_is_visible_product_name_category_price_availability_condition_brand() {
        productListAndPage.verifyProductDetailsAreVisible();

        // Log the product name for the report
        System.out.println("Verified Product: ");
    }

    //search product present in productListAndPage

    @Then("Enter product name in search input and click search button")
    public void enter_product_name_in_search_input_and_click_search_button() {
        logger.info("********** Starting Product Search **********");

        // 1. Force a check for the ad
        handleAd();

        // 2. If the element is STILL not found, the ad might be an iframe overlay
        // instead of a URL redirect. Let's handle both.
        try {
           productListAndPage.searchProduct("Polo");
        } catch (Exception e) {
            logger.warn("Search bar not visible. Retrying ad handling...");
            driver.navigate().refresh(); // Force refresh if first attempt failed
            handleAd();
            productListAndPage.searchProduct("Polo");
        }
    }
    @Then("Verify SEARCHED PRODUCTS is visible")
    public void verify_searched_products_is_visible() {
        Assert.assertTrue(productListAndPage.isSearchedHeaderVisible(),
                "The 'SEARCHED PRODUCTS' header is not visible on the page.");

        // 2. Verify the text content matches your expectation
        String actualHeader = productListAndPage.getSearchedHeaderText();
        Assert.assertEquals(actualHeader.toUpperCase(), "SEARCHED PRODUCTS",
                "Header text mismatch! Found: " + actualHeader);


    }
    @Then("Verify all the products related to search are visible")
    public void verify_all_the_products_related_to_search_are_visible() {
        // 1. Assert that the search result list is not empty
        boolean isVisible = productListAndPage.areProductsVisibleAfterSearch();
        Assert.assertTrue(isVisible, "No products were found in the search results!");


    }

    //add to cart

    @Then("Click on Add To Cart Button")
    public void click_on_add_to_cart_button() {
       addToCart= new AddToCart(driver);
       addToCart.clickAddToCart();
        handleAd();



    }
    @Then("Click on the View Cart link of the popup")
    public void click_on_the_view_cart_link_of_the_popup() {
        addToCart.clickViewCart();
        handleAd();

    }
    @Then("Verify their prices, quantity and total price")
    public void verify_their_prices_quantity_and_total_price() {
        String price = addToCart.getFirstProductPrice();
        String quantity = addToCart.getFirstProductQuantity();
        String total = addToCart.getFirstProductTotal();

        // 2. Perform Assertions
        Assert.assertFalse(price.isEmpty(), "Price is not visible!");
        Assert.assertEquals(quantity, "1", "Quantity mismatch!");

        // Logic: In a single item scenario, Price should equal Total
        Assert.assertEquals(price, total, "Price and Total do not match for single quantity!");

        System.out.println("Verified - Price: " + price + ", Qty: " + quantity + ", Total: " + total);

    }



    //subscription verification steps

    @Then("Scroll down to footer")
    public void scroll_down_to_footer() {
        homePage = new HomePage(driver);
       homePage.scrollToFooter();

    }
    @Then("Verify text SUBSCRIPTION")
    public void verify_text( ) {
        String actualText = homePage.getSubscriptionSectionText();

        // Check if visible
        Assert.assertTrue(homePage.isSubscriptionHeaderVisible(), "Subscription text is not visible!");

        // Check if text matches "SUBSCRIPTION"
        Assert.assertEquals(actualText.toUpperCase(), "subscription".toUpperCase(),
                "Text mismatch! Expected " + "SUBSCRIPTION" + " but found " + actualText);

    }
    @Then("Enter email address in input and click arrow button")
    public void enter_email_address_in_input_and_click_arrow_button() {
        String testEmail = "user66" + System.currentTimeMillis() + "@useer66.com";
        homePage.subscribeWithEmail(testEmail);
    }
    @Then("Verify success message You have been successfully subscribed is visible")
    public void verify_success_message_is_visible() {
        Assert.assertTrue(homePage.isSuccessMessageVisible(),
                "The subscription success message was not displayed!");

        // 2. Hardcoded text verification
        String actualMessage = homePage.getSubscriptionSuccessText();
        String expectedMessage = "You have been successfully subscribed!";

        Assert.assertEquals(actualMessage, expectedMessage,
                "The success message text does not match!");
    }

    //subscription in cart page

    @Then("Click Cart button")
    public void click_on_cart_page(){
       addToCart= new AddToCart(driver);
       addToCart.clickCart();
        handleAd();

    }

    //add multiple products tpmcart
    @Then("Hover over first product and click Add to cart")
    public void hover_over_first_product_and_click_add_to_cart() {
        if(productListAndPage == null) {
            productListAndPage = new ProductListAndPage(driver);
        }
        productListAndPage.hoverAndClickFirstAddToCart();
    }
    @Then("Click Continue Shopping button")
    public void click_continue_shopping_button() {
        productListAndPage.clickContinueShopping();
    }
    @Then("Hover over second product and click Add to cart")
    public void hover_over_second_product_and_click_add_to_cart() {
        productListAndPage.hoverAndClickSecondAddToCart();
    }
    @Then("Click View Cart button")
    public void click_view_cart_button() {
     productListAndPage.clickViewCart();
    }
    @Then("Verify both products are added to Cart")
    public void verify_both_products_are_added_to_cart() {
        int actualCount = addToCart.getCartProductCount();

        // Check if the size is 2
        Assert.assertEquals(actualCount, 2, "Expected 2 products in cart but found: " + actualCount);

        // Optional: Print names for the console log
        System.out.println("Products in cart: " + addToCart.getAllProductNames());
    }

    //Registration

    @Then("Enter name and email address")
    public void enter_name_and_email_address() {
        String name = "user66";
        String email = randomString() + "@gmail.com";

        // Call the method from your initialized Page Object
        newRegisterPage.enterNameAndEmail(name, email);
        handleAd();

        logger.info("********** Entered name and email for registration **********");
    }
    @Then("Verify that ENTER ACCOUNT INFORMATION is visible")
    public void verify_that_eis_visible( ) {

        boolean isVisible = newRegisterPage.isEnterAccountInfoVisible();

        // Logging failure or success for the report
        if (!isVisible) {
            logger.error("ENTER ACCOUNT INFORMATION header not found!");
        }

        Assert.assertTrue(isVisible, "'ENTER ACCOUNT INFORMATION' is not visible on the page.");
    }

    @Then("Fill details: Title, Name, Email, Password, Date of birth")
    public void fill_details_title_name_email_password_date_of_birth() {
        logger.info("********** Filling Account Information **********");

        newRegisterPage.fillAccountDetails("P@ssword123", "10", "May", "1990");
        handleAd();
    }
    @Then("Select checkbox Sign up for our newsletter!")
    public void select_checkbox_sign_up_for_our_newsletter() {
        logger.info("********** Selecting Newsletter Checkbox **********");
        newRegisterPage.clickNewsletterCheckbox();
        handleAd();

    }
    @Then("Select checkbox Receive special offers from our partners!")
    public void select_checkbox_receive_special_offers_from_our_partners() {
        logger.info("********** Selecting Special Offers Checkbox **********");
        newRegisterPage.selectSpecialOffers();
        handleAd();
    }
    @Then("Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number")
    public void fill_details_first_name_last_name_company_address_address2_country_state_city_zipcode_mobile_number() {
       newRegisterPage = new NewRegisterPage(driver);
        logger.info("********** Filling Address Information **********");

        newRegisterPage.fillAddressDetails(
                "John", "Doe", "Tech Corp", "123 Street", "Suite 100",
                "United States", "California", "Los Angeles", "90001", "1234567890"
        );
    }
    @Then("Click Create Account button")
    public void click_create_account_button() {
        logger.info("********** Clicking Create Account Button **********");
        newRegisterPage.clickCreateAccount();
        handleAd();

    }
    @Then("Verify that ACCOUNT CREATED is visible")
    public void verify_that_account_created_is_visible() {
        logger.info("********** Verifying Account Creation Success **********");

        boolean isVisible = newRegisterPage.isAccountCreatedVisible();

        // If this assertion fails, the @After hook takes over
        Assert.assertTrue(isVisible, "Success message 'ACCOUNT CREATED!' was not visible.");
        handleAd();
    }
    @Then("Click Continue button")
    public void click_continue_button() {
        logger.info("********** Clicking Continue Button **********");
        newRegisterPage.clickContinue();
        handleAd();

    }
    @Then("Click Delete Account button")
    public void click_delete_account_button() {
        logger.info("********** Initiating Account Deletion **********");
        newRegisterPage.clickDeleteAccount();
        handleAd();
    }
    @Then("Verify that ACCOUNT DELETED is visible and click Continue button")
    public void verify_that_account_deleted_is_visible_and_click_button() {
        logger.info("********** Verifying Account Deletion and Closing Session **********");

        // 1. Verify Visibility
        boolean isVisible = newRegisterPage.isAccountDeletedVisible();
        Assert.assertTrue(isVisible, "Success message 'ACCOUNT DELETED!' was not visible.");

        // 2. Click Continue
        newRegisterPage.clickFinalContinue();
        handleAd();
    }

    //Checkout button
    @Then("Click Proceed To Checkout")
    public void Click_proceed_to_checkout(){
        logger.info("********** Clicking Proceed To Checkout Button **********");
        // Ensure cartPage is initialized in your BaseClass or here
        addToCart.clickProceedToCheckout();
        handleAd();

}
    @Then("Click RegisterLogin button")
    public void click_register_login_button() {
        logger.info("********** Clicking Register/Login button **********");
        addToCart.clickRegisterLogin();
        handleAd();
    }

    @Then("Click 'Cart' button")
    public void click_cart_button() {
        logger.info("********** Navigating to Cart Page **********");
        addToCart.clickCartButton();
        handleAd();
    }

    @Then("Verify Address Details and Review Your Order")
    public void verify_address_details_and_review_your_order() {
        checkoutPage=new CheckoutPage(driver);
        logger.info("********** Verifying Checkout Information **********");

        // Check Address Section
        boolean isAddressVisible = checkoutPage.isAddressDetailsDisplayed();
        Assert.assertTrue(isAddressVisible, "Address Details section is not visible!");

        // Check Review Order Section
        boolean isReviewVisible = checkoutPage.isReviewOrderDisplayed();
        Assert.assertTrue(isReviewVisible, "Review Your Order section is not visible!");
        handleAd();

        logger.info("Address Details and Review Order are successfully verified.");
    }

    @Then("Enter description in comment text area and click Place Order")
    public void enter_description_in_comment_text_area_and_click_place_order() {
        logger.info("********** Finalizing Order with Comments **********");

        checkoutPage.enterComment("This is a test order for automation verification.");
        checkoutPage.clickPlaceOrder();
        handleAd();
    }

    @Then("Enter payment details: Name on Card, Card Number, CVC, Expiration date")
    public void enter_payment_details_name_on_card_card_number_cvc_expiration_date() {
        paymentPage= new PaymentPage(driver);
        logger.info("********** Entering Payment Information **********");

        // You can also pass these as parameters if using a Scenario Outline
        paymentPage.enterPaymentDetails("John Doe", "4242424242424242", "311", "12", "2028");
        handleAd();

    }

    @Then("Click Pay and Confirm Order button")
    public void click_pay_and_confirm_order_button() {
        logger.info("********** Confirming the Order **********");
        paymentPage.clickPayAndConfirm();
        handleAd();
    }

    @Then("Verify success message Your order has been placed successfully")
    public void verify_success_message_your_order_has_been_placed_successfully() {
        logger.info("********** Verifying Order Success Message **********");

        boolean isVisible = paymentPage.isSuccessMessageVisible();

        // This assertion triggers the screenshot in Hooks if it fails
        Assert.assertTrue(isVisible, "The order success message was not displayed!");


        logger.info("Order successfully verified!");
        handleAd();
    }

    @Then("Scroll up page to top")
    public void scroll_up_page_to_top() {
        logger.info("********** Scrolling back to the top of the page **********");
        // Call the method from any of your initialized page objects
        homePage.scrollToTop();
        handleAd();
    }

    @Then("Verify that page is scrolled up and Full-Fledged practice website for Automation Engineers text is visible on screen")
    public void verify_page_scrolled_up_and_text_visible() {
        logger.info("********** Verifying Scroll Up and Header Visibility **********");

        boolean isVisible = homePage.isMainHeadingVisible();

        // If the scroll failed or text isn't there, Hooks will capture the screenshot
        Assert.assertTrue(isVisible, "The header text was not visible after scrolling up!");

        logger.info("Scroll up verified: Header text is visible.");
        handleAd();
    }
    @Then("Click on arrow at bottom right side to move upward")
    public void click_on_arrow_at_bottom_right_side_to_move_upward() {
        logger.info("********** Clicking the Scroll-Up Arrow **********");
        homePage.clickScrollUpArrow();
        handleAd();
    }

    @Then("Verify RECOMMENDED ITEMS are visible")
    public void verify_recommended_items_are_visible() {
        logger.info("********** Verifying Recommended Items Section **********");

        boolean isVisible = homePage.isRecommendedItemsVisible();

        // If not visible, your fixed Hooks class will capture the failure screenshot
        Assert.assertTrue(isVisible, "RECOMMENDED ITEMS section is not visible on the page!");
        handleAd();
    }

    @Then("Click on Add To Cart on Recommended product")
    public void click_on_add_to_cart_on_recommended_product() {
        logger.info("********** Adding Recommended Product to Cart **********");
        homePage.clickAddRecommendedProduct();
        handleAd();
    }

    @Then("Verify that categories are visible on left side bar")
    public void verify_that_categories_are_visible_on_left_side_bar() {
        logger.info("********** Verifying Sidebar Categories **********");

        boolean isVisible = homePage.isCategorySidebarVisible();

        // Assertion: If false, Hook will take a screenshot of the failure
        Assert.assertTrue(isVisible, "Category sidebar is NOT visible on the left side!");

        logger.info("Category sidebar is successfully verified.");
        handleAd();
    }

    @Then("Click on Women category")
    public void click_on_women_category() {
        logger.info("********** Clicking Women Category **********");
        homePage.clickWomenCategory();
        handleAd();
    }

    @Then("Click on any category link under 'Women' category, for example: Dress")
    public void click_on_women_dress_sub_category() {
        logger.info("********** Navigating to Women > Dress Category **********");
        homePage.clickWomenDressSubCategory();
        handleAd();
    }

    @Then("Verify that category page is displayed and confirm text {string}")
    public void verify_category_page_and_text(String expectedText) {

        categoryPage= new CategoryPage(driver);
        logger.info("********** Verifying Category Page Header **********");

        // Convert to upper case to match the site's styling if necessary
        String actualText = categoryPage.getCategoryTitleText();

        logger.info("Expected: " + expectedText);
        logger.info("Actual: " + actualText);

        Assert.assertEquals(actualText, expectedText, "The category page title does not match!");
        handleAd();
    }

    @Then("On left side bar, click on any sub-category link of 'Men' category")
    public void click_on_men_sub_category_link() {
        logger.info("********** Navigating to Men > Tshirts Category **********");

        // Step 1: Expand Men Category
        homePage.clickMenCategory();
        handleAd();

        // Step 2: Click Tshirts
        homePage.clickMenTshirtsSubCategory();
        handleAd();
    }

    @Then("Verify that user is navigated to that category page")
    public void verify_user_is_navigated_to_category_page() {
        logger.info("********** Verifying Category Page Navigation **********");
         categoryPage= new CategoryPage(driver);

        // 1. Verify URL contains 'category_products'
        String currentUrl = categoryPage.getCategoryPageUrl();
        Assert.assertTrue(currentUrl.contains("category_products"),
                "The URL does not indicate a category page! Current URL: " + currentUrl);

        // 2. Verify the title header is visible
        String title = categoryPage.getCategoryTitleText();
        Assert.assertFalse(title.isEmpty(), "The category title header is empty or not displayed!");

        logger.info("Successfully navigated to category page: " + title);
        handleAd();
    }

    //brands verifying steps
    @Then("Verify that Brands are visible on left side bar")
    public void verify_brands_visibility() throws InterruptedException {
        logger.info("********** Verifying Brands Sidebar after navigating to Products **********");

        // If a full-screen ad appeared, you might need to call driver.navigate().refresh()
        // or close the ad before this line.

        boolean isVisible = productListAndPage.isBrandsSectionVisible();

        Assert.assertTrue(isVisible, "The Brands sidebar section was not found on the Products page!");
        logger.info("Brands sidebar is visible.");
        handleAd();
    }

    @Then("Click on {string} brand name")
    public void click_on_brand_name(String brandName) {
        logger.info("********** Clicking on Brand: " + brandName + " **********");
        productListAndPage.clickBrandByName(brandName);
        handleAd();
    }

    @Then("Verify that user is navigated to brand page and brand products are displayed")
    public void verify_brand_page_navigation() {
        brandPage=new BrandPage(driver);
        logger.info("********** Verifying Brand Page Display **********");

        // 1. Verify URL contains 'brand_products'
        String currentUrl = brandPage.getCurrentBrandUrl();
        Assert.assertTrue(currentUrl.contains("brand_products"),
                "URL does not contain 'brand_products'. Actual: " + currentUrl);

        // 2. Verify Header is visible
        Assert.assertTrue(brandPage.isBrandPageHeaderVisible(), "Brand page header is not visible!");

        // 3. Log the actual header found (e.g., BRAND - HM PRODUCTS)
        String headerText = brandPage.getBrandHeaderText();
        logger.info("Confirmed Brand Page Header: " + headerText);

        Assert.assertTrue(headerText.contains("BRAND -"), "Header text does not follow brand format!");
        handleAd();
    }

    @Then("On left side bar, click on any other brand link")
    public void click_on_any_other_brand_link() {
        logger.info("********** Clicking on a different brand link **********");
        brandPage.clickAnyOtherBrand();
        handleAd();
    }

    @Then("Verify that user is navigated to that brand page and can see products")
    public void verify_brand_page_and_products() {
        logger.info("********** Verifying New Brand Page Content **********");

        // 1. Verify Header Text
        String header = brandPage.getBrandHeaderText1();
        logger.info("Brand Page Header: " + header);
        Assert.assertTrue(header.contains("BRAND -"), "The page header does not reflect a brand page!");

        // 2. Verify Products are present
        boolean productsVisible = brandPage.areProductsDisplayed();
        Assert.assertTrue(productsVisible, "No products were found for this brand!");

        logger.info("Successfully verified brand page and product visibility.");
        handleAd();
    }

    //adding review
    @Then("Verify 'Write Your Review' is visible")
    public void verify_write_your_review_is_visible() {
        logger.info("********** Verifying Review Section Visibility **********");
   productDetailsPage= new ProductDetailsPage(driver);
        handleAd();
        boolean isVisible = productDetailsPage.isReviewSectionVisible();

        Assert.assertTrue(isVisible, "The 'Write Your Review' section is not visible on the page!");
        logger.info("Successfully verified 'Write Your Review' section.");

    }

    @Then("Enter name, email and review")
    public void enter_name_email_and_review() {
        logger.info("********** Filling out the Product Review form **********");

        // Ensure no ad is blocking the input fields
        handleAd();

        // You can pass hardcoded strings or use parameters from the feature file
        productDetailsPage.enterReviewDetails("Test User", "testuser@example.com", "This product has excellent quality and fits perfectly.");
    }

    @Then("Click on Submit button")
    public void click_on_submit_button() {
        productDetailsPage.clickSubmitReview();
        // After clicking submit, an ad might trigger or a success message appear
        handleAd();
        logger.info("********** Clicked Review Submit Button **********");
    }

    @Then("Verify success message {string}")
    public void verify_success_message(String expectedMessage) {
        logger.info("********** Verifying Review Success Message **********");

        // Clear any potential ad redirecting the page after the Submit click
        handleAd();

        String actualMessage = productDetailsPage.getSuccessMessageText();

        logger.info("Expected: " + expectedMessage);
        logger.info("Actual: " + actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage, "The success message text does not match!");
    }

    @Then("Verify that the delivery address is same address filled at the time registration of account")
    public void verify_delivery_address_matches_registration() {
        checkoutPage = new CheckoutPage(driver);
        handleAd();

        String actualAddress = checkoutPage.getDeliveryCityStateZipText();
        String expectedAddress = "Los Angeles California 90001";

        // THE FIX:
        // 1. Replace Unicode non-breaking spaces (\u00A0) with standard spaces
        // 2. Replace all other whitespace (\s+) with a single space
        String cleanActual = actualAddress.replace("\u00A0", " ").replaceAll("\\s+", " ").trim();
        String cleanExpected = expectedAddress.replace("\u00A0", " ").replaceAll("\\s+", " ").trim();

        logger.info("Comparing Final Cleaned Actual: [" + cleanActual + "] with Cleaned Expected: [" + cleanExpected + "]");

        // Using contains is safer for address blocks with dynamic formatting
        Assert.assertTrue(cleanActual.contains(cleanExpected),
                "Mismatch found! Actual string contains hidden characters. Length Actual: "
                        + cleanActual.length() + " vs Expected: " + cleanExpected.length());
    }
    @Then("Verify that the billing address is same address filled at the time registration of account")
    public void verify_billing_address_matches_registration() {
        logger.info("********** Verifying Billing Address **********");

        // 1. Clear any ads that appeared during the transition to Checkout
        handleAd();

        // 2. Fetch the text using the flexible locator
        String actualBillingText = checkoutPage.getBillingCityStateZipText();

        // 3. Normalize whitespace to handle non-breaking spaces (&nbsp;) or tabs
        String cleanActual = actualBillingText.replace("\u00A0", " ").replaceAll("\\s+", " ").trim();
        String expectedBilling = "Los Angeles California 90001"; // Match your registration data
        String cleanExpected = expectedBilling.replaceAll("\\s+", " ").trim();

        logger.info("Comparing Actual: [" + cleanActual + "] with Expected: [" + cleanExpected + "]");

        // 4. Assertion
        Assert.assertTrue(cleanActual.contains(cleanExpected),
                "Billing address mismatch! Found: [" + cleanActual + "]");
    }

    //invoice


    @Then("Click 'Download Invoice' button and verify invoice is downloaded successfully")
    public void click_download_invoice_and_verify() throws InterruptedException {
        logger.info("********** Downloading and Verifying Invoice **********");

        // 1. Clear ads before interacting with the button
        handleAd();

        // 2. Click the download button
        paymentPage.clickDownloadInvoice();

        // 3. Wait for the download to complete (files take a moment to write to disk)
        Thread.sleep(3000);

        // 4. Verify file exists in the default download directory
        String downloadPath = System.getProperty("user.home") + "/Downloads";
        String fileName = "invoice.txt"; // The site typically downloads a .txt invoice

        File file = new File(downloadPath + "/" + fileName);

        Assert.assertTrue(file.exists(), "Invoice file was not found in: " + downloadPath);
        logger.info("Invoice downloaded successfully: " + file.getAbsolutePath());

        // 5. Clean up: Delete the file after verification so next test starts fresh
        if (file.exists()) {
            file.delete();
        }
    }

    @Then("Click 'Continue' button")
    public void click_continue_button1() {
        logger.info("********** Clicking Continue Button **********");

        // 1. Clear any current ads before clicking
        handleAd();

        // 2. Perform the click
        paymentPage.clickContinue();

        // 3. Clear the ad again if it was triggered by the button click
        handleAd();

        logger.info("Successfully clicked Continue and cleared potential ads.");
    }



}


*/














