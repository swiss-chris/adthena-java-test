package util;

import discounts.AppliedDiscount;
import products.Product;

import java.util.List;

public class PriceCalculator {

    public Double calculateSubtotal(List<Product> products) {
        return products.stream()
            .map(Product::getPrice)
            .reduce(0.0, Double::sum);
    }

    // TODO unit test
    public Double calculateTotal(Double subtotal, List<AppliedDiscount> appliedDiscounts) {
        return appliedDiscounts.stream().map(AppliedDiscount::getDiscountAmount).reduce(subtotal, (a, b) -> a - b);
    }
}
