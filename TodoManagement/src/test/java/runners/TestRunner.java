package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features/TodoManagement.feature",
        glue = {"stepDefinitions", "base"},
        monochrome = true,
        plugin = {"pretty", "json:target/cucumber.json", "html:target/cucumber-reports.html"})
public class TestRunner {

}
