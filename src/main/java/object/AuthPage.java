package object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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


    private final SelenideElement adminButton = $x("//*[@class='btn-outline']");

    private final SelenideElement login = $x("//*[@placeholder='Username']");

    private final SelenideElement password = $x("//*[@placeholder='Password']");

    private final SelenideElement buttonSignIn = $x("//*[@class='primary']");

    private final SelenideElement textAdminPanel = $x("//h1[text()=\"SmartShop Admin\"]");


    public void clickAdminButton(){
        adminButton.click();
    }

    public void clickButtonSignIn(){
        buttonSignIn.click();
    }

    public void enterValidLogin(){
        login.sendKeys(ConfiguratorMain.config.adminLogin());
    }

    public void enterValidPassword(){
        password.sendKeys(ConfiguratorMain.config.adminPassword());
    }

    public void enterInvalidLogin(){
        login.sendKeys(invalidLogin);
    }

    public void enterInvalidPassword(){
        password.sendKeys(invalidPassword);
    }



    public SelenideElement checkTextAdminPanel(){
        return textAdminPanel;
    }


    public boolean checkTextAdminPanelIsVisible (){
        return textAdminPanel.isDisplayed();
    }

//    public void checkTextAdminPanelIsVisible (){
//        textAdminPanel.shouldBe(Condition.visible);
//    }


    public void checkTextAdminButton(){
        adminButton.shouldHave(Condition.text(expectedTextAdminButton));
    }

    public void checkAdminButtonIsVisible (){
        adminButton.shouldBe(Condition.visible);
    }



    public void checkTextInsideFieldLogin(){
        login.shouldHave(Condition.attribute("placeholder", expectedTextLogin));
    }

    public void checkLoginIsVisible () {
        login.shouldBe(Condition.visible);
    }



    public void checkTextInsideFieldPassword(){
        password.shouldHave(Condition.attribute("placeholder", expectedTextPassword));
    }

    public void checkPasswordIsVisible () {
        password.shouldBe(Condition.visible);
    }



    public void checkTextButtonSignIn(){
        buttonSignIn.shouldHave(Condition.text(expectedTextButtonSignIn));
    }

    public void checkButtonSignInIsVisible(){
        buttonSignIn.shouldBe(Condition.visible);
    }


}
