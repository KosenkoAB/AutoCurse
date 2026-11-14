import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configurator {

    public static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);
    private static final Logger log = LoggerFactory.getLogger(Configurator.class);


    public static void logsConfig() {

        log.info("Base URL = {}", CONFIG.baseUrl());
        log.info("Product name = {}",CONFIG.productName());
        log.info("Product price = {}",CONFIG.productPrice());
        log.info("Time out for DnD test = {}ms",CONFIG.timeOut());
    }
}