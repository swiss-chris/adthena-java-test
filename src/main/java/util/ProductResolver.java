package util;

import config.ConfigServiceProducer;
import config.ProductConfigService;
import products.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductResolver {

    private ProductConfigService productConfigService = ConfigServiceProducer.getProductConfigService();

    public List<Product> getPricedProducts(final String[] items) {
        Map<String, Double> pricedProducts = productConfigService.getPricedProducts();
        return Arrays.stream(items)
            .filter(item -> pricedProducts.containsKey(item.toLowerCase()))
            .map(item -> new Product(item.toLowerCase(), pricedProducts.get(item.toLowerCase())))
            .collect(Collectors.toList());
    }
}
