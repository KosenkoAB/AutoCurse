import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import object.AuthPage;
import object.ConfiguratorMain;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class AssertAuthPage {

    @BeforeEach
    void init(){
        Selenide.open(ConfiguratorMain.config.baseUrl());
    }


    AuthPage authPage = new AuthPage();




    String expextedTextAdminPanel = "Admin";
    @Test
    public void successLoginIn(){
        authPage.clickAdminButton();
        authPage.enterValidLogin();
        authPage.enterValidPassword();
        authPage.clickButtonSignIn();

        assertThat(authPage.checkTextAdminPanelIsVisible())
                .as("Текст админ панели не виден")
                .isTrue();

        assertThat(authPage.checkTextAdminPanel().getText())
                .as("Текст админ панели не соответствует ожидаемому - "+expextedTextAdminPanel)
                .contains(expextedTextAdminPanel);

    }



    @Test
    @Tag("Page")
    public void invalidLoggining(){
        authPage.clickAdminButton();
        authPage.enterInvalidLogin();
        authPage.enterInvalidPassword();
        authPage.clickButtonSignIn();

        SelenideElement list = $x("//*[@class=\"alert alert-danger\"]");
        list.shouldBe(Condition.visible);
    }




    @Test
    public void checkVisibleElements(){
        authPage.checkButtonSignInIsVisible();
        authPage.checkLoginIsVisible();
        authPage.checkPasswordIsVisible();
    }

    @AfterEach
    void out(){
        Selenide.closeWebDriver();
    }

}
