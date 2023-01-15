package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/Features"},
        glue = {"StepDefinitions"},
        plugin = {"pretty","json:target/json-report/cucumber.json"},
        dryRun = false,
        monochrome = true,
        tags = "@Ignore"
        //name = {"Search Link"}
)
public class TestRun extends AbstractTestNGCucumberTests {
}
