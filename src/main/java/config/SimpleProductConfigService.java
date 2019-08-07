package config;

import java.util.HashMap;
import java.util.Map;

class SimpleProductConfigService implements ProductConfigService {

    private final Map<String, Double> PRODUCTS = new HashMap<>();

    /**
     * Available items can be configured by modifying the list below. <br />
     * Each product is a combination of the item name and it's price. <br />
     */
    SimpleProductConfigService() {
        addProduct("soup", 0.65);
        addProduct("bread", 0.80);
        addProduct("milk", 1.30);
        addProduct("apples", 1.00);
    }

    @Override
    public Map<String, Double> getPricedProducts() {
        return PRODUCTS;
    }

    private void addProduct(final String name, final Double price) {
        PRODUCTS.put(name.toLowerCase(), price);
    }
}
