package utils;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BaseTest {

	private static WebDriver driver;

	@Before
	public void setup() {
		if (getDriver() == null) {
			String projectPath = System.getProperty("user.dir");
			System.out.println("Project path is : " + projectPath);
			System.setProperty("webdriver.chrome.driver",
					projectPath + "/src/test/resources/drivers/chromedriver2.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
	}

	public static WebDriver getDriver() {
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
