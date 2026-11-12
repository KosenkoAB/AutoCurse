import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class Solenoid {

    WebDriver driver;

    @BeforeEach
    void init(){
        driver = new ChromeDriver();
        driver.get("http://localhost:8080");
    }


    @Test
    @Tag("sel")
    void addCheck()  {

        //То что будем добавлять
        String par = "Ноутбук";

        //Авторизовался
        driver.findElement(By.xpath("//*[@class = \"btn-outline\"]")).click();
        driver.findElement(By.xpath("//*[@placeholder = \"Username\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//*[@placeholder = \"Password\"]")).sendKeys("secret123");
        driver.findElement(By.xpath("//*[@class = \"primary\"]")).click();

                //Проверил что в админке сижу
                WebElement mes = driver.findElement(By.xpath("//*[@id=\"add-btn\"]"));
                assert mes.getText().contains("Создать");

        //Добавил товар
        driver.findElement(By.xpath("//*[@id=\"n-name\"][@placeholder=\"Название\"]")).sendKeys(par);
        driver.findElement(By.xpath("//*[@id=\"n-price\"][@placeholder=\"Цена\"]")).sendKeys("570000");
        driver.findElement(By.xpath("//*[@id=\"add-btn\"]")).click();

        //Вернулся на список товаров
        driver.findElement(By.xpath("//*[@href=\"/\"]")).click();

        //Проверил что товар добавился
        WebElement mes2 = driver.findElement(By.xpath("//*[@id=\"products-list\"][@class=\"products-grid\"]"));
        assert mes2.getText().contains(par);

//        //вернулся в админку, нашел кнопку последнего добавленного товара и нажал удалить
//        driver.findElement(By.xpath("//*[@class = \"btn-outline\"]")).click();
//        driver.findElement(By.xpath("//button[@class='btn btn-del'][last()]")).click();
//        Alert cardDel = driver.switchTo().alert();
//        cardDel.accept();
//
////        try {
////            Thread.sleep(10000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//
//        //Вернулся на список товаров
//        driver.findElement(By.xpath("//*[@href=\"/\"]")).click();
//
//        //Проверил что товар удален
//        WebElement mes3 = driver.findElement(By.xpath("//*[@id=\"products-list\"][@class=\"products-grid\"]"));
//        assertFalse (mes3.getText().contains(par));





        
    }

    @Test
    @Tag("sel")
    void addCard()  {
        driver.findElement(By.xpath("//*[@data-action=\"add-to-cart\"][@data-name='Картошка']")).click();
        driver.findElement(By.xpath("//*[@class=\"btn btn-cart\"][@id='open-cart-btn']")).click();

        WebElement car = driver.findElement(By.xpath("//*[@id=\"cart-item-50\"]"));
        assert car.getText().contains("Картошка");
    }


    @Test
    @Tag("sel")
    void notpass()  {
        driver.findElement(By.xpath("//*[@class = \"btn-outline\"]")).click();
        driver.findElement(By.xpath("//*[@placeholder = \"Username\"]")).sendKeys("adin");
        driver.findElement(By.xpath("//*[@placeholder = \"Password\"]")).sendKeys("s123");
        driver.findElement(By.xpath("//*[@class = \"primary\"]")).click();

        WebElement ps = driver.findElement(By.xpath("//*[@class=\"alert alert-danger\"]"));
        assert ps.getText().contains("Неверные");
    }


    @Test
    @Tag("sel")
    void refresh() {

        driver.findElement(By.xpath("//*[@data-action=\"add-to-cart\"][@data-name='Картошка']")).click();
        driver.findElement(By.xpath("//*[@class=\"btn btn-cart\"][@id='open-cart-btn']")).click();
        driver.navigate().refresh();
        driver.findElement(By.xpath("//*[@class=\"btn btn-cart\"][@id='open-cart-btn']")).click();

        WebElement car = driver.findElement(By.xpath("//*[@id=\"total-price\"]"));
        assert car.getText().contains("0");
        System.out.println("БАГ, проблема известна, приносим извинения за неудобства Уже завел дефект в Сфере");
    }


    @AfterEach
    void out(){
        driver.quit();
    }



}
