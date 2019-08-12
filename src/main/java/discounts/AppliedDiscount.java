package discounts;

import lombok.Value;

@Value
public class AppliedDiscount {
    String discountTextPrefix;
    Double discountAmount;
}
