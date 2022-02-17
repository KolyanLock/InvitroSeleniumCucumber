package configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class WebDriverConfigurator {

    public static WebDriver getWebDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-notifications");
        options.addArguments("disable-infobars");
        options.addArguments("disable-popup");

        options.setPageLoadTimeout(Duration.ofSeconds(60));
        options.setScriptTimeout(Duration.ofSeconds(10));
        options.setImplicitWaitTimeout(Duration.ofSeconds(10));

        return new ChromeDriver(options);
    }
}
