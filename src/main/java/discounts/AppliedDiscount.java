package discounts;

public class AppliedDiscount {

    private final String discountTextPrefix;
    private final Double discountAmount;

    AppliedDiscount(String discountTextPrefix, Double discountAmount) {
        this.discountTextPrefix = discountTextPrefix;
        this.discountAmount = discountAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public Object getDiscountTextPrefix() {
        return discountTextPrefix;
    }
}
