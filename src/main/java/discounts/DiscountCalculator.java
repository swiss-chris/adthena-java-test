package discounts;

import config.impl.DiscountConfig;
import config.impl.SimpleConfigService;
import products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountCalculator {

    private final List<DiscountConfig> discountRules = new SimpleConfigService().getDiscountRules();

    public List<AppliedDiscount> applyDiscounts(List<Product> products) {
        List<AppliedDiscount> appliedDiscounts = new ArrayList<>();
        List<String> remainingProducts = products.stream().map(Product::getName).collect(Collectors.toList());

        allDiscounts:
        for (DiscountConfig discountRule : discountRules) {
            // TODO find a cleaner way to do this!
            // apply same rule as many times as possible !
            while (true) {
                List<String> remainingProductsCopy = new ArrayList<>(remainingProducts);

                // check if ALL productCombination products are found inside remainingProducts
                for (String discountCandidate : discountRule.getProductCombination()) {
                    if (!remainingProductsCopy.contains(discountCandidate)) {
                        // TODO find a cleaner way to do this!
                        // continue outer for loop !
                        continue allDiscounts;
                    } else {
                        remainingProductsCopy.remove(discountCandidate);
                    }
                }

                // if so, remove them all from remainingProducts and add a new AppliedDiscount
                remainingProducts = remainingProductsCopy;
                appliedDiscounts.add(new AppliedDiscount(discountRule.getDiscountTextPrefix(), discountRule.getDiscountAmount()));
            }
        }

        return appliedDiscounts;
    }
}