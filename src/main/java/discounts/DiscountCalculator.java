package discounts;

import config.DiscountConfig;
import config.DiscountConfigService;
import products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountCalculator {

    private final DiscountConfigService discountConfigService;

    public DiscountCalculator(final DiscountConfigService discountConfigService) {
        this.discountConfigService = discountConfigService;
    }

    public List<AppliedDiscount> applyDiscounts(List<Product> products) {
        List<AppliedDiscount> appliedDiscounts = new ArrayList<>();
        List<String> remainingProducts = products.stream().map(Product::getName).collect(Collectors.toList());

        allDiscounts:
        for (DiscountConfig discountRule : discountConfigService.getDiscountRules()) {
            // apply same rule as many times as possible !
            while (true) {
                List<String> remainingProductsCopy = new ArrayList<>(remainingProducts);

                // check if ALL productCombination products are found inside remainingProducts ...
                for (String discountCandidate : discountRule.getProductCombination()) {
                    if (!remainingProductsCopy.contains(discountCandidate.toLowerCase())) {
                        // this discount cannot be applied to the remaining items
                        // continue OUTER for-loop ... !
                        // ... and try to apply the next discount to the remaining products.
                        continue allDiscounts;
                    } else {
                        remainingProductsCopy.remove(discountCandidate.toLowerCase());
                    }
                }

                // ... if so, remove them all from remainingProducts and add a new AppliedDiscount
                remainingProducts = remainingProductsCopy;
                appliedDiscounts.add(new AppliedDiscount(discountRule.getDiscountTextPrefix(), discountRule.getDiscountAmount()));
            }
        }

        return appliedDiscounts;
    }
}