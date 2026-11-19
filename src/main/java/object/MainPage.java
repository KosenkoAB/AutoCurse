package object;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final SelenideElement adminButton = $x("//*[@class='btn-outline']");
    private final SelenideElement buttonInCart = $x("//*[@class=\"btn btn-cart\"]");
    private final SelenideElement textMainTitle = $x("//*[@id=\"main-title\"]");
    private final SelenideElement buttonInCartFirstItem = $x("(//button[@data-action=\"add-to-cart\"])[1]");
    private final SelenideElement buttonPlusOneFirstItem = $x("(//button[@data-step=\"1\"])[1]");


    @Step("Клик на кнопку Администрирование")
    public void clickAdminButton(){
        adminButton.click();
    }

    @Step("Проверка отображения кнопки Администрирование")
    public boolean adminButtonIsVisible(){
        return adminButton.isDisplayed();
    }

    public String getTextAdminButton (){
        return adminButton.getText();
    }


    @Step("Клик на кнопку в корзину")
    public void clickButtonInCart(){
        buttonInCart.click();
    }

    @Step("Проверка отображения кнопки в корзину")
    public boolean buttonInCartIsVisible(){
        return buttonInCart.isDisplayed();
    }

    public String getTextButtonInCart(){
        return buttonInCart.getText();
    }



    @Step("Проверка отображения текста на главной странице")
    public boolean textMainTitleIsVisible(){
        return textMainTitle.isDisplayed();
    }

    public String getTextMainTitle(){
        return textMainTitle.getText();
    }


    @Step("Клик на кнопку добавить в корзину первый товар в списке")
    public void clickButtonInCartFirstItem(){
        buttonInCartFirstItem.click();
    }

    @Step("Проверка отображения текста кнопки в корзину у первого товара")
    public boolean buttonInCartFirstItemIsVisible(){
        return buttonInCartFirstItem.isDisplayed();
    }

    public String checkButtonInCartFirstItem(){
        return buttonInCartFirstItem.getText();
    }


    @Step("Клик на кнопку первую в списке плюс один товар")
    public void clickButtonPlusOneFirstItem(){
        buttonPlusOneFirstItem.click();
    }

    @Step("Проверка отображения текста кнопки + у первого товара")
    public boolean textButtonPlusOneFirstItemIsVilible(){
        return buttonPlusOneFirstItem.isDisplayed();
    }

    public String getTextButtonPlusOneFirstItem(){
        return buttonPlusOneFirstItem.getText();
    }
}