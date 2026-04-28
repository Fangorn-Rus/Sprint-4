package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class SetUpBrowsers {
    private WebDriver driver;
    private static final String currentBrowser = "firefox";

    public WebDriver getDriver() {
        return setUp(currentBrowser);
    }

    public WebDriver setUp(String browser) {
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--no-sandbox");
                driver = new FirefoxDriver(firefoxOptions);
                break;
        }
        return driver;
    }
}
