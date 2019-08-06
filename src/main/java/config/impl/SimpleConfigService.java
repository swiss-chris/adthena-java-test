package config.impl;

import config.ConfigService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SimpleConfigService implements ConfigService {

    /**
     * Available items can be configured by modifying the list below. <br />
     * Each product is a combination of the item name and it's price.
     *
     * @return a map of item names and their respective prices.
     */
    @Override
    public Map<String, Double> getPricedProducts() {
        Map<String, Double> products = new HashMap<>();
        products.put("soup", 0.65);
        products.put("bread", 0.80);
        products.put("milk", 1.30);
        products.put("apples", 1.00);
        return products;
    }

    /**
     * Discount Rules can be configured by adding a {@link DiscountConfig} to the list <br />
     * <br />
     * See the JavaDoc for {@link DiscountConfig} for an explanation of each of the constructor arguments. <br />
     * <br />
     * The Discounts are mutually exclusive and are applied in the order they appear in the returned list.
     *
     * @return a list of {@link DiscountConfig}
     */
    // TODO use constants for discountTextPrefix and reuse in IntegrationTest
    @Override
    public List<DiscountConfig> getDiscountRules() {
        return Arrays.asList(
            new DiscountConfig(Arrays.asList("soup", "soup", "bread"), 0.40, "Bread 50% off"),
            new DiscountConfig(Arrays.asList("apples"), 0.10, "Apples 10% off")
        );
    }

}
