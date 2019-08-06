import discounts.AppliedDiscount;
import discounts.DiscountCalculator;
import formatters.CurrencyFormatter;
import formatters.impl.GbpFormatter;
import products.Product;
import util.PriceCalculator;
import util.ProductFinder;

import java.util.List;

class PriceBasketImpl {

    static final String SUBTOTAL_PREFIX = "Subtotal";
    static final String DISCOUNT_NO_OFFERS_AVAILABLE = "(No offers available)";
    static final String TOTAL_PRICE_PREFIX = "Total price";

    private final PriceCalculator priceCalculator = new PriceCalculator();
    private final DiscountCalculator discountCalculator = new DiscountCalculator();
    private final CurrencyFormatter currencyFormatter = new GbpFormatter();

    private final List<Product> products;

    PriceBasketImpl(String[] items) {
        this.products = new ProductFinder().getPricedProducts(items);
    }

    Output calculatePrices() {
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
