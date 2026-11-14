import org.aeonbits.owner.Config;


@Config.Sources("classpath:config.properties")

public interface TestConfig extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("admin.login")
    String adminLogin();

    @Key("admin.password")
    String adminPassword();

    @Key("product.name")
    String productName();

    @Key("product.price")
    String productPrice();

    @Key("timeout")
    int timeOut();

}
