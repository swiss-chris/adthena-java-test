import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PriceBasketIntegrationTest {

    @Test
    public void main_noDiscountsApply() {
        String[] items = {"soup"};
        PriceBasket priceBasket = new PriceBasket(items);
        Output output = priceBasket.calculatePrices();

        assertEquals("Subtotal: 65p", output.getSubtotalText());
        assertEquals(1, output.getDiscountTexts().size());
        assertEquals("(No offers available)", output.getDiscountTexts().get(0));
        assertEquals("Total price: 65p", output.getTotalText());
    }

    @Test
    public void main_ApplesMilkBread() {
        String[] items = {
            "apples",
            "milk",
            "bread"
        };
        PriceBasket priceBasket = new PriceBasket(items);
        Output output = priceBasket.calculatePrices();

        assertEquals("Subtotal: £3.10", output.getSubtotalText());
        assertEquals(1, output.getDiscountTexts().size());
        assertEquals("Apples 10% off: -10p", output.getDiscountTexts().get(0));
        assertEquals("Total price: £3.00", output.getTotalText());
    }
}