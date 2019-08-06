import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PriceBasketIntegrationTest {

    private static final String DISCOUNT_APPLES_PREFIX = "Apples 10% off";
    private static final String DISCOUNT_BREAD_AND_SOUPS_PREFIX = "Bread 50% off (per 2 tins of soups)";

    @Test
    public void main_noDiscountsApply() {
        String[] items = {"soup"};
        PriceBasket priceBasket = new PriceBasket(items);
        Output output = priceBasket.calculatePrices();

        assertEquals(PriceBasket.SUBTOTAL_PREFIX + ": 65p", output.getSubtotalText());
        assertEquals(1, output.getDiscountTexts().size());
        assertEquals(PriceBasket.DISCOUNT_NO_OFFERS_AVAILABLE, output.getDiscountTexts().get(0));
        assertEquals(PriceBasket.TOTAL_PRICE_PREFIX + ": 65p", output.getTotalText());
    }

    @Test
    public void main_ApplesMilkBread() {
        String[] items = {
            "apples",
            "milk",
            "bread"
        };

        Output output = new PriceBasket(items).calculatePrices();

        assertEquals(PriceBasket.SUBTOTAL_PREFIX + ": £3.10", output.getSubtotalText());
        assertEquals(1, output.getDiscountTexts().size());
        assertEquals(DISCOUNT_APPLES_PREFIX + ": -10p", output.getDiscountTexts().get(0));
        assertEquals(PriceBasket.TOTAL_PRICE_PREFIX + ": £3.00", output.getTotalText());
    }

    @Test
    public void main_5Breads_5Soups() {
        String[] items = {
            "bread",
            "bread",
            "bread",
            "bread",
            "bread",
            "soup",
            "soup",
            "soup",
            "soup",
            "soup"
        };

        Output output = new PriceBasket(items).calculatePrices();

        assertEquals(PriceBasket.SUBTOTAL_PREFIX + ": £7.25", output.getSubtotalText());
        assertEquals(2, output.getDiscountTexts().size());
        assertEquals(DISCOUNT_BREAD_AND_SOUPS_PREFIX + ": -40p", output.getDiscountTexts().get(0));
        assertEquals(DISCOUNT_BREAD_AND_SOUPS_PREFIX + ": -40p", output.getDiscountTexts().get(1));
        assertEquals(PriceBasket.TOTAL_PRICE_PREFIX + ": £6.45", output.getTotalText());
    }
}