package base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import utils.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new RuntimeException("Driver not initialized");
        }
        return driver;
    }

    @Before(order = 0)
    public void setup() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setCapability("--headless",true);
                driver = new SafariDriver(safariOptions);


                break;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(ConfigReader.getProperty("app.url"));
        driver.manage().window().maximize();
        System.out.println("Todo Management App Launched");

    }


    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenshot(driver, scenario.getName());
        }
            if (driver != null) {

                driver.quit();
                driver = null;
            }

    }
    public static void takeScreenshot(WebDriver driver, String scenarioName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = "screenshots/" +scenarioName + "_" + timestamp + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
            System.out.println("Saved Screenshot" + destination);
        } catch (IOException e) {
            System.out.println("Capture Failed " + e.getMessage());
        }
    }

}
