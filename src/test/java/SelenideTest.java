import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Selenide.$x;

public class SelenideTest {

    SelenideElement admin = $x("//*[@class = \"btn-outline\"]");
    SelenideElement login = $x("//*[@placeholder = \"Username\"]");
    SelenideElement password = $x("//*[@placeholder = \"Password\"]");
    SelenideElement signIn = $x("//*[@class = \"primary\"]");

    @BeforeAll
    static void logConfig() {
        Configurator.logsConfig();
    }

    @BeforeEach
    void init(){
        Selenide.open(Configurator.CONFIG.baseUrl());
    }

    @Test
    @Tag("sel")
    void addCheck()  {

        //То что будем добавлять
        String par = "Соленоид";

        //Авторизовался
        admin.click();
        login.sendKeys(Configurator.CONFIG.adminLogin());
        password.sendKeys(Configurator.CONFIG.adminPassword());
        signIn.click();

        //Проверка что в админке
        $x("//*[@id=\"add-btn\"]").should(Condition.visible);

        //Добавил товар
        $x("//*[@id=\"n-name\"][@placeholder=\"Название\"]").sendKeys(par);
        $x("//*[@id=\"n-price\"][@placeholder=\"Цена\"]").sendKeys("10550");
        $x("//*[@id=\"add-btn\"]").click();

        //Вернулся на список товаров
        $x("//*[@href=\"/\"]").click();

        //Ищем добавленный товар
        SelenideElement list = $x("//div/div/h4[text()='"+par+"']");
        list.shouldBe(Condition.visible);

        //Вернулись в админку и клик на удалить
        admin.click();
        $x("//div/table/tbody/tr[last()]/td[last()]/button[last()]").click();

        //Подтвердил алерт
        Alert cardDel = Selenide.switchTo().alert();
        cardDel.accept();

        //Вернулся на список товаров
        $x("//*[@href=\"/\"]").click();

        //Проверил что его нет
        SelenideElement list2 = $x("//div/div/h4[text()='"+par+"']");
        list2.shouldNotBe(Condition.visible);
    }


    @Test
    @Tag("sel")
    void addCard(){

        //Добавил в корзину
        $x("//*[@data-action=\"add-to-cart\"]").click();
        //Пошел в корзину
        $x("//*[@class=\"btn btn-cart\"][@id='open-cart-btn']").click();

        SelenideElement list = $x("//*[@class=\"cart-item\"]");
        list.shouldBe(Condition.visible);


    }


    @Test
    @Tag("sel")
    void invalidLoggining(){
        admin.click();
        $x("//*[@placeholder = \"Username\"]").sendKeys("adin");
        $x("//*[@placeholder = \"Password\"]").sendKeys("s123");
        $x("//*[@class = \"primary\"]").click();

        SelenideElement list = $x("//*[@class=\"alert alert-danger\"]");
        list.shouldBe(Condition.visible);
    }


    @Test
    @Tag("sel")
    void addAndRefresh(){
        //Добавил в корзину
        $x("//*[@data-action=\"add-to-cart\"]").click();
        //Пошел в корзину
        $x("//*[@class=\"btn btn-cart\"][@id='open-cart-btn']").click();
        Selenide.refresh();
        $x("//*[@class=\"btn btn-cart\"][@id='open-cart-btn']").click();

        SelenideElement list = $x("//*[@id=\"total-price\"]");
        list.shouldBe(Condition.visible);
        System.out.println("ЭТО БАГ!!! проблема известна, приносим извинения за неудобства Уже завел дефект в Сфере");

    }

    @Test
    @Tag("sel")
    void checkAlert(){
        //Добавил в корзину
        $x("//*[@data-action=\"add-to-cart\"]").click();
        $x("//*[@data-action=\"add-to-cart\"]").click();
        $x("//*[@data-action=\"add-to-cart\"]").click();
        $x("//*[@data-action=\"add-to-cart\"]").click();
        $x("//*[@data-action=\"add-to-cart\"]").click();
        $x("//*[@data-action=\"add-to-cart\"]").click();
        $x("//*[@data-action=\"add-to-cart\"]").click();
        $x("//*[@data-action=\"add-to-cart\"]").click();
        $x("//*[@data-action=\"add-to-cart\"]").click();
        $x("//*[@data-action=\"add-to-cart\"]").click();

        //Пошел в корзину
        $x("//*[@class=\"btn btn-cart\"][@id='open-cart-btn']").click();

        //Оформил заказ
        $x("//*[@id=\"makeOrder\"]").click();

        //Подтвердил алерт
        Alert cardDel = Selenide.switchTo().alert();

        String kiking = cardDel.getText();

        assert kiking.contains("Денег не хватает");
    }




    
    @AfterEach
    void out(){
        Selenide.closeWebDriver();
    }

}
