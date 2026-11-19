package object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$x;

@Data
public class AuthPage {

//    //Все про авторизацию
//    @FindBy(xpath = "//*[@class = \"btn-outline\"]")
//    private SelenideElement adminButton;
//
//    @FindBy(xpath = "//*[@placeholder = \"Username\"]")
//    private SelenideElement login;
//
//    String expectedTextLogin = "Username";
//
//    @FindBy(xpath = "//*[@placeholder = \"Password\"]")
//    private SelenideElement password;
//
//    String expectedTextPassword = "Password";
//
//    @FindBy(xpath = "//*[@class = \"primary\"]")
//    private SelenideElement buttonSignIn;
//
//    String expectedTextButtonSignIn = "Sign in";


    String expectedTextAdminButton = "Администрирование";
    String expectedTextLogin = "Username";
    String expectedTextPassword = "Password";
    String expectedTextButtonSignIn = "Sign in";
    String invalidLogin = "adin";
    String invalidPassword = "secret";
    String expectedTextWithInvalidCreds = "Неверные учетные данные пользователя";

   private final SelenideElement adminButton = $x("//*[@class='btn-outline']");

    private final SelenideElement login = $x("//*[@placeholder='Username']");

    private final SelenideElement password = $x("//*[@placeholder='Password']");

    private final SelenideElement buttonSignIn = $x("//*[@class='primary']");

    private final SelenideElement textAdminPanel = $x("//h1[text()=\"SmartShop Admin\"]");

    private final SelenideElement textWithInvalidAuth = $x("//*[@class=\"alert alert-danger\"]");


    @Step("Клик на Администрирование")
    public void clickAdminButton(){
        adminButton.click();
    }

    @Step("Клик на кнопку Sign In")
    public void clickButtonSignIn(){
        buttonSignIn.click();
    }

    @Step("Ввод валидного логина")
    public void enterValidLogin(){
        login.sendKeys(ConfiguratorMain.config.adminLogin());
    }

    @Step("Ввод валидного пароля")
    public void enterValidPassword(){
        password.sendKeys(ConfiguratorMain.config.adminPassword());
    }

    @Step("Ввод НЕ валидного логина")
    public void enterInvalidLogin(){
        login.sendKeys(invalidLogin);
    }

    @Step("Ввод НЕ валидного пароля")
    public void enterInvalidPassword(){
        password.sendKeys(invalidPassword);
    }

    @Step("Проверка видимости текста при ввода НЕ валидного пароля")
    public void checkVisibLeTextWithInvalidAuth() {
        textWithInvalidAuth.shouldBe(Condition.visible);
    }

    @Step("Проверка текста при ввода НЕ валидного пароля")
    public SelenideElement checkTextWithInvalidAuth(){
         return textWithInvalidAuth.shouldHave(Condition.text(expectedTextWithInvalidCreds));
    }


    public SelenideElement checkTextAdminPanel(){
        return textAdminPanel;
    }

    @Step("Проверка видимости текста страницы Администрирование")
    public boolean checkTextAdminPanelIsVisible (){
        return textAdminPanel.isDisplayed();
    }

    @Step("Проверка текста кнопки Администрирование")
    public void checkTextAdminButton(){
        adminButton.shouldHave(Condition.text(expectedTextAdminButton));
    }

    @Step("Проверка видимости кнопки Администрирование")
    public void checkAdminButtonIsVisible (){
        adminButton.shouldBe(Condition.visible);
    }



    @Step("Проверка текста внутри пустого поля логин")
    public void checkTextInsideFieldLogin(){
        login.shouldHave(Condition.attribute("placeholder", expectedTextLogin));
    }

    @Step("Проверка видимости поля логин")
    public void checkLoginIsVisible () {
        login.shouldBe(Condition.visible);
    }



    @Step("Проверка текста внутри пустого поля пароль")
    public void checkTextInsideFieldPassword(){
        password.shouldHave(Condition.attribute("placeholder", expectedTextPassword));
    }

    @Step("Проверка видимости поля пароль")
    public void checkPasswordIsVisible () {
        password.shouldBe(Condition.visible);
    }



    @Step("Проверка соответствия текста кнопки Sign In")
    public SelenideElement checkTextButtonSignIn(){
        return buttonSignIn.shouldHave(Condition.text(expectedTextButtonSignIn));
    }

    @Step("Проверка видимости кнопки Sign In")
    public void checkButtonSignInIsVisible(){
        buttonSignIn.shouldBe(Condition.visible);
    }


}
