package stepDefinations;

import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePageSteps extends BaseClass{

    @Then("Scroll down to footer")
    public void scroll_down_to_footer() {
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
    /*public void click_on_women_dress_sub_category() {
        logger.info("********** Navigating to Women > Dress Category **********");
        homePage.clickWomenDressSubCategory();
        handleAd();
    }*/

    public void click_on_women_dress_sub_category() {

        logger.info("********** Navigating to Women > Dress Category **********");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // First Attempt
        homePage.clickWomenDressSubCategory();

        try {

            // If vignette hijacks
            if (driver.getCurrentUrl().contains("google_vignette")) {

                logger.info("Google Vignette detected. Navigating back and retrying...");

                driver.navigate().back();

                // Small wait for page stability
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='panel-group category-products']")));

                // Retry click
                homePage.clickWomenDressSubCategory();
            }

            // Now wait for actual category page
            wait.until(ExpectedConditions.urlContains("/category_products/"));

            logger.info("Successfully navigated to Category page.");

        } catch (Exception e) {

            throw new RuntimeException(" Failed to navigate to Women > Dress category", e);
        }
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

    @Then("Click on arrow at bottom right side to move upward")
    public void click_on_arrow_at_bottom_right_side_to_move_upward() {
        logger.info("********** Clicking the Scroll-Up Arrow **********");
        homePage.clickScrollUpArrow();
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

    @Then("Verify RECOMMENDED ITEMS are visible")
    public void verify_recommended_items_are_visible() {
        logger.info("********** Verifying Recommended Items Section **********");

        boolean isVisible = homePage.isRecommendedItemsVisible();

        // If not visible, your fixed Hooks class will capture the failure screenshot
        Assert.assertTrue(isVisible, "RECOMMENDED ITEMS section is not visible on the page!");
        handleAd();
    }

    @Then("Scroll up page to top")
    public void scroll_up_page_to_top() {
        logger.info("********** Scrolling back to the top of the page **********");
        // Call the method from any of your initialized page objects
        homePage.scrollToTop();
        handleAd();
    }

}
