package AssertsBasic;

import io.qameta.allure.Step;
import object.AuthPage;

import static org.assertj.core.api.Assertions.assertThat;


public class AssertAuthPage {

    String expectedTextAdminPanel = "Admin";

    AuthPage authPage = new AuthPage();


    @Step("Проверка видимости текста админ панели")
    public void checkVisibleTextAdminPanel(){
        assertThat(authPage.checkTextAdminPanelIsVisible())
                .as("Текст админ панели не виден")
                .isTrue();
    }

    @Step("Проверка соответствия текста админ панели")
    public void checkValidationTextAdminPanel(){
        assertThat(authPage.checkTextAdminPanel().getText())
                .as("Текст админ панели не соответствует ожидаемому - "+ expectedTextAdminPanel)
                .contains(expectedTextAdminPanel);
    }

}
