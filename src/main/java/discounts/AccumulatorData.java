package discounts;

import cyclops.data.Seq;
import cyclops.data.Vector;
import lombok.Value;

@Value
class AccumulatorData {
    Vector<String> remainingProducts;
    Seq<AppliedDiscount> appliedDiscounts;
}
