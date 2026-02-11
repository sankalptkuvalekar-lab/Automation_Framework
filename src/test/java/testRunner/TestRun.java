/*package testRunner;

import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.ExtentReporterListener;

@Listeners({ExtentReporterListener.class})
@CucumberOptions
        (
          //need to specify location of feature and stepdefination file
                features = {".//Features/UserLogin.feature"}, //Users/sankalp/IdeaProjects/nopCommerce_Cucumberframework/Features/Login.feature
                glue=
                        {"stepDefinations","hooks"},


             //  dryRun = true, //check wthether every styeps haviung coreresponmding methods or not
                monochrome = true, //remove unnecessary characters from console
               plugin = {"pretty",
                       "html:Report/cucumber-reports/cucumber.html",
                      }


              //  tags= "@sanity"
        )
public class TestRun  extends AbstractTestNGCucumberTests {

}
*/

//added by me
/*package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import utilities.ExtentReporterListener;

@Listeners({ExtentReporterListener.class})
@CucumberOptions(
        features = {".//Features/NewSearchProduct.feature"},
        glue = {"stepDefinations", "hooks"},
        monochrome = true,
        plugin = {
                "pretty",
                "html:Report/cucumber-reports/cucumber.html"
        }
)
public class TestRun extends AbstractTestNGCucumberTests {

        // Force sequential execution
        @Override
        @DataProvider(parallel = false)
        public Object[][] scenarios() {
                return super.scenarios();
        }
}
*/

package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {".//Features/AddToCart.feature"},
        glue = {"stepDefinations", "hooks"},
        monochrome = true,
        plugin = {
                "pretty",
                //"html:Report/cucumber-reports/cucumber.html"
        }
)
public class TestRun extends AbstractTestNGCucumberTests {

        @Override
        @DataProvider(parallel = false)
        public Object[][] scenarios() {
                return super.scenarios();
        }
}
