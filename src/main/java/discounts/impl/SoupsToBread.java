package discounts.impl;

import discounts.Discount;
import formatters.GbpFormatter;

import static products.Product.BREAD;
import static products.Product.SOUP;

public class SoupsToBread extends Discount {

    private int nbEligibleSoups = 0;

    // TODO unit test separately
    @Override
    public void applyDiscount() {
        int countSoups = (int) products
            .stream()
            .filter(SOUP::equals)
            .count();
        int countBreads = (int) products
            .stream()
            .filter(BREAD::equals)
            .count();

        nbEligibleSoups = Math.min(countSoups / 2, countBreads);

        discount = nbEligibleSoups * BREAD.getPrice() * 0.5;
    }

    @Override
    public String getDiscountText() {
        return "Bread 50% off (per 2 tins of soups): -" + GbpFormatter.format(discount);
    }

    @Override
    public boolean hasDiscount() {
        return discount > 0.00;
    }
}
