import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import groovy.util.logging.Slf4j;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import object.AuthPage;
import object.ConfiguratorMain;
import object.PageForFourTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.codeborne.selenide.Selenide.sleep;




@Slf4j
public class ChecksForFourTest {

    private static final Logger log = LoggerFactory.getLogger(ChecksForFourTest.class);

    AssertsBasic.AssertForFourTest assertForFourTest = new AssertsBasic.AssertForFourTest();



    @BeforeAll
    public static void initAllureSelenide(){
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @Step("Открытие Браузера и открытие главной страницы")
    @BeforeEach
    void init(){
        Selenide.open(ConfiguratorMain.config.baseUrl());
    }

    @Step("Закрытие Браузера ")
    @AfterEach
    void out(){
        Selenide.closeWebDriver();
    }

    PageForFourTest pageForFourTest = new PageForFourTest();
    ChecksAuthPage assertAuthPage = new ChecksAuthPage();
    AuthPage authPage = new AuthPage();


    @DisplayName("Тест-кейс оформление заказа тремя товарами")
    @Test
    @Tag("Page")
    void addCart(){
        log.info("Инициализация теста 'Добавить 3 товара'");
        //Добавил первые 3 товара в корзину
        pageForFourTest.clickButtonAddToCartFirstItem();
        pageForFourTest.clickButtonAddToCartSecondItem();
        pageForFourTest.clickButtonAddToCartThirdItem();
        log.info("3 товара добавлены");

        log.info("Кликаем в корзину");
        //Пошел в корзину
        pageForFourTest.clickButtonInCart();

        log.info("Кликаем оформить заказ");
        //Оформил заказ
        pageForFourTest.clickButtonReleaseOrder();


        log.info("Проверяем элементы после нажатия оформить");
        //Проверка оформления
        log.info("Проверка видимости главного текста");
        assertForFourTest.checkIsVisibleTextTitleMainOrder();

        assertForFourTest.checkTextMainTitle();


        log.info("Проверка видимости тоста после оформления");
        assertForFourTest.checkTostOrderIsVisible();

        log.info("Проверка текста Главной страницы");
        assertForFourTest.checkTextTitleMainOrder();

        log.info("Проверка текста тоста");
        assertForFourTest.checkTextTostOrder();

        log.info("РЕЗУЛЬТАТ ТЕСТА --- УСПЕШНО");
    }


    @DisplayName("Тест-кейс проверка суммы чисел товаров на странице с суммой товаров в корзине")
    @Test
    @Tag("Page")
    public void checkSumAny(){
        log.info("Инициализация теста 'Проверить суммы трех товаров'");
        //Добавил первые 3 товара в корзину
        pageForFourTest.clickButtonAddToCartFirstItem();
        pageForFourTest.clickButtonAddToCartSecondItem();
        pageForFourTest.clickButtonAddToCartThirdItem();
        log.info("3 товара добавлены");

        log.info("Проверка отображения сумм трех товаров");
        assertForFourTest.checkSummFirstItemIsVisible();
        assertForFourTest.checkSummSecondItemIsVisible();
        assertForFourTest.checkSummThirdItemIsVisible();

        log.info("Перешли в корзину");
        pageForFourTest.clickButtonInCart();

        log.info("Проверили что итоговая сумма видна");
        assertForFourTest.checkSummAllItemIsVisible();

        log.info("Проверка суммы товаров из гл.страницы равна сумме в корзине");
        assertForFourTest.checkCountSum();
        log.info("РЕЗУЛЬТАТ ТЕСТА --- УСПЕШНО");

    }

    @DisplayName("Тест-кейс добавление товара через админку")
    @Test
    @Tag("Page")
    void addItem(){
        log.info("Инициализация теста 'Добавление товара через админку'");

        log.info("Логинимся");
        assertAuthPage.successLoginIn();
        log.info("Авторизация прошла успешно");

        log.info("Выполняются проверки по наличию полей ввода товара и кнопки добавить");
        assertForFourTest.checkFieldNameItemIsVisible();

        assertForFourTest.checkFieldPriceItemIsVisible();

        assertForFourTest.checkButtonAddItemIsVisible();
        log.info("Все элементы присутствуют");

        log.info("Добавляем новый товар");
        pageForFourTest.inputNameItem();
        pageForFourTest.inputPriceItem();
        pageForFourTest.clickButtonAddItem();

        log.info("Выполяются проверки по тосту и кнопке удалить");
        sleep(1500);
        assertForFourTest.checkTostAddItemIsVisible();

        sleep(1500);
        assertForFourTest.checkTextTostAddItem();

        sleep(1500);
        assertForFourTest.checkButtonDeleteLastItemIsVisible();

        log.info("Кликаем на удалить последний товар");
        pageForFourTest.clickButtonDeleteLastItem();

        //Подтвердил алерт
        assertForFourTest.skipAlert();
        log.info("Подтвердил алерт при удалении");

        log.info("Проверка наличия Гиппер-ссылки вернуться на главную страницу");
        assertForFourTest.checkButtonBackToMainPageIsVisible();
        log.info("Гиппер-ссылка присутствует");

        log.info("Кликаем на гипер-ссылку");
        pageForFourTest.clickButtonBackToMainPage();

        log.info("Проверка отсутствия товара");
        //Проверил что его нет
        assertForFourTest.itemNotVisible();
        log.info("РЕЗУЛЬТАТ ТЕСТА --- УСПЕШНО");
    }

    @DisplayName("Тест-кейс редактирование ранее добавленного товара в админке")
    @Test
    @Tag("Page")
    void editItem(){
        log.info("Инициализация теста 'Редактирование товара через админку'");

        log.info("Логинимся");
        assertAuthPage.successLoginIn();

        log.info("Выполняются проверки по наличию полей ввода товара и кнопки добавить");
        assertForFourTest.checkFieldNameItemIsVisible();
        assertForFourTest.checkFieldPriceItemIsVisible();
        assertForFourTest.checkButtonAddItemIsVisible();
        log.info("Поля присутствуют и кнопка тоже");

        log.info("Добавляем новый товар");
        pageForFourTest.inputNameItem();
        pageForFourTest.inputPriceItem();
        pageForFourTest.clickButtonAddItem();

        log.info("Выполяются проверки по тосту и кнопке удалить");
        sleep(1500);
        assertForFourTest.checkTostAddItemIsVisible();

        sleep(1500);
        assertForFourTest.checkTextTostAddItem();

        sleep(1500);
        assertForFourTest.checkFieldNameItemLastIsVisible();
        log.info("Проверки выполнены");

        log.info("Чистим поле наименование");
        pageForFourTest.clearFieldNameItemLastIsVisible();
        log.info("Вводим новое наименование товара");
        pageForFourTest.editFieldNameItemLast();

        log.info("Проверка кнопки сохранить");
        assertForFourTest.checkButtonSaveChangeIsVisible();

        log.info("Кликаем на сохранить");
        pageForFourTest.clickButtonSaveChange();

        log.info("Проверка тоста о сохранении изменений");
        sleep(1500);
        assertForFourTest.checkTostSaveChangeIsVisible();

        sleep(1500);
        assertForFourTest.checkTextTostSaveChange();

        log.info("Возвращаемся на главную страницу");
        pageForFourTest.clickButtonBackToMainPage();

        log.info("Проверка что изменения есть на главной странице");
        assertForFourTest.checkChange();

        log.info("Клик на Администрирование");
        authPage.clickAdminButton();

        log.info("Кликаем на удалить товар");
        pageForFourTest.clickButtonDeleteLastItem();

        log.info("Подтверждаем алерт");
        Alert cardDel = Selenide.switchTo().alert();
        cardDel.accept();
        log.info("Проверка что товара нет в списке");

        assertForFourTest.checkNotVisibleEditedItem();
        log.info("РЕЗУЛЬТАТ ТЕСТА --- УСПЕШНО");
    }
}
