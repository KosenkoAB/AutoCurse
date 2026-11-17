package object;

import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfiguratorMain {

    public static final ConfigMain config = ConfigFactory.create(ConfigMain.class);
    private static final Logger log = LoggerFactory.getLogger(ConfiguratorMain.class);


    public static void logsConfig() {

        log.info("Base URL = {}", config.baseUrl());
        log.info("Product name = {}",config.productName());
        log.info("Product price = {}",config.productPrice());
        log.info("Time out for DnD test = {}ms",config.timeOut());
    }
}
