package config;

import java.util.HashMap;
import java.util.Map;

class SimpleProductConfigService implements ProductConfigService {

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
}
