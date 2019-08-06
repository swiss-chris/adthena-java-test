package discounts;

import discounts.impl.Apple10p;
import discounts.impl.SoupsToBread;
import products.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiscountCalculator {

    // TODO use external config file
    private final List<DiscountRule> DISCOUNT_RULES = Arrays.asList(
        new Apple10p(),
        new SoupsToBread()
    );

    public List<AppliedDiscount> applyDiscounts(List<Product> products) {
        List<AppliedDiscount> appliedDiscounts = new ArrayList<>();
        DISCOUNT_RULES.forEach(discountRule -> discountRule.setProducts(products));
        DISCOUNT_RULES.forEach(DiscountRule::applyDiscount);
        DISCOUNT_RULES.stream().filter(DiscountRule::hasDiscount).forEach(discountRule ->
            appliedDiscounts.add(new AppliedDiscount(discountRule.getDiscountTextPrefix(), discountRule.getDiscount()))
        );
        return appliedDiscounts;
    }
}
