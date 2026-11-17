import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import groovy.util.logging.Slf4j;
import object.AuthPage;
import object.ConfiguratorMain;
import object.PageForFourTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;



@Slf4j
public class AssertForFourTest {

    private static final Logger log = LoggerFactory.getLogger(AssertForFourTest.class);


    String expTextTitleOrder = "Заказ успешно оформлен!";
    String expTextTostOrder = "Заказ принят в обработку!";
    String expTextTostAddItem = "Товар успешно добавлен!";
    String expTextTostSaveChanges = "обновлен";





    @BeforeEach
    void init(){
        Selenide.open(ConfiguratorMain.config.baseUrl());
    }

    @AfterEach
    void out(){
        Selenide.closeWebDriver();
    }

    PageForFourTest pageForFourTest = new PageForFourTest();
    AssertAuthPage assertAuthPage = new AssertAuthPage();
    AuthPage authPage = new AuthPage();



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
        log.info("Проверка видимости главного текста - "+expTextTitleOrder);
        assertThat(pageForFourTest.isVisibleTextTitleMainOrder())
                .as("Текст уведомления на главной странице не виден")
                .isTrue();

        log.info("Проверка видимости тоста после оформления");
        assertThat(pageForFourTest.tostOrderIsVisible())
                .as("Тест тоста отсутсвует или сам тост не видел")
                .isTrue();

        log.info("Проверка текста Главной страницы");
        assertThat(pageForFourTest.getTextTitleMainOrder())
                .as("Текст не соотвествует ожидаемому - "+expTextTitleOrder+" или сам элемент отсутствует")
                .contains(expTextTitleOrder);

        log.info("Проверка текста тоста");
        assertThat(pageForFourTest.getTextTostOrder())
                .as("Текст не соотвествует ожидаемому - "+expTextTostOrder+" или сам элемент отсутствует")
                .contains(expTextTostOrder);

