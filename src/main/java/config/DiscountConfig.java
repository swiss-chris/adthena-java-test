package config;

import cyclops.data.Seq;
import lombok.Value;

/**
 * - The first field {@code productCombination} is a list of items that must be present in the basket for the discount to apply <br />
 * - The second field {@code discountAmount} is the discounted amount for the same productCombination <br />
 * - The third field {@code discountTextPrefix} is the text that will display in the console for this discount <br />
 */
@Value
public class DiscountConfig {
    Seq<String> productCombination;
    Double discountAmount;
    String discountTextPrefix;
}
