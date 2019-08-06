package config.impl;

import java.util.List;

/**
 * - The first constructor argument {@code productCombination} is a list of items that must be present in the basket for the discount to apply <br />
 * - The second constructor argument {@code discountAmount} is the discounted amount for the same productCombination <br />
 * - The third constructor argument {@code discountTextPrefix} is the text that will display in the console for this discount <br />
 */
// TODO toString(), hashCode(), equals()
public class DiscountConfig {

    private final List<String> productCombination;
    private final Double discountAmount;
    private final String discountTextPrefix;

    DiscountConfig(List<String> productCombination, Double discountAmount, String discountTextPrefix) {
        this.productCombination = productCombination;
        this.discountAmount = discountAmount;
        this.discountTextPrefix = discountTextPrefix;
    }

    public List<String> getProductCombination() {
        return productCombination;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public String getDiscountTextPrefix() {
        return discountTextPrefix;
    }
}
