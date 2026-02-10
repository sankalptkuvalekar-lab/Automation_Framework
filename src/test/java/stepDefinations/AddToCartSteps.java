package stepDefinations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AddToCartSteps extends BaseClass {

    @Then("Click on Add To Cart Button")
    public void click_on_add_to_cart_button() {
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

        Assert.assertFalse(price.isEmpty(), "Price is not visible!");
        Assert.assertEquals(quantity, "1", "Quantity mismatch!");

        Assert.assertEquals(price, total, "Price and Total do not match for single quantity!");

        System.out.println("Verified - Price: " + price + ", Qty: " + quantity + ", Total: " + total);

    }

    @Then("Verify both products are added to Cart")
    public void verify_both_products_are_added_to_cart() {
        int actualCount = addToCart.getCartProductCount();

        // Check if the size is 2
        Assert.assertEquals(actualCount, 2, "Expected 2 products in cart but found: " + actualCount);

        // Optional: Print names for the console log
        System.out.println("Products in cart: " + addToCart.getAllProductNames());
    }

    @Then("Click RegisterLogin button")
    public void click_register_login_button() {
        logger.info("********** Clicking Register/Login button **********");
        addToCart.clickRegisterLogin();
        handleAd();
    }

    @Then("Click Cart button")
    public void click_cart_button() {
        logger.info("********** Navigating to Cart Page **********");
        addToCart.clickCartButton();
        handleAd();
    }

    @When("User clicks the X button to remove the product")
    public void user_clicks_the_x_button_to_remove_the_product() {
       // addToCart.removeProductFromCart();
        addToCart.storeInitialCount(); // Remember how many items were there
        addToCart.removeFirstProduct(); // Delete one
    }

    @Then("The product should be removed from the cart")
    public void the_product_should_be_removed_from_the_cart() {
        //boolean isMsgVisible = addToCart.isCartEmptyMessageDisplayed();
        //Assert.assertTrue(isMsgVisible, "The 'Cart is empty!' message was not displayed!");
        boolean countDecreased = addToCart.isItemCountDecreased();
        Assert.assertTrue(countDecreased, "The product count did not decrease after removal!");
    }

    @Then("Verify that product quantity is {string} in cart")
    public void verify_that_product_quantity_is_in_cart(String expectedQty) {
        String actualQty = addToCart.getCartQuantity();

        logger.info("Verifying quantity. Expected: " + expectedQty + " | Actual: " + actualQty);

        // Assertion to fail the test if the quantity doesn't match
        Assert.assertEquals(actualQty, expectedQty, "Cart quantity mismatch!");
    }
}
