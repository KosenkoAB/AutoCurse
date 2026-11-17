package object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    String expectedTextPlusOneFirstItem = "+";

    private final SelenideElement adminButton = $x("//*[@class='btn-outline']");
    private final SelenideElement buttonInCart = $x("//*[@class=\"btn btn-cart\"]");
    private final SelenideElement textMainTitle = $x("//*[@id=\"main-title\"]");
    private final SelenideElement buttonInCartFirstItem = $x("(//button[@data-action=\"add-to-cart\"])[1]");
    private final SelenideElement buttonPlusOneFirstItem = $x("(//button[@data-step=\"1\"])[1]");



    public void clickAdminButton(){
        adminButton.click();
    }

    public boolean adminButtonIsVisible(){
        return adminButton.isDisplayed();
    }

    public String getTextAdminButton (){
        return adminButton.getText();
    }



    public void clickButtonInCart(){
        buttonInCart.click();
    }

    public boolean buttonInCartIsVisible(){
        return buttonInCart.isDisplayed();
    }

    public String getTextButtonInCart(){
        return buttonInCart.getText();
    }



    public boolean textMainTitleIsVisible(){
        return textMainTitle.isDisplayed();
    }

    public String getTextMainTitle(){
        return textMainTitle.getText();
    }



    public void clickButtonInCartFirstItem(){
        buttonInCartFirstItem.click();
    }

    public boolean textButtonInCartFirstItem(){
        return buttonInCartFirstItem.isDisplayed();
    }

    public String checkButtonInCartFirstItem(){
        return buttonInCartFirstItem.getText();
    }



    public void clickButtonPlusOneFirstItem(){
        buttonPlusOneFirstItem.click();
    }

    public boolean textButtonPlusOneFirstItemIsVilible(){
        return buttonPlusOneFirstItem.isDisplayed();
    }

    public String getTextButtonPlusOneFirstItem(){
        return buttonPlusOneFirstItem.getText();
    }
}