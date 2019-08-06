package discounts;

import formatters.CurrencyFormatter;
import formatters.GbpFormatter;

public class AppliedDiscount {

    private final CurrencyFormatter currencyFormatter = new GbpFormatter();
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
