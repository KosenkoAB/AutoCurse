package AssertsBasic;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import object.MainPage;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class AssertMainPage {

    String expectedTextAdminButton = "Администрирование";
    String expectedTextButtonInCart = "Корзина";
    String expInsideCart = "Корзина SmartShop";
    String expectedTextMainTitle = "SmartShop";
    String expectedTextInCartFirstItem = "В корзину";
    String expectedTextPlusOneFirstItem = "+";



    MainPage mainPage = new MainPage();


    @Step("Проверка соответствия текста кнопки Администрирование")
    public void checkTextAdminButton(){
        assertThat(mainPage.getTextAdminButton())
                .as("Текст не соотвествует - "+expectedTextAdminButton+" или элемент не обнаружен")
                .contains(expectedTextAdminButton);
    }

    @Step("Проверка видимости кнопки Администрирование")
    public void checkAdminButtonIsVisible(){
        assertThat(mainPage.adminButtonIsVisible())
                .as("Кнопка Администрирование не видна")
                .isTrue();
    }

    @Step("Проверка видимости кнопки корзина")
    public void checkButtonInCartIsVisible(){
        assertThat(mainPage.buttonInCartIsVisible())
                .as("Кнопка не найдена")
                .isTrue();
    }

    @Step("Проверка соответствия текста кнопки Корзина")
    public void checkTextButtonInCart(){
        assertThat(mainPage.getTextButtonInCart())
                .as("Текст кнопки не соответствует ожидаемому -"+expectedTextButtonInCart+" или не найдена")
                .contains(expectedTextButtonInCart);
    }

    @Step("Проверка видимости текста главной страницы")
    public void checkTextMainTitleIsVisible(){
        assertThat(mainPage.textMainTitleIsVisible())
                .as("Наименование отсутствует")
                .isTrue();
    }

    @Step("Проверка соответсвия текста главной страницы")
    public void checkTextMainTitle(){
        assertThat(mainPage.getTextMainTitle())
                .as("Элемент отсутствует или не соответствует ожидаемому тексту - "+expectedTextMainTitle)
                .contains(expectedTextMainTitle);
    }

    @Step("Проверка видимости кнопки в корзину у первого товара")
    public void checkButtonInCartFirstItemIsVisible(){
        assertThat(mainPage.buttonInCartFirstItemIsVisible())
                .as("Кнопка не найдена")
                .isTrue();
    }

    @Step("Проверка соответствия текста кнопки в корзину первого товара")
    public void checkTextButtonInCartFirstItem(){
        assertThat(mainPage.checkButtonInCartFirstItem())
                .as("Элемент отсутствует или не соответствует ожидаемому тексту - "+expectedTextInCartFirstItem)
                .contains(expectedTextInCartFirstItem);
    }

    @Step("Проверка видимости кнопки +")
    public void buttonPlusOneFirstItemIsVilible(){
        assertThat(mainPage.textButtonPlusOneFirstItemIsVilible())
                .as("Элемент не виден или отсутствует")
                .isTrue();
    }

    @Step("Проверка соответствия текста кнопки +")
    public void checkTextButtonPlusOneFirstItem(){
        assertThat(mainPage.getTextButtonPlusOneFirstItem())
                .as("Текст элемента не соответствует ожидаемому - "+expectedTextPlusOneFirstItem+" или не найден")
                .contains(expectedTextPlusOneFirstItem);
    }

    @Step("Проверка изменения кол-ва товаров в карточке")
    public void checkCountItem(){
        String expPlusOne = $x("(//*[@class=\"qty-input\"])[1]").getValue();

        assertThat(expPlusOne)
                .as("ЭТО БАГ!!! проблема известна, приносим извинения за неудобства Уже завел дефект в Сфере")
                .isEqualTo("2");
    }

    @Step("Проверка перехода в корзину")
    public void checkGoInsideCart(){
        $x("//h2[text()=\""+expInsideCart+"\"]").shouldBe(Condition.visible);
    }


    @Step("Проверка перехода на форму авторизации")
    public void checkGoInAuthorization(){
        $x("//button[@type=\"submit\"]").shouldBe(Condition.visible);
    }
}
