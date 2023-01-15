package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static final DriverFactory INSTANCE = new DriverFactory();
    private final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();

    private DriverFactory() {
    }

    public static DriverFactory getDriverFactory() {
        return INSTANCE;
    }

    public WebDriver getDriver(BrowserType type) {
        if (threadLocal.get() == null) {
            switch (type) {
                case CHROME:
                    threadLocal.set(new ChromeDriver());
                    break;
                case FIREFOX:
                    threadLocal.set(new FirefoxDriver());
                    break;
                default:
                    break;
            }
        }
        return threadLocal.get();
    }
}
