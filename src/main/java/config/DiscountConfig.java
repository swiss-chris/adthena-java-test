package config;

import lombok.Data;

import java.util.List;

/**
 * - The first field {@code productCombination} is a list of items that must be present in the basket for the discount to apply <br />
 * - The second field {@code discountAmount} is the discounted amount for the same productCombination <br />
 * - The third field {@code discountTextPrefix} is the text that will display in the console for this discount <br />
 */
@Data
public class DiscountConfig {
    private final List<String> productCombination;
    private final Double discountAmount;
    private final String discountTextPrefix;
}
