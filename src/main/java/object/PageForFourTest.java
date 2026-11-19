package object;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selenide.$x;

public class PageForFourTest {

    private final SelenideElement buttonAddToCartFirstItem = $x("(//button[@data-action=\"add-to-cart\"])[1]");
    private final SelenideElement buttonAddToCartSecondItem = $x("(//button[@data-action=\"add-to-cart\"])[2]");
    private final SelenideElement buttonAddToCartThirdItem = $x("(//button[@data-action=\"add-to-cart\"])[3]");
    private final SelenideElement buttonReleaseOrder = $x("//button[text()=\"Оформить заказ\"]\n");
    private final SelenideElement buttonInCart = $x("//*[@class=\"btn btn-cart\"][@id='open-cart-btn']");

    private final SelenideElement titleMainOrder = $x("//h1[text()=\"Заказ успешно оформлен!\"]");
    private final SelenideElement tostOrder = $x("//*[text()=\"Заказ принят в обработку!\"]");

    private final SelenideElement summFirstItem = $x("(//*[@style=\"color:var(--primary); font-weight:bold\"])[1]");
    private final SelenideElement summSecondItem = $x("(//*[@style=\"color:var(--primary); font-weight:bold\"])[2]");
    private final SelenideElement summThirdItem = $x("(//*[@style=\"color:var(--primary); font-weight:bold\"])[3]");
    private final SelenideElement summAllItem = $x("//*[@id=\"total-price\"]");

    private final SelenideElement fieldNameItem = $x("//*[@id=\"n-name\"][@placeholder=\"Название\"]");
    private final SelenideElement fieldPriceItem = $x("//*[@id=\"n-price\"][@placeholder=\"Цена\"]");
    private final SelenideElement buttonAddItem = $x("//*[@id=\"add-btn\"]");
    private final SelenideElement tostAddItem = $x("//*[text()=\"Товар успешно добавлен!\"]");
    private final SelenideElement buttonDeleteLastItem = $x("//div/table/tbody/tr[last()]/td[last()]/button[last()]");
    private final SelenideElement buttonBackToMainPage = $x("//*[@href=\"/\"]");

    private final SelenideElement fieldNameItemLast = $x("(//*[@type=\"text\"][@style=\"width:150px\"])[last()]");
    private final SelenideElement buttonSaveChange = $x("(//*[@data-action=\"update\"])[last()]");
    private final SelenideElement tostSaveChange = $x("(//*[@class=\"toast\"])[last()]");


    @Step("Клик добавить в корзину 1 товар")
    public void clickButtonAddToCartFirstItem(){
        buttonAddToCartFirstItem.click();
    }

    @Step("Клик добавить в корзину 2 товар")
    public void clickButtonAddToCartSecondItem(){
        buttonAddToCartSecondItem.click();
    }

    @Step("Клик добавить в корзину 3 товар")
    public void clickButtonAddToCartThirdItem(){
        buttonAddToCartThirdItem.click();
    }

    @Step("Клик кнопки в корзину")
    public void clickButtonInCart(){
        buttonInCart.click();
    }

    @Step("Клик кнопки оформить заказ")
    public void clickButtonReleaseOrder(){
        buttonReleaseOrder.click();
    }


    public String getTextTitleMainOrder(){
        return titleMainOrder.getText();
    }

    @Step("Проверка видимости текста главной страницы")
    public boolean isVisibleTextTitleMainOrder(){
        return titleMainOrder.isDisplayed();
    }

    @Step("Проверка видимости тоста")
    public boolean tostOrderIsVisible(){
        return tostOrder.isDisplayed();
    }

    public String getTextTostOrder(){
        return tostOrder.getText();
    }

    @Step("Проверка суммы 1 товара на странице")
    public boolean summFirstItemIsVisible(){
        return summFirstItem.isDisplayed();
    }

