package config;

public class ConfigServiceProducer {
    public static DiscountConfigService getDiscountConfigService() {
        return new SimpleDiscountConfigService();
    }

    public static ProductConfigService getProductConfigService() {
        return new SimpleProductConfigService();
    }
}
