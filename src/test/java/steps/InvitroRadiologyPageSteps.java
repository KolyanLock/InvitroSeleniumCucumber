package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.jodah.failsafe.internal.util.Assert;
import pages.InvitroRadiologyPage;

import java.util.List;

import static configuration.WebDriverConfigurator.*;

public class InvitroRadiologyPageSteps {

    InvitroRadiologyPage invitroRadiologyPage = new InvitroRadiologyPage(getWebDriver());
    ;

    @Given("^Открываю страницу invitro.ru/radiology/$")
    public void given_open_start_page() {
        invitroRadiologyPage.open();
    }

    @And("^Прокликиваю верхнее меню$")
    public void click_all_top_menu() {
        invitroRadiologyPage
                .clickAllTopMenu()
                .clickAllMoreMenu();
    }

    @When("^Кликаю по кнопке выбора города$")
    public void click_on_city_change_btn() {
        invitroRadiologyPage
                .checkAndClickAttentionCloseBtn()
                .checkAndClickCityChangeBtn();
    }

    @And("^Ввожу (.*) в строку поиска$")
    public void input_city_into_search_field(String city) {
        invitroRadiologyPage.inputCityIntoSearchField(city);
    }

    @Then("^Проверяю найден ли город (.*)$")
    public void check_find_result(String city) {
        Assert.isTrue(invitroRadiologyPage.isCityFounded(city),
                "Город " + city + "не найден!");
    }

    @When("^Кликаю по найденному городу (.*)$")
    public void click_on_founded_city(String city) {
        invitroRadiologyPage.clickOnFoundedCity(city);
    }

    @Then("^Проверяю дествительно ли выбрался город (.*)$")
    public void check_current_city(String city) {
        Assert.isTrue(invitroRadiologyPage.isCurrentCityCorrect(city),
                "Выбранный город почему-то не " + city +
                        "a, " + invitroRadiologyPage.getCurrentCityBy());
    }

    @When("^Кликаю по кнопке Результаты анализов$")
    public void click_on_analysis_result_btn() {
        invitroRadiologyPage.clickAnalysisResultsBtn();
    }

    @And("^Клиаю по копке Найти результаты$")
    public void click_on_find_result_btn() {
        invitroRadiologyPage.clickFindResultsBtn();
    }

    @Then("^Проверяю что поля выделленым красным и и появилось предупреждение$")
    public void check_error() {
        Assert.isTrue(invitroRadiologyPage.isResultsErrorVisible(), "Ошибка не отображается!");
        Assert.isTrue(invitroRadiologyPage.isResultsErrorContainsCorrectText(),
                "Текст ошибки:\n" + invitroRadiologyPage.getResultsErrorText() +
                        "\n не соотвествует условию!");
        Assert.isTrue(invitroRadiologyPage.isBorderColorRed(), "Поля не выделены красным!");
    }

    @When("^Ввожу неверные ИНЗ (.*), дату рождения  (.*), фамилию (.*)$")
    public void send_incorrect_data(String inz, String birth, String lastName) {
        invitroRadiologyPage
                .sendINZ(inz)
                .sendBirth(birth)
                .sendLastName(lastName);
    }

    @And("^Кликаю по очереди все элементы меню и подменю$")
    public void click_all_left_scroll_menu() {
        invitroRadiologyPage
                .checkAndClickAttentionCloseBtn()
                .checkAndClickCityConfirmBtn()
                .clickAllLeftScrollMenu();
    }

    @When("^Ввожу (.*) в строку поиска и жму ввод$")
    public void enter_code_into_search_header_input(String code) {
        invitroRadiologyPage
                .checkAndClickAttentionCloseBtn()
                .checkAndClickCityConfirmBtn()
                .enterTextIntoSearchHeaderInput(code);
    }

    @When("^Выбираю Анализы -> (.*) -> (.*)$")
    public void go_to_left_scroll_menu_item(String item1, String item2) {
        invitroRadiologyPage
                .checkAndClickAttentionCloseBtn()
                .checkAndClickCityConfirmBtn()
                .goToTopMenuItem("Анализы")
                .goToLeftScrollMenuItem(item1, item2);
    }

    @And("^Запоминаю сумму (.*) по списку анализа и добавляю его в корзину$")
    public void remember_product_price_and_add_to_cart(int n) {
        invitroRadiologyPage
                .checkAndClickMonitoringCloseBtn()
                .rememberProductPrice(n)
                .addProductToCart(n);
    }

    @Then("Захожу в корзирн и проверяю стоимость")
    public void go_to_cart_and_check_price() {
        invitroRadiologyPage
                .clickOnGoToCartBtn();
        Assert.isTrue(invitroRadiologyPage.isProductPriceEquals(), "Стоимость товара в прайсе и корзине разная!" +
                "\nСтоимость в прайсе: " + invitroRadiologyPage.getProductPrice() +
                "\nСтоимтость в корзине : " + invitroRadiologyPage.getProductPrice());
    }


    @When("^Кликаю по пункту верхнего меню (.*)$")
    public void go_to_some_top_menu_item(String itemTitle) {
        List<String> items = List.of("Запись к врачу", "Анализы", "Акции", "Адреса");
        Assert.isTrue(items.contains(itemTitle), "Неверный пункт меню! Доступные пункты:\n" + items);
        invitroRadiologyPage
                .checkAndClickAttentionCloseBtn()
                .checkAndClickCityConfirmBtn()
                .goToTopMenuItem(itemTitle);
    }
}
