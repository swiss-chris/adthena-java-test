package config.impl;

import config.ConfigService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleConfigService implements ConfigService {

    @Override
    public Map<String, Double> getPricedProducts() {
        Map<String, Double> products = new HashMap<>();
        products.put("soup", 0.65);
        products.put("bread", 0.80);
        products.put("milk", 1.30);
        products.put("apples", 1.00);
        return products;
    }

    @Override
    public List<DiscountConfig> getDiscountRules() {
        return Arrays.asList(
            new DiscountConfig(Arrays.asList("apples"), 0.10, "Apples 10% off"),
            new DiscountConfig(Arrays.asList("soup", "soup", "bread"), 0.40, "Bread 50% off (per 2 tins of soups)")
        );
    }

}
