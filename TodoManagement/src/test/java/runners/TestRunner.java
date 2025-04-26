package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/TodoManagement.feature",
        glue = {"stepDefinitions", "base"},
        monochrome = true,
//        plugin = {"pretty", "json:target/cucumber.json", "html:target/cucumber-reports.html"}
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)

public class TestRunner extends AbstractTestNGCucumberTests {

}
