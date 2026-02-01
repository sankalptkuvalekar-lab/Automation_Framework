
package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {
        String dateName = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());

        // Absolute path
        String filePath = System.getProperty("user.dir") + File.separator + "Screenshots"
                + File.separator + testName + "_" + dateName + ".png";

        try {
            // Create folder if it doesn't exist
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/Screenshots"));

            // Take screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(filePath);
            Files.copy(src.toPath(), dest.toPath());

            System.out.println("Screenshot saved at: " + dest.getAbsolutePath());
            return dest.getAbsolutePath();  // return **absolute path**
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
