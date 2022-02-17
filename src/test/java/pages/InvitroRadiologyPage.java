package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.regex.Pattern;


public class InvitroRadiologyPage {

    private WebDriver driver;

    public InvitroRadiologyPage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    private String productPrice;
    private String productPriceInCart;
    private String productTag;
    private By productPriceBys = By.xpath("//span[@class='result-item__price']");
    private By productPriceInCartBys = By.xpath("//div[contains(@class,'SummaryBlock_cartCostPositionCost')]");
    private By productToCartBtnBys = By.xpath("//div[@class='btn-icon btn-icon--fill btn-cart add_basket']");
    private By productTagBys = By.xpath("//div[@class='result-item__header']");
    private By productTegInCartBys = By.xpath("//div[contains(@class,'CartProduct_productTags')]");
    private By goToCartBtnBy = By.xpath("//div [@class='invitro-header-cart row row--center']");
    private Pattern pricePattern = Pattern.compile("\\d+");

    private By attentionCloseBtnBy = By.xpath("//button[@class='attention-close-button btn']");
    private By citySelectArrowBtnBy = By.xpath("//img[@class='city_arrow']");
    private By cityConfirmBtnBy = By.xpath("//button[@class='btn btn--narrow city__confirm-btn']");

    private By topMainMenuElementsBys = By.xpath("//li[@class='invitro_header-menu_main-item' and @style='display: block;']");
    private By moreMenuBlockBy = By.xpath("//li[@class='invitro_header-menu_main-item invitro_header-menu_main-item_more']");
    private By moreMenuElementsBys = By.xpath("//div[@id='moreMenuItems']//a");

    private By leftScrollMenuItemsBys = By.xpath("//ul[@class='side-bar-second__list']//li");

    private By cityChangeBtnBy = By.xpath("//a[@class='btn btn--narrow btn--empty city__change-btn']");
    private By changeCitySearchInputBy = By.xpath("//input[@class='change-city-search-input']");
    private String selectedCityStr = "//b[contains(text(), '%s')]";
    private By currentCityBy = By.xpath("//span[@class='city__name city__btn city__name--label']");

    private By analysisResultsBtnBy = By.xpath("//div[@class='invitro_header-bottom_right']");
    private By findResultsBtnBy = By.xpath("//button[contains(text(), 'Найти результаты')]");
    private By orderNumberInputBy = By.xpath("//input[@name='orderNumber']");
    private By birthdayInputBy = By.xpath("//input[@name='birthday']");
    private By lastNameInputBy = By.xpath("//input[@name='lastName']");
    private By resultsErrorBy = By.xpath("//div[contains(text(), 'Поля')]");

    private By searchHeaderInputBy = By.xpath("//input[@class='search__input form-header-search_input']");

    private By monitoringCloseBtnBy = By.xpath("//div[@id='monitoring_close']");
    private By subscriptionCloseBtnBy = By.xpath("//a[@class='subscription__close-btn']");

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductPriceInCart() {
        return productPriceInCart;
    }

    public InvitroRadiologyPage open() {
        driver.get("https://www.invitro.ru/radiology/");
        return this;
    }

    public InvitroRadiologyPage checkAndClickAttentionCloseBtn() {
        WebElement attentionCloseBtn = driver.findElement(this.attentionCloseBtnBy);
        if (attentionCloseBtn.isDisplayed()) {
            attentionCloseBtn.click();
        }
        return this;
    }

    public InvitroRadiologyPage checkAndClickCityConfirmBtn() {
        WebElement cityConfirmBtn = driver.findElement(cityConfirmBtnBy);
        if (cityConfirmBtn.isDisplayed()) {
            cityConfirmBtn.click();
        }
        return this;
    }

    public InvitroRadiologyPage checkAndClickCityChangeBtn() {
        WebElement cityConfirmBtn = driver.findElement(cityChangeBtnBy);
        if (cityConfirmBtn.isDisplayed()) {
            cityConfirmBtn.click();
        } else {
            driver.findElement(citySelectArrowBtnBy).click();
            driver.findElement(this.cityChangeBtnBy).click();
        }
        return this;
    }

