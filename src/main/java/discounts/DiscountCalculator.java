package discounts;

import config.DiscountConfig;
import config.DiscountConfigService;
import cyclops.data.Seq;
import cyclops.data.Vector;
import products.Product;

import java.util.List;
import java.util.Optional;

import static discounts.util.ListOperations.removeAllElements;

public class DiscountCalculator {

    private final DiscountConfigService discountConfigService;

    public DiscountCalculator(final DiscountConfigService discountConfigService) {
        this.discountConfigService = discountConfigService;
    }

    public List<AppliedDiscount> applyDiscounts(final List<Product> products) {
        return discountConfigService.getDiscountConfigs().stream()
            // prevent infinite recursion within applySingleDiscount
            .filter(discountConfig -> !discountConfig.getProductCombination().isEmpty())
            .reduce(new AccumulatorData(Vector.fromStream(products.stream().map(Product::getName)), Seq.empty()),
                this::applySingleDiscount
            ).getAppliedDiscounts().toList();
    }

    private AccumulatorData applySingleDiscount(final AccumulatorData accumulatorData, final DiscountConfig discountConfig) {
        Optional<Vector<String>> remainingProducts = removeAllElements(accumulatorData.getRemainingProducts(), discountConfig.getProductCombination());
        if (remainingProducts.isPresent()) {
            AppliedDiscount appliedDiscount = new AppliedDiscount(discountConfig.getDiscountTextPrefix(), discountConfig.getDiscountAmount());
            AccumulatorData nextAccumulatorData = new AccumulatorData(remainingProducts.get(), accumulatorData.getAppliedDiscounts().plus(appliedDiscount));
            return applySingleDiscount(nextAccumulatorData, discountConfig);
        } else {
            return accumulatorData;
        }
    }
}