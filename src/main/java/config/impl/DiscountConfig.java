package config.impl;

import java.util.List;

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
