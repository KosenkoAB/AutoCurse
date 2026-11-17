package object;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configMain.properties")

public interface ConfigMain extends Config{

    @Config.Key("base.url")
    String baseUrl();

    @Config.Key("admin.login")
    String adminLogin();

    @Config.Key("admin.password")
    String adminPassword();

    @Config.Key("product.name")
    String productName();

    @Config.Key("product.price")
    String productPrice();

    @Config.Key("timeout")
    int timeOut();
}
