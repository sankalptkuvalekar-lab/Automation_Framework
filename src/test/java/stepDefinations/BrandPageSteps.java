package stepDefinations;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObjects.BrandPage;

public class BrandPageSteps extends BaseClass {

    @Then("Verify that user is navigated to brand page and brand products are displayed")
    public void verify_brand_page_navigation() {
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

}
