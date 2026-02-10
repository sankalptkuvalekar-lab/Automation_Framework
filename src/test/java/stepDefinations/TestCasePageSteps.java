package stepDefinations;

import io.cucumber.java.en.Then;
import pageObjects.TestCasePage;

public class TestCasePageSteps extends BaseClass{

    @Then("Click on Test Cases button")
    public void click_on_test_cases_button() {
        testCasePage.clickTestCases();
        handleAd();

    }
    @Then("Verify user is navigated to test cases page successfully")
    public void verify_user_is_navigated_to_test_cases_page_successfully() {
        testCasePage.verifyTestCasesPageIsVisible();
    }

    @Then ("Click on Test case Button")
    public void click_on_testcase(){

            testCasePage.clickTestCases();
        handleAd();
    }

}
