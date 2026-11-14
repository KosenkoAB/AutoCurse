import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;


import static com.codeborne.selenide.Selenide.$x;
import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class DopTestsSelenid {

    @BeforeAll
    static void logConfig() {
        Configurator.logsConfig();
    }

    public record Item(String name, Double price) {
    }

    //Для второго теста переменные цены и наименования
    String name = Configurator.CONFIG.productName();
    Double price = Double.valueOf(Configurator.CONFIG.productPrice());

    DopTestsSelenid.Item newItem = new DopTestsSelenid.Item(name, price);


    private RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(Configurator.CONFIG.baseUrl())
            .setAuth(basic(Configurator.CONFIG.adminLogin(), Configurator.CONFIG.adminPassword()))
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @BeforeEach
    void genData(){
        given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(newItem)
                .post("/goods/add");


    }

    @Test
    @Tag("sel")
    void addCart(){
        Selenide.open(Configurator.CONFIG.baseUrl());
        $x("(//button[@data-action=\"add-to-cart\"])[1]").click();
        $x("(//button[@data-action=\"add-to-cart\"])[2]").click();
        $x("(//button[@data-action=\"add-to-cart\"])[3]").click();

        //Пошел в корзину
        $x("//*[@class=\"btn btn-cart\"][@id='open-cart-btn']").click();

        //Оформил заказ
        $x("//button[text()=\"Оформить заказ\"]\n").click();

        //Проверка оформления
        $x("//h1[text()=\"Заказ успешно оформлен!\"]").shouldBe(Condition.visible);
        $x("//*[text()=\"Заказ принят в обработку!\"]").shouldBe(Condition.visible);

        Selenide.closeWebDriver();

    }


    @Test
    @Tag("sel")
    void checkSumAny (){
        Selenide.open(Configurator.CONFIG.baseUrl());
        //Добавил товары
        $x("(//button[@data-action=\"add-to-cart\"])[1]").click();
        $x("(//button[@data-action=\"add-to-cart\"])[2]").click();
        $x("(//button[@data-action=\"add-to-cart\"])[3]").click();

        //вытащил число из строки суммы
        SelenideElement sum = $x("(//*[@style=\"color:var(--primary); font-weight:bold\"])[1]");
        String summa = sum.getText().replaceAll("[^0-9]", "");;
        int summ = Integer.parseInt(summa);

        SelenideElement sum2 = $x("(//*[@style=\"color:var(--primary); font-weight:bold\"])[2]");
        String summa2 = sum2.getText().replaceAll("[^0-9]", "");;
        int summ2 = Integer.parseInt(summa2);

        SelenideElement sum3 = $x("(//*[@style=\"color:var(--primary); font-weight:bold\"])[3]");
        String summa3 = sum3.getText().replaceAll("[^0-9]", "");;
        int summ3 = Integer.parseInt(summa3);

        int resolution = summ+summ2+summ3;

        //перешел в корзину
        $x("//*[@class=\"btn btn-cart\"][@id='open-cart-btn']").click();

        //Взял сумму из модалки корзины
        SelenideElement sumFront = $x("//*[@id=\"total-price\"]");
        String sumF = sumFront.getText();
        int summF = Integer.parseInt(sumF);


        // проверил что равны
        assertThat(resolution)
                .as("Сумма товаров должна быть равно сумме в корзине")
                .isEqualTo(summF);

        Selenide.closeWebDriver();
    }


    @Test
    @Tag("sel")
    void addItem(){

        //То что будем добавлять
        String par = "Соленоид";

        Selenide.open(Configurator.CONFIG.baseUrl());

        //Авторизация
        $x("//*[@class = \"btn-outline\"]").click();
        $x("//*[@placeholder = \"Username\"]").sendKeys(Configurator.CONFIG.adminLogin());
        $x("//*[@placeholder = \"Password\"]").sendKeys(Configurator.CONFIG.adminPassword());
        $x("//*[@class = \"primary\"]").click();

        //Добавил товар
        $x("//*[@id=\"n-name\"][@placeholder=\"Название\"]").sendKeys(par);
        $x("//*[@id=\"n-price\"][@placeholder=\"Цена\"]").sendKeys("10550");
        $x("//*[@id=\"add-btn\"]").click();

        //Проверил что тост есть
        $x("//*[text()=\"Товар успешно добавлен!\"]").shouldBe(Condition.visible);

        //Нажал удалить добавленный товар
        $x("//div/table/tbody/tr[last()]/td[last()]/button[last()]").click();

        //Подтвердил алерт
        Alert cardDel = Selenide.switchTo().alert();
        cardDel.accept();

        //Вернулся на список товаров
        $x("//*[@href=\"/\"]").click();

        //Проверил что его нет
        SelenideElement list2 = $x("//div/div/h4[text()='"+par+"']");
        list2.shouldNotBe(Condition.visible);



        Selenide.closeWebDriver();
    }


    @Test
    @Tag("sel")
    void edit(){

        //То что будем добавлять
        String par = "Соленоид";

        Selenide.open(Configurator.CONFIG.baseUrl());
        //Авторизовался
        $x("//*[@class = \"btn-outline\"]").click();
        $x("//*[@placeholder = \"Username\"]").sendKeys(Configurator.CONFIG.adminLogin());
        $x("//*[@placeholder = \"Password\"]").sendKeys(Configurator.CONFIG.adminPassword());
        $x("//*[@class = \"primary\"]").click();

        //Добавил товар
        $x("//*[@id=\"n-name\"][@placeholder=\"Название\"]").sendKeys("кОшак");
        $x("//*[@id=\"n-price\"][@placeholder=\"Цена\"]").sendKeys("1000000");
        $x("//*[@id=\"add-btn\"]").click();

        //Очистил поле и отредачил
        $x("//*[@value=\"кОшак\"]").clear();
        $x("(//*[@style=\"width:150px\"])[last()]").append("Кошка");

        //Сохранил изменения
        $x("(//*[@data-action=\"update\"])[last()]").click();

        //Проверил тост и что в чем есть слово обновлен
        SelenideElement editter = $x("(//*[@class=\"toast\"])[last()]");
        editter.shouldBe(Condition.visible);
        editter.shouldHave(Condition.text("обновлен"));


        //Пошли проверять изменения в списке
        $x("//*[text()=\"Вернуться на сайт\"]").click();


        SelenideElement checker = $x("//*[text()=\"Кошка\"]");
        checker.shouldBe(Condition.visible);
        checker.shouldHave(Condition.text("Кошка"));


        $x("//*[@class = \"btn-outline\"]").click();


        //Нажал на удалить последний добавленный товар
        $x("(//*[@data-action=\"delete\"])[last()]").click();

        //Подтвердил алерт
        Alert cardDel = Selenide.switchTo().alert();
        cardDel.accept();

        //Проверил что товара нет в списке
        $x("//*[@value=\"Кошка\"]").shouldNotBe(Condition.visible);

        Selenide.closeWebDriver();
    }




    @AfterEach
    void delGenData(){

        Response response = given()
                .spec(spec)
                .queryParam("page", 0)
                .queryParam("size", 10)
                .contentType(ContentType.JSON)
                .get("/goods/list");

        int beerId = response.jsonPath().getInt("goods.find { it.name == '" + name + "' }.id");

        given()
                .spec(spec)
                .pathParam("id", beerId)
                .when()
                .delete("/goods/{id}")
                .then()
                .log().all()
                .statusCode(200);

    }



}
