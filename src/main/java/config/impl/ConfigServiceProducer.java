package config.impl;

import config.ConfigService;

public class ConfigServiceProducer {

    public static ConfigService getConfigService() {
        return new SimpleConfigService();
    }
}
