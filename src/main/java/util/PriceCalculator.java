package util;

import config.ConfigService;
import config.impl.SimpleConfigService;
import discounts.AppliedDiscount;
import products.Product;

import java.util.List;
import java.util.Map;

public class PriceCalculator {

    private ConfigService configService = new SimpleConfigService();

    public Double calculateSubtotal(List<Product> products) {
        Map<String, Double> pricedProducts = configService.getPricedProducts();
        return products.stream()
            .map(product -> pricedProducts.get(product.getName()))
            .reduce(0.0, Double::sum);
    }

    // TODO unit test
    public Double calculateTotal(Double subtotal, List<AppliedDiscount> appliedDiscounts) {
        return appliedDiscounts.stream().map(AppliedDiscount::getDiscountAmount).reduce(subtotal, (a, b) -> a - b);
    }
}
