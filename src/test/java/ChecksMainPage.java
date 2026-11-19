import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import groovy.util.logging.Slf4j;
import io.qameta.allure.selenide.AllureSelenide;
import object.ConfiguratorMain;
import object.MainPage;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$x;


@Slf4j
public class ChecksMainPage {

    private static final Logger log = LoggerFactory.getLogger(ChecksMainPage.class);

    MainPage mainPage = new MainPage();
    AssertsBasic.AssertMainPage assertMainPage = new AssertsBasic.AssertMainPage();


    @BeforeAll
    public static void initAllureSelenide(){
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @DisplayName("Запуск браузера")
    @BeforeEach
    void init(){
        Selenide.open(ConfiguratorMain.config.baseUrl());
    }

    @DisplayName("Закрытие браузера")
    @AfterEach
    void out(){
        Selenide.closeWebDriver();
    }


    @DisplayName("Проверка кнопки - Администрирование")
    @Test
    @Tag("Page")
    public void checkAdminButton(){
        log.info("Проверка кнопки - Администрирование");
        assertMainPage.checkTextAdminButton();
        assertMainPage.checkAdminButtonIsVisible();

        log.info("Нажал на Администрирование");
        mainPage.clickAdminButton();

        log.info("Проверка перехода на форму авторизации");
        assertMainPage.checkGoInAuthorization();
        log.info("Результат - УСПЕШНО");
    }

    @DisplayName("Проверка кнопки - Корзина")
    @Test
    @Tag("Page")
    public void checkButtonInCart(){
        log.info("Проверка кнопки - Корзина");
        assertMainPage.checkButtonInCartIsVisible();
        assertMainPage.checkTextButtonInCart();

        log.info("Жмем Корзина");
        mainPage.clickButtonInCart();

        log.info("Проверка перехода в Корзину");
        assertMainPage.checkGoInsideCart();
        log.info("Результат УСПЕШНО");
    }

    @DisplayName("Проверка видимости наименования магаза")
    @Test
    @Tag("Page")
    public void checkMainTitle(){
        log.info("Проверка видимости наименования магаза");
        assertMainPage.checkTextMainTitleIsVisible();

        log.info("Проверка соответствия текста");
        assertMainPage.checkTextMainTitle();

        log.info("Результат УСПЕШНО");
    }

    @DisplayName("Проверка кнопки в корзину")
    @Test
    @Tag("Page")
    public void checkButtonInCartFirstItem(){

        log.info("Проверка Видимости кнопки - в корзину");
        assertMainPage.checkButtonInCartFirstItemIsVisible();

        log.info("Проверка на соответствие тексту кнопки - в корзину");
        assertMainPage.checkTextButtonInCartFirstItem();

        log.info("Проверка нажатия на кнопку - в корзину");
        mainPage.clickButtonInCartFirstItem();

        log.info("Проверка отображения товара в корзине");
        mainPage.clickButtonInCart();

        SelenideElement list = $x("//*[@class=\"cart-item\"]");
        list.shouldBe(Condition.visible);
        log.info("Товар присутствует результат - УСПЕШНО");
    }

    @DisplayName("Проверка кнопки - +1 товар")
    @Test
    @Tag("Page")
    public void checkButtonPlusOneFirstItem(){
        log.info("Проверка кнопки - +1 товар");

        log.info("Проверка видимости");
        assertMainPage.buttonPlusOneFirstItemIsVilible();

        log.info("Проверка соответствия текста");
        assertMainPage.checkTextButtonPlusOneFirstItem();

        log.info("Кликаем на плюсик");
        mainPage.clickButtonPlusOneFirstItem();

        assertMainPage.checkCountItem();
        log.info("Результат успешно");

    }
}
