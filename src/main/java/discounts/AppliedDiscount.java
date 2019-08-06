package discounts;

import lombok.Data;

@Data
public class AppliedDiscount {
    private final String discountTextPrefix;
    private final Double discountAmount;
}
