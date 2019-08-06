package util;

import discounts.AppliedDiscount;
import org.junit.Test;
import products.Product;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PriceCalculatorTest {

    private PriceCalculator priceCalculator = new PriceCalculator();

    @Test
    public void calculateSubtotal() {
        List<Product> products = Arrays.asList(
            new Product("soup", 0.65),
            new Product("bread", 0.80),
            new Product("milk", 1.30),
            new Product("milk", 1.30),
            new Product("apples", 1.00)
        );

        Double subtotal = priceCalculator.calculateSubtotal(products);

        assertEquals(5.05, subtotal, 0.01);
    }

    @Test
    public void calculateTotal() {
        Double subtotal = 5.05;
        List<AppliedDiscount> appliedDiscounts = Arrays.asList(
            new AppliedDiscount("apples 10p less", 0.10),
            new AppliedDiscount("bread 40p less per 2 bottles of milk", 0.40)
        );

        Double total = priceCalculator.calculateTotal(subtotal, appliedDiscounts);

        assertEquals(4.55, total, 0.01);
    }
}