package StepDefinitions;

import DriverFactory.BrowserType;
import DriverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class CommonSteps {

    WebDriver driver;

    public CommonSteps() {
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "Resources/geckodriver.exe");
    }

    public WebDriver getDriver(BrowserType type) {
        this.driver = DriverFactory.getDriverFactory().getDriver(type);
        return driver;
    }

    @After
    public void tearDown(/*Scenario scenario*/) {
        /*if (scenario.isFailed()) {
            final byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot, "image/png", scenario.getName());
        }*/
        driver.quit();
    }
}
