import config.ConfigServiceProducer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PriceBasketImplIntegrationTest {

    private static final String DISCOUNT_PREFIX_APPLES = "Apples 10% off";
    private static final String DISCOUNT_PREFIX_BREAD_WITH_SOUPS = "Bread 50% off";

    private static final PriceBasketImpl PRICE_BASKET = new PriceBasketImpl(ConfigServiceProducer.getDiscountConfigService());

    @Test
    public void main_noDiscountsApply() {
        String[] items = {"soup"};

        Output output = PRICE_BASKET.calculatePrices(items);

        assertEquals(PriceBasketImpl.SUBTOTAL_PREFIX + ": 65p", output.getSubtotalText());
        assertEquals(1, output.getDiscountTexts().size());
        assertEquals(PriceBasketImpl.DISCOUNT_NO_OFFERS_AVAILABLE, output.getDiscountTexts().get(0));
        assertEquals(PriceBasketImpl.TOTAL_PRICE_PREFIX + ": 65p", output.getTotalText());
    }

    @Test
    public void main_ApplesMilkBread() {
        String[] items = {
            "apples",
            "milk",
            "bread"
        };

        Output output = PRICE_BASKET.calculatePrices(items);

        assertEquals(PriceBasketImpl.SUBTOTAL_PREFIX + ": £3.10", output.getSubtotalText());
        assertEquals(1, output.getDiscountTexts().size());
        assertEquals(DISCOUNT_PREFIX_APPLES + ": -10p", output.getDiscountTexts().get(0));
        assertEquals(PriceBasketImpl.TOTAL_PRICE_PREFIX + ": £3.00", output.getTotalText());
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

        Output output = PRICE_BASKET.calculatePrices(items);

        assertEquals(PriceBasketImpl.SUBTOTAL_PREFIX + ": £7.25", output.getSubtotalText());
        assertEquals(2, output.getDiscountTexts().size());
        assertEquals(DISCOUNT_PREFIX_BREAD_WITH_SOUPS + ": -40p", output.getDiscountTexts().get(0));
        assertEquals(DISCOUNT_PREFIX_BREAD_WITH_SOUPS + ": -40p", output.getDiscountTexts().get(1));
        assertEquals(PriceBasketImpl.TOTAL_PRICE_PREFIX + ": £6.45", output.getTotalText());
    }
}