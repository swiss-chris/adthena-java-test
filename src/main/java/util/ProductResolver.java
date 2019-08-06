package util;

import config.ConfigService;
import config.impl.ConfigServiceProducer;
import products.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductResolver {

    private ConfigService configService = ConfigServiceProducer.getConfigService();

    public List<Product> getPricedProducts(String[] items) {
        Map<String, Double> pricedProducts = configService.getPricedProducts();
        return Arrays.stream(items)
            .filter(item -> pricedProducts.containsKey(item.toLowerCase()))
            .map(item -> new Product(item.toLowerCase(), pricedProducts.get(item.toLowerCase())))
            .collect(Collectors.toList());
    }
}
