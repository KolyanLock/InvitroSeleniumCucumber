import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.time.Duration;
import java.util.Set;

public class App {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-notifications");
        options.addArguments("disable-infobars");
        options.addArguments("block-new-web-contents");
        options.addArguments("disable-ax-menu-lis");
        options.addArguments("suppress-message-center-popups");
        options.addArguments("disable-automation");

        options.setPageLoadTimeout(Duration.ofSeconds(60));
        options.setScriptTimeout(Duration.ofSeconds(10));
        options.setImplicitWaitTimeout(Duration.ofSeconds(10));
        WebDriver webDriver = new ChromeDriver(options);

        FileInputStream fis = new FileInputStream("src/test/resources/cookies/invitro.ru.cookie");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Set<Cookie> cookies = (Set<Cookie>) ois.readObject();

        webDriver.get("https://www.invitro.ru/radiology/");

        for (Cookie cookie: cookies) {
            webDriver.manage().addCookie(cookie);
        }
        ois.close();
        fis.close();

        webDriver.navigate().refresh();

        By attentionCloseBtnBy = By.xpath("//button[@class='attention-close-button btn']");
        By cityConfirmBtnBy = By.xpath("//button[@class='btn btn--narrow city__confirm-btn']");

        WebElement attentionCloseBtn = webDriver.findElement(attentionCloseBtnBy);
        if (attentionCloseBtn.isDisplayed()) {
            attentionCloseBtn.click();
        }

        WebElement cityConfirmBtn = webDriver.findElement(cityConfirmBtnBy);
        if (cityConfirmBtn.isDisplayed()) {
            cityConfirmBtn.click();
        }

        String productPrice;
        By productPriceBys = By.xpath("//span[@class='result-item__price']");
        By productToCartBtnBys = By.xpath("//div[@class='btn-icon btn-icon--fill btn-cart add_basket']");
        By topMainMenuElementsBys = By.xpath("//li[@class='invitro_header-menu_main-item' and @style='display: block;']");
        By monitoringCloseBy = By.xpath("//div[@id='monitoring_close']");
        By subscriptionCloseBtnBy = By.xpath("//a[@class='subscription__close-btn']");

        webDriver.findElements(topMainMenuElementsBys)
                .stream()
                .filter(element -> element.getText().equals("Анализы"))
                .findAny()
                .ifPresent(WebElement::click);

//        webDriver.findElement(monitoringCloseBy).click();

        webDriver.findElements(topMainMenuElementsBys)
                .stream()
                .filter(element -> element.getText().equals("Анализы"))
                .findAny()
                .ifPresent(WebElement::click);


//        webDriver.findElement(subscriptionCloseBtnBy).click();

//        TimeUnit.SECONDS.sleep(5);
//
//        FileOutputStream fout = new FileOutputStream("src/test/resources/cookies/invitro.ru.cookie");
//        ObjectOutputStream oos = new ObjectOutputStream(fout);
//        oos.writeObject(webDriver.manage().getCookies());
//        oos.close();
//        fout.close();

        System.out.println(webDriver.findElements(productPriceBys).get(0).getText());
    }
}
