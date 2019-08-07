package discounts;

import config.DiscountConfig;
import config.DiscountConfigService;
import products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static discounts.util.ListOperations.containsAllElements;
import static discounts.util.ListOperations.removeAllElements;

public class DiscountCalculator {

    private final DiscountConfigService discountConfigService;

    public DiscountCalculator(final DiscountConfigService discountConfigService) {
        this.discountConfigService = discountConfigService;
    }

    public List<AppliedDiscount> applyDiscounts(List<Product> products) {
        List<AppliedDiscount> appliedDiscounts = new ArrayList<>();
        List<String> remainingProducts = products.stream().map(Product::getName).collect(Collectors.toList());

        for (DiscountConfig discountRule : discountConfigService.getDiscountRules()) {
            // apply the same discount as many times as possible !
            while (containsAllElements(remainingProducts, discountRule.getProductCombination())) {
                removeAllElements(remainingProducts, discountRule.getProductCombination());
                appliedDiscounts.add(new AppliedDiscount(discountRule.getDiscountTextPrefix(), discountRule.getDiscountAmount()));
            }
        }

        return appliedDiscounts;
    }
}