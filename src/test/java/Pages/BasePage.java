package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public WebDriver getDriver() {
        return driver;
    }


    public WebDriverWait getWait() {
        return wait;
    }

    public void dispose() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void visit(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void waitForPageLoad() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public boolean isStale(WebElement element) {
        boolean isStale = true;
        int retries = 0;
        while (retries < 5 && isStale) {
            try {
                element.getSize();
                isStale = false;
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
                retries++;
            }
        }
        return isStale;
    }

    public void click(WebElement element) {
        waitForPageLoad();
        isStale(element);
        getWait().until(ExpectedConditions.visibilityOf(element));
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }


    public void sendKeys(WebElement element, String inputText) {
        waitForPageLoad();
        getWait().until(ExpectedConditions.visibilityOf(element));
        element.click();
        element.sendKeys(inputText);
    }

    public String getText(WebElement element) {
        waitForPageLoad();
        getWait().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

}
