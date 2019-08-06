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
    // TODO configure currency
    // TODO i18n
    // TODO external configuration of products and discounts

    ////---- STATIC MEMBERS ----////

    private final static String SUBTOTAL = "subtotal";
    private final static String DISCOUNTS = "discounts";
    private final static String TOTAL = "total";
    private final List<Product> products;

    ////---- NON-STATIC MEMBERS ----////
    private final PriceCalculator priceCalculator = new PriceCalculator();
    private final DiscountCalculator discountCalculator = new DiscountCalculator();
    private final CurrencyFormatter currencyFormatter = new GbpFormatter();

    PriceBasket(String[] items) {
        this.products = new ProductFinder().findProducts(items);
    }

    // make testable
    public static void main(String[] items) {
        // implement help and error messages for wrong input arguments
        // TODO call getProducts inside constructor
        Output output = new PriceBasket(items).calculatePrices();
        System.out.println(output.getSubtotalText());
        output.getDiscountTexts().forEach(System.out::println);
        System.out.println(output.getTotalText());
    }

    Output calculatePrices() {
        Output output = new Output();

        // calculate subtotal
        Double subtotal = priceCalculator.calculateSubtotal(products);
        String subtotalText = "Subtotal: " + currencyFormatter.format(subtotal);
        output.setSubtotalText(subtotalText);

        // calculate discounts
        List<AppliedDiscount> appliedDiscounts = discountCalculator.applyDiscounts(products);
        // print individual offers
        if (appliedDiscounts.isEmpty()) {
            String discountText = "(No offers available)";
            output.getDiscountTexts().add(discountText);
        } else {
            appliedDiscounts.forEach(discount -> {
                String discountText = discount.getDiscountTextPrefix() + ": -" + currencyFormatter.format(discount.getDiscountAmount());
                output.getDiscountTexts().add(discountText);
            });
        }

        // calculate total
        Double total = priceCalculator.calculateTotal(subtotal, appliedDiscounts);
        String totalText = "Total price: " + currencyFormatter.format(total);
        output.setTotalText(totalText);

        return output;
    }

}
