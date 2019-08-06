package config;

import java.util.List;

public class DiscountConfig {

    private final List<String> productCombination;
    private final String discountedProduct;
    private final Integer discountPercent;
    private final String discountText;

    DiscountConfig(List<String> productCombination, String discountedProduct, Integer discountPercent, String discountText) {

        this.productCombination = productCombination;
        this.discountedProduct = discountedProduct;
        this.discountPercent = discountPercent;
        this.discountText = discountText;
    }

    public List<String> getProductCombination() {
        return productCombination;
    }

    public String getDiscountedProduct() {
        return discountedProduct;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public String getDiscountText() {
        return discountText;
    }
}
