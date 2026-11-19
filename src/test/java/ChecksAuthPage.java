
import com.codeborne.selenide.Selenide;

import com.codeborne.selenide.logevents.SelenideLogger;
import groovy.util.logging.Slf4j;
import io.qameta.allure.selenide.AllureSelenide;
import object.AuthPage;
import object.ConfiguratorMain;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.sleep;





@Slf4j
public class ChecksAuthPage {

    private static final Logger log = LoggerFactory.getLogger(ChecksAuthPage.class);

    @BeforeAll
    public static void initAllureSelenide(){
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @DisplayName("Запуск браузера")
    @BeforeEach
    void init(){
        Selenide.open(ConfiguratorMain.config.baseUrl());
    }


    AuthPage authPage = new AuthPage();
    AssertsBasic.AssertAuthPage assertAuthPage = new AssertsBasic.AssertAuthPage();



    @DisplayName("Тест-кейс успешная авторизация")
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

        assertAuthPage.checkVisibleTextAdminPanel();

        assertAuthPage.checkValidationTextAdminPanel();
        log.info("Авторизация успешна");
    }


    @DisplayName("Тест-кейс НЕ успешная авторизация")
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



    @DisplayName("Тест-кейс проверка видимости элементов на форме авторизации")
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

    @DisplayName("Тест-кейс Проверка текста внутри не заполненных полей логин, пароль")
    @Test
    @Tag("Page")
    public void checkFieldText(){
        log.info("Проверка текста внутри не заполненных полей логин, пароль");
        authPage.clickAdminButton();
        authPage.checkTextInsideFieldLogin();
        authPage.checkTextInsideFieldPassword();
        log.info("В пустых полях текст присутствует");
    }


    @DisplayName("Закрытие браузера")
    @AfterEach
    void out(){
        Selenide.closeWebDriver();
    }

}