    public InvitroRadiologyPage checkAndClickSubscriptionCloseBtn() {
        try {
            WebElement cityConfirmBtn = driver.findElement(subscriptionCloseBtnBy);
            if (cityConfirmBtn.isDisplayed()) {
                cityConfirmBtn.click();
            }
        } catch (NoSuchElementException e) {
        }
        return this;
    }

    public InvitroRadiologyPage checkAndClickMonitoringCloseBtn() {
        try {
            WebElement cityConfirmBtn = driver.findElement(monitoringCloseBtnBy);
            if (cityConfirmBtn.isDisplayed()) {
                cityConfirmBtn.click();
            }
        } catch (NoSuchElementException e) {
        }
        return this;
    }

    public InvitroRadiologyPage clickAllTopMenu() {
        int numberOfElements = driver.findElements(topMainMenuElementsBys).size();
        for (int i = 0; i < numberOfElements; i++) {
            checkAndClickAttentionCloseBtn();
            checkAndClickCityConfirmBtn();

            driver.findElements(topMainMenuElementsBys)
                    .get(i)
                    .click();
        }
        return this;
    }

    public InvitroRadiologyPage clickAllMoreMenu() {
        int numberOfElements = driver.findElements(moreMenuElementsBys).size();
        for (int i = 0; i < numberOfElements; i++) {
            checkAndClickAttentionCloseBtn();
            checkAndClickCityConfirmBtn();

            WebElement moreMenuBlock = driver.findElement(moreMenuBlockBy);

            new Actions(driver)
                    .moveToElement(moreMenuBlock, 2, 2)
                    .perform();

            driver.findElements(moreMenuElementsBys)
                    .get(i)
                    .click();
        }
        return this;
    }

    public InvitroRadiologyPage clickAllLeftScrollMenu() {
        int numberOfElements = driver.findElements(leftScrollMenuItemsBys).size();
        for (int i = 0; i < numberOfElements; i++) {
            driver
                    .findElements(leftScrollMenuItemsBys)
                    .get(i)
                    .click();
        }
        return this;
    }

    public InvitroRadiologyPage inputCityIntoSearchField(String city) {
        WebElement changeCitySearchInput = driver.findElement(this.changeCitySearchInputBy);
        changeCitySearchInput.sendKeys(city);
        changeCitySearchInput.sendKeys(" ", Keys.BACK_SPACE);
        return this;
    }

    public boolean isCityFounded(String city) {
        return driver.findElement(By.xpath(String.format(selectedCityStr, city))).isDisplayed();
    }

    public InvitroRadiologyPage clickOnFoundedCity(String city) {
        driver.findElement(By.xpath(String.format(selectedCityStr, city))).click();
        return this;
    }

    public InvitroRadiologyPage selectCity(String city) {
        checkAndClickAttentionCloseBtn();
        checkAndClickCityChangeBtn();

        WebElement changeCitySearchInput = driver.findElement(this.changeCitySearchInputBy);
        changeCitySearchInput.sendKeys(city);
        changeCitySearchInput.sendKeys(" ", Keys.BACK_SPACE);

        WebElement selectedCity = driver.findElement(By.xpath(String.format(selectedCityStr, city)));
        selectedCity.click();
        return this;
    }

    public boolean isCurrentCityCorrect(String city) {
        WebElement currentCity = driver.findElement(this.currentCityBy);
        return city.equals(currentCity.getText());
    }

    public String getCurrentCityBy() {
        return driver.findElement(this.currentCityBy).getText();
    }

    public InvitroRadiologyPage clickFindResultsBtn() {
        driver.findElement(findResultsBtnBy).click();
        return this;
    }

    public InvitroRadiologyPage clickAnalysisResultsBtn() {
        driver.findElement(analysisResultsBtnBy).click();
        return this;
    }

