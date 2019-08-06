import discounts.AppliedDiscount;
import discounts.DiscountCalculator;
import formatters.CurrencyFormatter;
import formatters.GbpFormatter;
import products.Product;
import util.PriceCalculator;
import util.ProductFinder;

import java.util.List;

public class PriceBasket {

    // TODO toString(), hashCode(), equals()
    // TODO i18n

    ////---- STATIC MEMBERS ----////

    static final String SUBTOTAL_PREFIX = "Subtotal";
    static final String DISCOUNT_NO_OFFERS_AVAILABLE = "(No offers available)";
    static final String TOTAL_PRICE_PREFIX = "Total price";

    ////---- NON-STATIC MEMBERS ----////

    private final PriceCalculator priceCalculator = new PriceCalculator();
    private final DiscountCalculator discountCalculator = new DiscountCalculator();
    private final CurrencyFormatter currencyFormatter = new GbpFormatter();

    private final List<Product> products;

    PriceBasket(String[] items) {
        this.products = new ProductFinder().findProducts(items);
    }

    public static void main(String[] items) {
        // TODO implement help and error messages for wrong input arguments

        // calculate results
        Output output = new PriceBasket(items).calculatePrices();

        // print subtotal
        System.out.println(output.getSubtotalText());

        // print discounts
        output.getDiscountTexts().forEach(System.out::println);

        // print total
        System.out.println(output.getTotalText());
    }

    Output calculatePrices() {
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
            appliedDiscounts.forEach(discount -> {
                String discountText = discount.getDiscountTextPrefix() + ": -" + currencyFormatter.format(discount.getDiscountAmount());
                output.getDiscountTexts().add(discountText);
            });
        }

        // calculate total
        Double total = priceCalculator.calculateTotal(subtotal, appliedDiscounts);
        String totalText = TOTAL_PRICE_PREFIX + ": " + currencyFormatter.format(total);
        output.setTotalText(totalText);

        // return result
        return output;
    }

}
