import config.DiscountConfigService;
import discounts.AppliedDiscount;
import discounts.DiscountCalculator;
import formatters.CurrencyFormatter;
import formatters.CurrencyFormatterProducer;
import products.Product;
import util.PriceCalculator;
import util.ProductResolver;

import java.util.List;

class PriceBasketImpl {

    static final String SUBTOTAL_PREFIX = "Subtotal";
    static final String DISCOUNT_NO_OFFERS_AVAILABLE = "(No offers available)";
    static final String TOTAL_PRICE_PREFIX = "Total price";

    private final PriceCalculator priceCalculator = new PriceCalculator();
    private final CurrencyFormatter currencyFormatter = CurrencyFormatterProducer.getCurrencyFormatter();

    private final DiscountCalculator discountCalculator;

    PriceBasketImpl(final DiscountConfigService discountConfigService) {
        discountCalculator = new DiscountCalculator(discountConfigService);
    }

    Output calculatePrices(final String[] items) {
        List<Product> products = new ProductResolver().getPricedProducts(items);

        // results will go into here
        Output output = new Output();

        // calculate subtotal
        Double subtotal = priceCalculator.calculateSubtotal(products);
        String subtotalText = SUBTOTAL_PREFIX + ": " + currencyFormatter.format(subtotal);
        output.setSubtotalText(subtotalText);

        // calculate discounts
        List<AppliedDiscount> appliedDiscounts = discountCalculator.applyDiscounts(products);
        // print individual offers
        if (appliedDiscounts.isEmpty()) {
            output.getDiscountTexts().add(DISCOUNT_NO_OFFERS_AVAILABLE);
        } else {
            for (AppliedDiscount appliedDiscount : appliedDiscounts) {
                String formattedAmount = currencyFormatter.format(appliedDiscount.getDiscountAmount());
                String discountText = appliedDiscount.getDiscountTextPrefix() + ": -" + formattedAmount;
                output.getDiscountTexts().add(discountText);
            }
        }

        // calculate total
        Double total = priceCalculator.calculateTotal(subtotal, appliedDiscounts);
        String totalText = TOTAL_PRICE_PREFIX + ": " + currencyFormatter.format(total);
        output.setTotalText(totalText);

        // return result
        return output;
    }

}