    public boolean isResultsErrorVisible() {
        return driver.findElement(resultsErrorBy).isDisplayed();
    }

    public boolean isResultsErrorContainsCorrectText() {
        String errorText = driver.findElement(resultsErrorBy).getText();
        return errorText.equals("Поля Код ИНЗДата рожденияФамилия обязательны для заполнения");
    }

    public String getResultsErrorText() {
        return driver.findElement(resultsErrorBy).getText();
    }

    public boolean isBorderColorRed() {
        WebElement orderNumberInput = driver.findElement(this.orderNumberInputBy);
        WebElement birthdayInput = driver.findElement(this.birthdayInputBy);
        WebElement lastNameInput = driver.findElement(this.lastNameInputBy);

        return orderNumberInput.getCssValue("border-color").equals("rgb(255, 0, 0)")
                && birthdayInput.getCssValue("border-color").equals("rgb(255, 0, 0)")
                && lastNameInput.getCssValue("border-color").equals("rgb(255, 0, 0)");
    }

    public InvitroRadiologyPage sendINZ(String inz) {
        driver.findElement(this.orderNumberInputBy).sendKeys(inz);
        return this;
    }

    public InvitroRadiologyPage sendBirth(String birth) {
        WebElement birthdayInput = driver.findElement(this.birthdayInputBy);
        birthdayInput.sendKeys(Keys.HOME);
        driver.findElement(this.birthdayInputBy).sendKeys(birth);
        birthdayInput.sendKeys(Keys.RETURN);
        return this;
    }

    public InvitroRadiologyPage sendLastName(String lastName) {
        driver.findElement(this.lastNameInputBy).sendKeys(lastName);
        return this;
    }

    public InvitroRadiologyPage enterTextIntoSearchHeaderInput(String search) {
        WebElement searchHeaderInput = driver.findElement(searchHeaderInputBy);
        searchHeaderInput.clear();
        searchHeaderInput.sendKeys(search);
        searchHeaderInput.submit();
        return this;
    }

    public InvitroRadiologyPage goToTopMenuItem(String itemTitle) {
        driver.findElements(topMainMenuElementsBys)
                .stream()
                .filter(element -> element.getText().equals(itemTitle))
                .findAny()
                .ifPresent(WebElement::click);
        return this;
    }

    public InvitroRadiologyPage goToLeftScrollMenuItem(String... itemTitles) {
        int numberOfElements = driver.findElements(leftScrollMenuItemsBys).size();
        for (int i = 0, j = 0; i < numberOfElements && j < itemTitles.length; i++) {
            WebElement element = driver.findElements(leftScrollMenuItemsBys).get(i);
            if (element.getText().equals(itemTitles[j])) {
                element.click();
                j++;
            }
        }
        return this;
    }

    public InvitroRadiologyPage rememberProductPrice(int n) {
        productPrice = pricePattern.matcher(driver.findElements(productPriceBys).get(n - 1).getText())
                .results().findAny().orElseThrow().group();
        productTag = driver.findElements(productTagBys).get(n - 1).getText();
        return this;
    }

    public InvitroRadiologyPage addProductToCart(int n) {
        //        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", productToCartBtn);
        driver.findElements(productToCartBtnBys).get(n - 1).click();
        return this;
    }

    public InvitroRadiologyPage clickOnGoToCartBtn() {
        driver.findElement(goToCartBtnBy).click();
        return this;
    }

    public boolean isProductPriceEquals() {
        List<WebElement> products = driver.findElements(productTegInCartBys);

        for (int i = 0; i < products.size(); i++) {
            WebElement product = products.get(i);
            if (product.getText().equals(productTag)) {
                productPriceInCart = pricePattern.matcher(driver.findElements(productPriceInCartBys).get(i).getText())
                        .results().findAny().orElseThrow().group();
                break;
            }
        }
        return productPriceInCart.equals(productPrice);
    }
}
