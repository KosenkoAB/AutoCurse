package AssertsBasic;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import object.AuthPage;
import object.ConfiguratorMain;
import object.PageForFourTest;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;


public class AssertForFourTest {

    String expTextTitleOrder = "Заказ успешно оформлен!";
    String expTextTostOrder = "Заказ принят в обработку!";
    String expTextTostAddItem = "Товар успешно добавлен!";
    String expTextTostSaveChanges = "обновлен";


    PageForFourTest pageForFourTest = new PageForFourTest();
    AssertAuthPage assertAuthPage = new AssertAuthPage();
    AuthPage authPage = new AuthPage();


    @Step("Проверка соответствия тексте главной страницы")
    public void checkTextMainTitle(){
        assertThat(pageForFourTest.isVisibleTextTitleMainOrder())
                .as("Текст уведомления на главной странице не виден")
                .isTrue();
    }

    @Step("Проверка видимости текста на главной странице")
    public void checkIsVisibleTextTitleMainOrder(){
        assertThat(pageForFourTest.isVisibleTextTitleMainOrder())
                .as("Текст на главной странице не виден")
                .isTrue();
    }

    @Step("Проверка видимости тоста после оформления")
    public void checkTostOrderIsVisible(){
        assertThat(pageForFourTest.tostOrderIsVisible())
                .as("Тест тоста отсутствует или сам тост не видел")
                .isTrue();
    }

    @Step("Проверка текста Главной страницы")
    public void checkTextTitleMainOrder(){
        assertThat(pageForFourTest.getTextTitleMainOrder())
                .as("Текст не соотвествует ожидаемому - "+expTextTitleOrder+" или сам элемент отсутствует")
                .contains(expTextTitleOrder);
    }

    @Step("Проверка текста тоста")
    public void checkTextTostOrder(){
        assertThat(pageForFourTest.getTextTostOrder())
                .as("Текст не соотвествует ожидаемому - "+expTextTostOrder+" или сам элемент отсутствует")
                .contains(expTextTostOrder);
    }


    @Step("Проверка отображения суммы 1 товара")
    public void checkSummFirstItemIsVisible(){
        assertThat(pageForFourTest.summFirstItemIsVisible())
                .as("Сумма первого товара не видна")
                .isTrue();
    }

    @Step("Проверка отображения суммы 2 товара")
    public void checkSummSecondItemIsVisible(){
        assertThat(pageForFourTest.summSecondItemIsVisible())
                .as("Сумма второго товара не видна")
                .isTrue();
    }

    @Step("Проверка отображения суммы 3 товара")
    public void checkSummThirdItemIsVisible(){
        assertThat(pageForFourTest.summThirdItemIsVisible())
                .as("Сумма третьего товара не видна")
                .isTrue();
    }

    @Step("Считаем сумму 3 товаров с карточек")
    public int countSum(){
        return pageForFourTest.getCountFirstItem()
                +pageForFourTest.getCountSecondItem()
                +pageForFourTest.getCountThirdItem();
    }

    @Step("Проверка отображения суммы товаров в корзине")
    public void checkSummAllItemIsVisible(){
        assertThat(pageForFourTest.summAllItemIsVisible())
                .as("Сумма всех товаров отсутствует")
                .isTrue();
    }

    @Step("Проверка сумм товаров из карточек и итоговой в корзине")
    public void checkCountSum(){
        assertThat(countSum())
                .as("Сумма товаров должна быть равно сумме в корзине")
                .isEqualTo(pageForFourTest.getCountAllItem());
    }

    @Step("Проверка наличия поля наименование товара")
    public void checkFieldNameItemIsVisible(){
        assertThat(pageForFourTest.fieldNameItemIsVisible())
                .as("Поле ввода наименования товара отсутствует")
                .isTrue();
    }

    @Step("Проверка наличия поля цены товара")
    public void checkFieldPriceItemIsVisible(){
        assertThat(pageForFourTest.fieldPriceItemIsVisible())
                .as("Поле ввода цены товара отсутствует")
                .isTrue();
    }

    @Step("Проверка наличия кнопки добавить")
    public void checkButtonAddItemIsVisible(){
        assertThat(pageForFourTest.buttonAddItemIsVisible())
                .as("Кнопка добавить товар отсуствует")
                .isTrue();
    }

    @Step("Проверка видимости тоста о добавлении нового товара")
    public void checkTostAddItemIsVisible(){
        assertThat(pageForFourTest.tostAddItemIsVisible())
                .as("Тост о добавлении нового товара отсутствует")
                .isTrue();
    }

    @Step("Проверка текста тоста о добавлении нового товара")
    public void checkTextTostAddItem(){
        assertThat(pageForFourTest.getTextTostAddItem())
                .as("Тест в тосте не соответствует ожидаемому")
                .contains(expTextTostAddItem);
    }

    @Step("Проверка отображения кнопки удалить у последнего товара")
    public void checkButtonDeleteLastItemIsVisible(){
        assertThat(pageForFourTest.buttonDeleteLastItemIsVisible())
                .as("Кнопка удалить последний товар отсутствует")
                .isTrue();
    }

    @Step("Подтверждение алерта")
    public void skipAlert(){
        Alert cardDel = Selenide.switchTo().alert();
        cardDel.accept();
    }

    @Step("Проверка видимости гипер-ссылки вернуться на главную страницу")
    public void checkButtonBackToMainPageIsVisible(){
        assertThat(pageForFourTest.buttonBackToMainPageIsVisible())
                .as("Гиппер-ссылка вернуться на главную страницу отсутствует")
                .isTrue();
    }

    @Step("Проверка отсутствия товара")
    public void itemNotVisible(){
        SelenideElement list2 = $x("//div/div/h4[text()='"+ ConfiguratorMain.config.productName()+"']");
        list2.shouldNotBe(Condition.visible);
    }

    @Step("Проверка видимости поля наименования последнего товара")
    public void checkFieldNameItemLastIsVisible(){
        assertThat(pageForFourTest.fieldNameItemLastIsVisible())
                .as("Поле Наименование последнего товара отсутствует")
                .isTrue();
    }

    @Step("Проверка отображения кнопки сохранить")
    public void checkButtonSaveChangeIsVisible(){
        assertThat(pageForFourTest.buttonSaveChangeIsVisible())
                .as("Кнопка Сохранить на последнем товаре отсутствует")
                .isTrue();
    }

    @Step("Проверка видимости тоста о сохранении изменений")
    public void checkTostSaveChangeIsVisible(){
        assertThat(pageForFourTest.tostSaveChangeIsVisible())
                .as("Тост об изменений о товаре отсуствует")
                .isTrue();
    }

    @Step("Проверка текста тоста о сохранении изменений")
    public void checkTextTostSaveChange(){
        assertThat(pageForFourTest.getTextTostSaveChange())
                .as("Текст не соответствует ожидаемому - "+expTextTostSaveChanges)
                .contains(expTextTostSaveChanges);
    }

    @Step("Проверка видимости изменений на главной странице")
    public void checkChange(){
        SelenideElement checker = $x("//*[text()=\""+ConfiguratorMain.config.productNameEdit()+"\"]");
        checker.shouldBe(Condition.visible);
        checker.shouldHave(Condition.text(ConfiguratorMain.config.productNameEdit()));
    }

    @Step("Проверка отсутствия отредактированного товара")
    public void checkNotVisibleEditedItem(){
        $x("//*[@value=\""
                +ConfiguratorMain.config.productNameEdit()+
                "\"]").shouldNotBe(Condition.visible);
    }

}
