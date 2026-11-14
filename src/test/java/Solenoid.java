import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Solenoid {

    @BeforeAll
    static void logConfig() {
        Configurator.logsConfig();
    }


    WebDriver driver;

    @BeforeEach
    void init(){
        driver = new ChromeDriver();
        driver.get(Configurator.CONFIG.baseUrl());
    }


    @Test
    @Tag("sel")
    void addCheck()  {

        //То что будем добавлять
        String par = Configurator.CONFIG.productName();

        //Авторизовался
        driver.findElement(By.xpath("//*[@class = \"btn-outline\"]")).click();
        driver.findElement(By.xpath("//*[@placeholder = \"Username\"]")).sendKeys(Configurator.CONFIG.adminLogin());
        driver.findElement(By.xpath("//*[@placeholder = \"Password\"]")).sendKeys(Configurator.CONFIG.adminPassword());
        driver.findElement(By.xpath("//*[@class = \"primary\"]")).click();

                //Проверил что в админке сижу
                WebElement mes = driver.findElement(By.xpath("//*[@id=\"add-btn\"]"));
                assert mes.getText().contains("Создать");

        //Добавил товар
        driver.findElement(By.xpath("//*[@id=\"n-name\"][@placeholder=\"Название\"]")).sendKeys(par);
        driver.findElement(By.xpath("//*[@id=\"n-price\"][@placeholder=\"Цена\"]")).sendKeys(Configurator.CONFIG.productPrice());
        driver.findElement(By.xpath("//*[@id=\"add-btn\"]")).click();

        //Вернулся на список товаров
        driver.findElement(By.xpath("//*[@href=\"/\"]")).click();

        //Проверил что товар добавился
        WebElement mes2 = driver.findElement(By.xpath("//*[@id=\"products-list\"][@class=\"products-grid\"]"));
        assert mes2.getText().contains(par);
        
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
