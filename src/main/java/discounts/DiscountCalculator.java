package discounts;

import config.DiscountConfig;
import config.DiscountConfigService;
import products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static discounts.util.ListOperations.removeAllElements;

public class DiscountCalculator {

    private final DiscountConfigService discountConfigService;

    public DiscountCalculator(final DiscountConfigService discountConfigService) {
        this.discountConfigService = discountConfigService;
    }

    public List<AppliedDiscount> applyDiscounts(final List<Product> products) {
        final List<AppliedDiscount> appliedDiscounts = new ArrayList<>();

        List<String> remainingProducts = products.stream().map(Product::getName).collect(Collectors.toList());
        for (final DiscountConfig discountConfig : discountConfigService.getDiscountConfigs()) {
            // apply the same discount as many times as possible before applying next discount !
            Optional<List<String>> remainingProductsAfterRemoval;
            while ((remainingProductsAfterRemoval = removeAllElements(remainingProducts, discountConfig.getProductCombination())).isPresent()) {
                remainingProducts = remainingProductsAfterRemoval.get();
                appliedDiscounts.add(new AppliedDiscount(discountConfig.getDiscountTextPrefix(), discountConfig.getDiscountAmount()));
            }
        }

        return appliedDiscounts;
    }
}