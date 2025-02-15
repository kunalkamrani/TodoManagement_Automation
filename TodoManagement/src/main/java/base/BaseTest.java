package base;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigReader;

public class BaseTest {

    private static WebDriver driver;

    @Before(order = 0)
    public void setup() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        String projectPath = System.getProperty("user.dir");
        System.out.println("Project path is : " + projectPath);
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", projectPath + ConfigReader.getProperty("chrome.path"));
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", projectPath + ConfigReader.getProperty("firefox.path"));
            driver = new FirefoxDriver();

        }
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.get(ConfigReader.getProperty("app.url"));
        driver.manage().window().maximize();
        System.out.println("Driver Launched");

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new RuntimeException("Driver not initialized");
        }
        return driver;
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
