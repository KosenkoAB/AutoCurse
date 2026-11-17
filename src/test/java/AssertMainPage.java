import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import com.codeborne.selenide.SelenideElement;
import groovy.util.logging.Slf4j;
import object.ConfiguratorMain;
import object.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
public class AssertMainPage {

    String expectedTextAdminButton = "Администрирование";
    String expectedTextButtonInCart = "Корзина";
    String expInsideCart = "Корзина SmartShop";
    String expectedTextMainTitle = "SmartShop";
    String expectedTextInCartFirstItem = "В корзину";
    String expectedTextPlusOneFirstItem = "+";
    String expPlusOne = $x("(//*[@class=\"qty-input\"])[1]").getValue();

    private static final Logger log = LoggerFactory.getLogger(AssertMainPage.class);

    MainPage mainPage = new MainPage();


    @BeforeEach
    void init(){
        Selenide.open(ConfiguratorMain.config.baseUrl());
    }

    @AfterEach
    void out(){
        Selenide.closeWebDriver();
    }


    @Test
    @Tag("Page")
    public void checkAdminButton(){
        log.info("Проверка кнопки - Администрирование");

        assertThat(mainPage.getTextAdminButton())
                .as("Текст не соотвествует - "+expectedTextAdminButton+" или элемент не обнаружен")
                .contains(expectedTextAdminButton);

        assertThat(mainPage.adminButtonIsVisible())
                .as("Кнопка Администрирование не видна")
                .isTrue();

        log.info("Нажал на Администрирование");
        mainPage.clickAdminButton();

        log.info("Проверка перехода на форму авторизации");
        $x("//button[@type=\"submit\"]").shouldBe(Condition.visible);
        log.info("Результат - УСПЕШНО");
    }


    @Test
    @Tag("Page")
    public void checkButtonInCart(){
        log.info("Проверка кнопки - Корзина");

        assertThat(mainPage.buttonInCartIsVisible())
                .as("Кнопка не найдена")
                .isTrue();

        assertThat(mainPage.getTextButtonInCart())
                .as("Текст кнопки не соответствует ожидаемому -"+expectedTextButtonInCart+" или не найдена")
                .contains(expectedTextButtonInCart);

        log.info("Жмем Корзина");
        mainPage.clickButtonInCart();

        log.info("Проверка перехода в Корзину");
        $x("//h2[text()=\""+expInsideCart+"\"]").shouldBe(Condition.visible);
        log.info("Результат УСПЕШНО");
    }


    @Test
    @Tag("Page")
    public void checkMainTitle(){
        log.info("Проверка видимости наименования магаза");

        assertThat(mainPage.textMainTitleIsVisible())
                .as("Наименование отсутствует")
                .isTrue();

        log.info("Проверка соответствия текста");
        assertThat(mainPage.getTextMainTitle())
                .as("Элемент отсутствует или не соответствует ожидаемому тексту - "+expectedTextMainTitle)
                .contains(expectedTextMainTitle);
        log.info("Результат УСПЕШНО");
    }


    @Test
    @Tag("Page")
    public void checkButtonInCartFirstItem(){
        log.info("Проверка кнопки - в корзину");

        log.info("Проверка Видимости кнопки - в корзину");
        assertThat(mainPage.textButtonInCartFirstItem())
                .as("Кнопка не найдена")
                .isTrue();
        log.info("Проверка на соответствие тексту кнопки - в корзину");
        assertThat(mainPage.checkButtonInCartFirstItem())
                .as("Элемент отсутствует или не соответствует ожидаемому тексту - "+expectedTextInCartFirstItem)
                .contains(expectedTextInCartFirstItem);

        log.info("Проверка нажатия на кнопку - в корзину");
        mainPage.clickButtonInCartFirstItem();

        log.info("Проверка отображения товара в корзине");
        mainPage.clickButtonInCart();

        SelenideElement list = $x("//*[@class=\"cart-item\"]");
        list.shouldBe(Condition.visible);
        log.info("Товар присутствует результат - УСПЕШНО");
    }


    @Test
    @Tag("Page")
    public void checkButtonPlusOneFirstItem(){
        log.info("Проверка кнопки - +1 товар");

        log.info("Проверка видимости");
        assertThat(mainPage.textButtonPlusOneFirstItemIsVilible())
                .as("Элемент не виден или отсутствует")
                .isTrue();

        log.info("Проверка соответствия текста");
        assertThat(mainPage.getTextButtonPlusOneFirstItem())
                .as("Текст элемента не соответствует ожидаемому - "+expectedTextPlusOneFirstItem+" или не найден")
                .contains(expectedTextPlusOneFirstItem);

        log.info("Кликаем на плюсик");
        mainPage.clickButtonPlusOneFirstItem();

        assertThat(expPlusOne)
                .as("ЭТО БАГ!!! проблема известна, приносим извинения за неудобства Уже завел дефект в Сфере")
                .isEqualTo("2");
    }
}
