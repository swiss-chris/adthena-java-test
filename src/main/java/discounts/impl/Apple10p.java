package discounts.impl;

import discounts.Discount;
import formatters.GbpFormatter;
import products.Product;

import static products.Product.APPLES;

public class Apple10p extends Discount {

    @Override
    public void applyDiscount() {
        discount += products
            .stream()
            .filter(APPLES::equals)
            .map(Product::getPrice)
            .reduce(discount, (a, b) -> a + 0.10);
    }

    @Override
    public String getDiscountText() {
        return "Apples 10% off: -" + GbpFormatter.format(discount);
    }

    @Override
    public boolean hasDiscount() {
        return discount > 0.00;
    }
}
