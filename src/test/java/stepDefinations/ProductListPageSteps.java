package stepDefinations;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObjects.ProductListAndPage;

public class ProductListPageSteps extends BaseClass{

    @Then("Click on Products button")
    public void click_on_products_button() {
       // productListAndPage.clickProducts();
        productListAndPage.clickOnProductsButton();
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
        productListAndPage.clickOnProductsButton();
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

    @Then("Hover over first product and click Add to cart")
    public void hover_over_first_product_and_click_add_to_cart() {
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

}
