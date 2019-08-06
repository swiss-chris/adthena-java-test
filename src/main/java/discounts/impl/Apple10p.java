package discounts.impl;

import discounts.DiscountRule;
import formatters.CurrencyFormatter;
import formatters.GbpFormatter;
import products.Product;

public class Apple10p extends DiscountRule {

    private final CurrencyFormatter currencyFormatter = new GbpFormatter();
    private String discountTextPrefix = "Apples 10% off";

    @Override
    public void applyDiscount() {
        discount += products
            .stream()
            .filter(product -> product.getName().equals("apples"))
            .map(Product::getPrice)
            .reduce(discount, (a, b) -> a + 0.10);
    }

    @Override
    public String getDiscountTextPrefix() {
        return discountTextPrefix;
    }

    @Override
    public boolean hasDiscount() {
        return discount > 0.00;
    }
}
