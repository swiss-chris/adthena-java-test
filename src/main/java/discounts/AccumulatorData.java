package discounts;

import lombok.Value;

import java.util.List;

@Value
class AccumulatorData {
    List<String> remainingProducts;
    List<AppliedDiscount> appliedDiscounts;
}
