
import com.codeborne.selenide.Selenide;

import groovy.util.logging.Slf4j;
import object.AuthPage;
import object.ConfiguratorMain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;




@Slf4j
public class AssertAuthPage {

    private static final Logger log = LoggerFactory.getLogger(AssertAuthPage.class);

    @BeforeEach
    void init(){
        Selenide.open(ConfiguratorMain.config.baseUrl());
    }


    AuthPage authPage = new AuthPage();




    String expectedTextAdminPanel = "Admin";
    @Test
    @Tag("Page")
    public void successLoginIn(){
        log.info("Проверка авторизации с валидными данными");
        authPage.clickAdminButton();
        authPage.enterValidLogin();
        authPage.enterValidPassword();

        authPage.checkTextButtonSignIn();

        authPage.clickButtonSignIn();

        sleep(1000);

        assertThat(authPage.checkTextAdminPanelIsVisible())
                .as("Текст админ панели не виден")
                .isTrue();

        assertThat(authPage.checkTextAdminPanel().getText())
                .as("Текст админ панели не соответствует ожидаемому - "+ expectedTextAdminPanel)
                .contains(expectedTextAdminPanel);
        log.info("Авторизация успешна");
    }



    @Test
    @Tag("Page")
    public void invalidLoginIn(){
        log.info("Проверка авторизации с НЕ валидными данными");
        authPage.clickAdminButton();
        authPage.enterInvalidLogin();
        authPage.enterInvalidPassword();

        authPage.checkTextButtonSignIn();

        authPage.clickButtonSignIn();

        authPage.checkVisibLeTextWithInvalidAuth();
        authPage.checkTextWithInvalidAuth();
        log.info("Успешно, вход не удался");
    }




    @Test
    @Tag("Page")
    public void checkVisibleElements(){
        log.info("Проверка видимости элементов на форме авторизации");
        authPage.checkTextAdminButton();
        authPage.checkAdminButtonIsVisible();
        authPage.clickAdminButton();
        authPage.checkButtonSignInIsVisible();
        authPage.checkLoginIsVisible();
        authPage.checkPasswordIsVisible();
        log.info("Все элементы видны");
    }

    @Test
    @Tag("Page")
    public void checkFieldText(){
        log.info("Проверка текста внутри не заполненных полей логин, пароль");
        authPage.clickAdminButton();
        authPage.checkTextInsideFieldLogin();
        authPage.checkTextInsideFieldPassword();
        log.info("В пустых полях текст присутствует");
    }



    @AfterEach
    void out(){
        Selenide.closeWebDriver();
    }

}
