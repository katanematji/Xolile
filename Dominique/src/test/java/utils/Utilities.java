package utils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;

public class Utilities {
    public WebDriver initializeWebDriver() throws Exception {

                String downloadFilepath = System.getProperty("user.dir") + "\\target\\report\\";
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", downloadFilepath);
                ChromeOptions options = new ChromeOptions();
                HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
                options.setExperimentalOption("prefs", chromePrefs);
                options.addArguments("--test-type");
                options.setExperimentalOption("useAutomationExtension", false);
                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
                cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                cap.setCapability(ChromeOptions.CAPABILITY, options);
                if (System.getProperty("os.name").contains("Windows")) {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                } else {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                }
                return new ChromeDriver(cap);
    }

    public String takeScreenshot(WebDriver driver) throws Exception {
        File scrFile = (File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String fileName = System.currentTimeMillis() + ".png";
        String fileAbsolutePath = "target/report/extent/" + fileName;
        File file = new File(fileAbsolutePath);
        FileUtils.copyFile(scrFile, file);
        return fileName;
    }

}