        log.info("РЕЗУЛЬТАТ ТЕСТА --- УСПЕШНО");
    }



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
        assertThat(pageForFourTest.summFirstItemIsVisible())
                .as("Сумма первого товара не видна")
                .isTrue();

        assertThat(pageForFourTest.summSecondItemIsVisible())
                .as("Сумма второго товара не видна")
                .isTrue();

        assertThat(pageForFourTest.summThirdItemIsVisible())
                .as("Сумма третьего товара не видна")
                .isTrue();


        int resolution = pageForFourTest.getCountFirstItem() + pageForFourTest.getCountSecondItem() + pageForFourTest.getCountThirdItem();
        log.info("Посчитали сумму трех товаров = "+resolution);

        log.info("Перешли в корзину");
        pageForFourTest.clickButtonInCart();

        log.info("Проверили что итоговая сумма видна");
        assertThat(pageForFourTest.summAllItemIsVisible())
                .as("Сумма всех товаров отсуствует")
                .isTrue();


        log.info("Проверка суммы товаров из гл.страницы равна сумме в корзине");
        assertThat(resolution)
                .as("Сумма товаров должна быть равно сумме в корзине")
                .isEqualTo(pageForFourTest.getCountAllItem());
        log.info("РЕЗУЛЬТАТ ТЕСТА --- УСПЕШНО");

    }

    @Test
    @Tag("Page")
    void addItem(){
        log.info("Инициализация теста 'Добавление товара через админку'");

        log.info("Логинимся");
        assertAuthPage.successLoginIn();
        log.info("Авторизация прошла успешно");

        log.info("Выполняются проверки по наличию полей ввода товара и кнопки добавить");
        assertThat(pageForFourTest.fieldNameItemIsVisible())
                .as("Поле ввода наименования товара отсутствует")
                .isTrue();

        assertThat(pageForFourTest.fieldPriceItemIsVisible())
                .as("Поле ввода цены товара отсутствует")
                .isTrue();

        assertThat(pageForFourTest.buttonAddItemIsVisible())
                .as("Кнопка добавить товар отсуствует")
                .isTrue();
        log.info("Все элементы присутствуют");

        log.info("Добавляем новый товар");
        pageForFourTest.inputNameItem();
        pageForFourTest.inputPriceItem();
        pageForFourTest.clickButtonAddItem();

        log.info("Выполяются проверки по тосту и кнопке удалить");
        sleep(1500);
        assertThat(pageForFourTest.tostAddItemIsVisible())
                .as("Тост о добавлении нового товара отсутствует")
                .isTrue();

        sleep(1500);
        assertThat(pageForFourTest.getTextTostAddItem())
                .as("Тест в тосте не соответствует ожидаемому")
                .contains(expTextTostAddItem);

        sleep(1500);
        assertThat(pageForFourTest.buttonDeleteLastItemIsVisible())
                .as("Кнопка удалить последний товар отсутствует")
                .isTrue();

        log.info("Кликаем на удалить последний товар");
        pageForFourTest.clickButtonDeleteLastItem();

        //Подтвердил алерт
        Alert cardDel = Selenide.switchTo().alert();
        cardDel.accept();
        log.info("Подтвердил алерт при удалении");

        log.info("Проверка наличия Гиппер-ссылки вернуться на главную страницу");
        assertThat(pageForFourTest.buttonBackToMainPageIsVisible())
                .as("Гиппер-ссылка вернуться на главную страницу отсутствует")
                .isTrue();
        log.info("Гиппер-ссылка присутствует");

        log.info("Кликаем на гипер-ссылку");
        pageForFourTest.clickButtonBackToMainPage();

        log.info("Проверка отсутствия товара");
        //Проверил что его нет
        SelenideElement list2 = $x("//div/div/h4[text()='"+ConfiguratorMain.config.productName()+"']");
        list2.shouldNotBe(Condition.visible);
        log.info("РЕЗУЛЬТАТ ТЕСТА --- УСПЕШНО");
    }


    @Test
    @Tag("Page")
    void editItem(){
        log.info("Инициализация теста 'Редактирование товара через админку'");

        log.info("Логинимся");
        assertAuthPage.successLoginIn();

        log.info("Выполняются проверки по наличию полей ввода товара и кнопки добавить");
        assertThat(pageForFourTest.fieldNameItemIsVisible())
                .as("Поле ввода наименования товара отсутствует")
                .isTrue();

        assertThat(pageForFourTest.fieldPriceItemIsVisible())
                .as("Поле ввода цены товара отсутствует")
                .isTrue();

        assertThat(pageForFourTest.buttonAddItemIsVisible())
                .as("Кнопка добавить товар отсуствует")
                .isTrue();
        log.info("Поля присутствуют и кнопка тоже");

        log.info("Добавляем новый товар");
        pageForFourTest.inputNameItem();
        pageForFourTest.inputPriceItem();
        pageForFourTest.clickButtonAddItem();

        log.info("Выполяются проверки по тосту и кнопке удалить");
        sleep(1500);
        assertThat(pageForFourTest.tostAddItemIsVisible())
                .as("Тост о добавлении нового товара отсутствует")
                .isTrue();

        sleep(1500);
        assertThat(pageForFourTest.getTextTostAddItem())
                .as("Тест в тосте не соответствует ожидаемому")
                .contains(expTextTostAddItem);


        sleep(1500);
        assertThat(pageForFourTest.fieldNameItemLastIsVisible())
                .as("Поле Наименование последнего товара отсутствует")
                .isTrue();
        log.info("Проверки выполнены");

        log.info("Чистим поле наименование");
        pageForFourTest.clearFieldNameItemLastIsVisible();
        log.info("Вводим новое наименование товара");
        pageForFourTest.editFieldNameItemLast();

        log.info("Проверка кнопки сохранить");
        assertThat(pageForFourTest.buttonSaveChangeIsVisible())
                .as("Кнопка Сохранить на последнем товаре отсутствует")
                .isTrue();

        log.info("Кликаем на сохранить");
        pageForFourTest.clickButtonSaveChange();



        log.info("Проверка тоста о сохранении изменений");
        sleep(1500);
        assertThat(pageForFourTest.tostSaveChangeIsVisible())
                .as("Тост об изменений о товаре отсуствует")
                .isTrue();

        sleep(1500);
        assertThat(pageForFourTest.getTextTostSaveChange())
                .as("Текст не соответствует ожидаемому - "+expTextTostSaveChanges)
                .contains(expTextTostSaveChanges);

        log.info("Возвращаемся на главную страницу");
        pageForFourTest.clickButtonBackToMainPage();


        log.info("Проверка что изменения есть на главной странице");
        SelenideElement checker = $x("//*[text()=\""+ConfiguratorMain.config.productNameEdit()+"\"]");
        checker.shouldBe(Condition.visible);
        checker.shouldHave(Condition.text(ConfiguratorMain.config.productNameEdit()));


        log.info("Клик на Администрирование");
        authPage.clickAdminButton();

        log.info("Кликаем на удалить товар");
        pageForFourTest.clickButtonDeleteLastItem();


        log.info("Подтверждаем алерт");
        Alert cardDel = Selenide.switchTo().alert();
        cardDel.accept();
        log.info("Проверка что товара нет в списке");

        $x("//*[@value=\""+ConfiguratorMain.config.productNameEdit()+"\"]").shouldNotBe(Condition.visible);
        log.info("РЕЗУЛЬТАТ ТЕСТА --- УСПЕШНО");
    }
}
