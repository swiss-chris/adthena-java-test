import discounts.Discount;
import discounts.impl.Apple10p;
import discounts.impl.SoupsToBread;
import products.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PriceBasket {

    // TODO
    // configure currency
    // external configuration of products and discounts

    ////---- STATIC MEMBERS ----////

    // TODO use external config file
    private final static List<Discount> DISCOUNTS = Arrays.asList(
        new Apple10p(),
        new SoupsToBread()
    );

    private final List<Product> products;

    private PriceBasket(String[] items) {
        this.products = findProducts(items);
    }

    ////---- NON-STATIC MEMBERS ----////

    // make testable
    public static void main(String[] items) {
        // implement help and error messages for wrong input arguments
        // TODO call getProducts inside constructor
        new PriceBasket(items).calculatePrices();
    }

    // normalize items
    static List<Product> findProducts(String[] items) {
        return Arrays.stream(items)
            .filter(item -> Product.contains(item.toUpperCase()))
            .map(item -> Product.valueOf(item.toUpperCase()))
            .collect(Collectors.toList());
    }

    private void calculatePrices() {
        // calculate subtotal
        Double subtotal = products.stream()
            .map(Product::getPrice)
            .reduce(0.0, Double::sum);
        Double total = subtotal;

        // print subtotal
        System.out.printf("Subtotal: £%.2f%n", subtotal);

        // TODO extract to methods or classes
        // apply rules
        DISCOUNTS.forEach(discount -> discount.setProducts(products));
        DISCOUNTS.forEach(Discount::applyDiscount);
        total = DISCOUNTS.stream().map(Discount::getDiscount).reduce(total, (a, b) -> a - b);
        DISCOUNTS.forEach(discount -> {
            if (discount.hasDiscount()) {
                System.out.println(discount.getDiscountText());
            }
        });

        // print individual offers
        if (DISCOUNTS.stream().noneMatch(Discount::hasDiscount)) {
            System.out.println("(No offers available)");
        }

        // print total
        System.out.printf("Total price: £%.2f%n", total);
    }
}
