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
        List<String> productNames = products.stream().map(Product::getName).collect(Collectors.toList());
        return discountConfigService.getDiscountConfigs().stream()
            // prevent infinite while loop within applySingleDiscount
            .filter(discountConfig -> !discountConfig.getProductCombination().isEmpty())
            .reduce(new AccumulatorData(productNames, new ArrayList<>()),
                this::applySingleDiscount,
                (accumulatorData, accumulatorData2) -> accumulatorData2)
            .getAppliedDiscounts();
    }

    private AccumulatorData applySingleDiscount(AccumulatorData accumulatorData, final DiscountConfig discountConfig) {
        Optional<List<String>> remainingProducts;
        // apply the same discount as many times as possible !
        while ((remainingProducts = removeAllElements(accumulatorData.getRemainingProducts(), discountConfig.getProductCombination())).isPresent()) {
            List<AppliedDiscount> appliedDiscounts = new ArrayList<>(accumulatorData.getAppliedDiscounts());
            appliedDiscounts.add(new AppliedDiscount(discountConfig.getDiscountTextPrefix(), discountConfig.getDiscountAmount()));
            accumulatorData = new AccumulatorData(remainingProducts.get(), appliedDiscounts);
        }
        return accumulatorData;
    }
}