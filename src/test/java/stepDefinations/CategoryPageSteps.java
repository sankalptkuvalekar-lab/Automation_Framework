package stepDefinations;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObjects.CategoryPage;

public class CategoryPageSteps extends BaseClass{

    @Then("Verify that category page is displayed and confirm text {string}")
    public void verify_category_page_and_text(String expectedText) {
        logger.info("********** Verifying Category Page Header **********");

        // Convert to upper case to match the site's styling if necessary
        String actualText = categoryPage.getCategoryTitleText();

        logger.info("Expected: " + expectedText);
        logger.info("Actual: " + actualText);

        Assert.assertEquals(actualText, expectedText, "The category page title does not match!");
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
}