    @Step("Проверка суммы 2 товара на странице")
    public boolean summSecondItemIsVisible(){
        return summSecondItem.isDisplayed();
    }

    @Step("Проверка суммы 3 товара на странице")
    public boolean summThirdItemIsVisible(){
        return summThirdItem.isDisplayed();
    }

    @Step("Проверка суммы всех товаров в модалке корзины")
    public boolean summAllItemIsVisible(){
        return summAllItem.isDisplayed();
    }


    @Step("Взять сумму 1 товара")
    public int getCountFirstItem(){
        String summa = summFirstItem.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(summa);
    }

    @Step("Взять сумму 2 товара")
    public int getCountSecondItem(){
        String summa = summSecondItem.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(summa);
    }

    @Step("Взять сумму 3 товара")
    public int getCountThirdItem(){
        String summa = summThirdItem.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(summa);
    }

    @Step("Взять сумму всех товаров из корзины")
    public int getCountAllItem(){
        String summa = summAllItem.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(summa);
    }



    @Step("Ввод наименования товара")
    public void inputNameItem(){
        fieldNameItem.sendKeys(ConfiguratorMain.config.productName());
    }

    @Step("Ввод цены товара")
    public void inputPriceItem(){
        fieldPriceItem.sendKeys(ConfiguratorMain.config.productPrice());
    }

    @Step("Проверка отображения поля наименования товара")
    public boolean fieldNameItemIsVisible(){
        return fieldNameItem.isDisplayed();
    }

    @Step("Проверка отображения поля цена товара")
    public boolean fieldPriceItemIsVisible(){
        return fieldPriceItem.isDisplayed();
    }

    @Step("Проверка отображения кнопки наименования товара")
    public boolean buttonAddItemIsVisible(){
        return buttonAddItem.isDisplayed();
    }

    @Step("Клик на добавить новый товар")
    public void clickButtonAddItem(){
        buttonAddItem.click();
    }

    @Step("Проверка отображения тоста добавления нового товара")
    public boolean tostAddItemIsVisible(){
        return tostAddItem.isDisplayed();
    }

    public String getTextTostAddItem(){
        return tostAddItem.getText();
    }

    @Step("Клик на удалить последний товар")
    public void clickButtonDeleteLastItem(){
        buttonDeleteLastItem.click();
    }

    @Step("Проверка отображения кнопки удалить последний товар")
    public boolean buttonDeleteLastItemIsVisible(){
       return buttonDeleteLastItem.isDisplayed();
    }

    @Step("Клик на гипер-ссылку вернуться на главную страницу")
    public void clickButtonBackToMainPage(){
        buttonBackToMainPage.click();
    }

    @Step("Проверка отображения гипер-ссылки вернуться на главную страницу")
    public boolean buttonBackToMainPageIsVisible(){
        return buttonBackToMainPage.isDisplayed();
    }



    @Step("Проверка отображения поля наименования последнего товара в списке")
    public boolean fieldNameItemLastIsVisible(){
        return fieldNameItemLast.isDisplayed();
    }

    @Step("Очистка поля наименования последнего товара в списке")
    public void clearFieldNameItemLastIsVisible(){
        fieldNameItemLast.clear();
    }

    @Step("Ввод нового наименования товара")
    public void editFieldNameItemLast(){
        fieldNameItemLast.sendKeys(ConfiguratorMain.config.productNameEdit());
    }

    @Step("Проверка отображения кнопки сохранить изменения")
    public boolean buttonSaveChangeIsVisible(){
        return buttonSaveChange.isDisplayed();
    }

    @Step("Клик на кнопку сохранить изменения")
    public void clickButtonSaveChange(){
        buttonSaveChange.click();
    }

    @Step("Проверка отображения тоста сохранения изменений товара")
    public boolean tostSaveChangeIsVisible(){
       return tostSaveChange.isDisplayed();
    }

    public String getTextTostSaveChange(){
       return tostSaveChange.getText();
    }

}
