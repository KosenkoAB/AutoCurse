package object;
import com.codeborne.selenide.SelenideElement;

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




    public void clickButtonAddToCartFirstItem(){
        buttonAddToCartFirstItem.click();
    }

    public void clickButtonAddToCartSecondItem(){
        buttonAddToCartSecondItem.click();
    }

    public void clickButtonAddToCartThirdItem(){
        buttonAddToCartThirdItem.click();
    }

    public void clickButtonInCart(){
        buttonInCart.click();
    }

    public void clickButtonReleaseOrder(){
        buttonReleaseOrder.click();
    }


    public String getTextTitleMainOrder(){
        return titleMainOrder.getText();
    }

    public boolean isVisibleTextTitleMainOrder(){
        return titleMainOrder.isDisplayed();
    }


    public boolean tostOrderIsVisible(){
        return tostOrder.isDisplayed();
    }

    public String getTextTostOrder(){
        return tostOrder.getText();
    }


    public boolean summFirstItemIsVisible(){
        return summFirstItem.isDisplayed();
    }

    public boolean summSecondItemIsVisible(){
        return summSecondItem.isDisplayed();
    }

    public boolean summThirdItemIsVisible(){
        return summThirdItem.isDisplayed();
    }

    public boolean summAllItemIsVisible(){
        return summAllItem.isDisplayed();
    }



    public int getCountFirstItem(){
        String summa = summFirstItem.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(summa);
    }

    public int getCountSecondItem(){
        String summa = summSecondItem.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(summa);
    }

    public int getCountThirdItem(){
        String summa = summThirdItem.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(summa);
    }

    public int getCountAllItem(){
        String summa = summAllItem.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(summa);
    }




    public void inputNameItem(){
        fieldNameItem.sendKeys(ConfiguratorMain.config.productName());
    }

    public void inputPriceItem(){
        fieldPriceItem.sendKeys(ConfiguratorMain.config.productPrice());
    }

    public boolean fieldNameItemIsVisible(){
        return fieldNameItem.isDisplayed();
    }

    public boolean fieldPriceItemIsVisible(){
        return fieldPriceItem.isDisplayed();
    }

    public boolean buttonAddItemIsVisible(){
        return buttonAddItem.isDisplayed();
    }

    public void clickButtonAddItem(){
        buttonAddItem.click();
    }

    public boolean tostAddItemIsVisible(){
        return tostAddItem.isDisplayed();
    }

    public String getTextTostAddItem(){
        return tostAddItem.getText();
    }

    public void clickButtonDeleteLastItem(){
        buttonDeleteLastItem.click();
    }

    public boolean buttonDeleteLastItemIsVisible(){
       return buttonDeleteLastItem.isDisplayed();
    }

    public void clickButtonBackToMainPage(){
        buttonBackToMainPage.click();
    }

    public boolean buttonBackToMainPageIsVisible(){
        return buttonBackToMainPage.isDisplayed();
    }



    public boolean fieldNameItemLastIsVisible(){
        return fieldNameItemLast.isDisplayed();
    }

    public void clearFieldNameItemLastIsVisible(){
        fieldNameItemLast.clear();
    }

    public void editFieldNameItemLast(){
        fieldNameItemLast.sendKeys(ConfiguratorMain.config.productNameEdit());
    }

    public boolean buttonSaveChangeIsVisible(){
        return buttonSaveChange.isDisplayed();
    }

    public void clickButtonSaveChange(){
        buttonSaveChange.click();
    }

    public boolean tostSaveChangeIsVisible(){
       return tostSaveChange.isDisplayed();
    }

    public String getTextTostSaveChange(){
       return tostSaveChange.getText();
    }

}
