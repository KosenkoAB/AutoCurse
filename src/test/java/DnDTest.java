import com.codeborne.selenide.Condition;
import com.codeborne.selenide.DragAndDropOptions;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class DnDTest {

    @BeforeEach
    void init(){
        Selenide.open("http://localhost:8080");
    }


   //один тест за два
    @Test
    @Tag("DnD")
    void makeDnD(){
        //Выполнил драг дроп
        $x("(//*[@data-name][@draggable])[1]").dragAndDrop(DragAndDropOptions.to($x("//*[@class=\"btn btn-cart\"][@id='open-cart-btn']")));
        //Перешел в козину
        $x("//*[@class=\"btn btn-cart\"][@id='open-cart-btn']").click();
        //Проверил наличие товара по кнопке удалить товар, т.к. не привязывался к конкретному товару
        //делаю драг дроп первого товара
        $x("//*[@data-action=\"remove\"]").shouldBe(Condition.visible);
        //Нажал на кнопку удалить товар
        $x("//*[@data-action=\"remove\"]").click();
        //проверил что его нет по наличию элемента с текстом пусто
        $x("//*[@id=\"empty-cart\"]").shouldBe(Condition.visible);
    }

    @Test
    @Tag("DnD")
    void dNd(){
        sleep(1000);
        $x("(//*[@data-name][@draggable])[1]").dragAndDrop(DragAndDropOptions.to($x("//*[@class=\"btn btn-cart\"][@id='open-cart-btn']")));
        sleep(2000);
    }



    @AfterEach
    void out(){
        Selenide.closeWebDriver();
    }
}
