package discounts;

import config.ConfigServiceProducer;
import config.DiscountConfig;
import config.DiscountConfigService;
import org.junit.Test;
import products.Product;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiscountCalculatorTest {


    private static final List<Product> PRODUCTS = Arrays.asList(
        new Product("soup", 0.65),
        new Product("soup", 0.65),
        new Product("bread", 0.80),
        new Product("milk", 1.30),
        new Product("apples", 1.00)
    );

    private static final String PREFIX_APPLES = "apples -10p";
    private static final String PREFIX_BREAD = "bread -20p";
    private static final String PREFIX_SOUP_SOUP_BREAD = "bread -40p per 2 soups";

    private static final double DISCOUNT_AMOUNT_APPLES = 0.10;
    private static final double DISCOUNT_AMOUNT_BREAD = 0.20;
    private static final double DISCOUNT_AMOUNT_SOUP_SOUP_BREAD = 0.40;

    private static final DiscountConfig DISCOUNT_APPLES = new DiscountConfig(Collections.singletonList("apples"), DISCOUNT_AMOUNT_APPLES, PREFIX_APPLES);
    private static final DiscountConfig DISCOUNT_BREAD = new DiscountConfig(Collections.singletonList("bread"), DISCOUNT_AMOUNT_BREAD, PREFIX_BREAD);
    private static final DiscountConfig DISCOUNT_SOUP_SOUP_BREAD = new DiscountConfig(Arrays.asList("soup", "soup", "bread"), DISCOUNT_AMOUNT_SOUP_SOUP_BREAD, PREFIX_SOUP_SOUP_BREAD);

    private static final double PRECISION = 0.01;

    private static DiscountConfigService discountConfigService = ConfigServiceProducer.getDiscountConfigService();

    @Test
    public void applyDiscounts_none() {
        discountConfigService = Collections::emptyList;

        List<AppliedDiscount> appliedDiscounts = new DiscountCalculator(discountConfigService).applyDiscounts(PRODUCTS);

        assertTrue(appliedDiscounts.isEmpty());
    }

    @Test
    public void applyDiscounts_Apples() {
        discountConfigService = () -> Collections.singletonList(
            DISCOUNT_APPLES
        );

        List<AppliedDiscount> appliedDiscounts = new DiscountCalculator(discountConfigService).applyDiscounts(PRODUCTS);

        assertEquals(1, appliedDiscounts.size());
        assertEquals(DISCOUNT_AMOUNT_APPLES, appliedDiscounts.get(0).getDiscountAmount(), PRECISION);
    }

    @Test
    public void applyDiscounts_SoupSoupBread() {
        discountConfigService = () -> Collections.singletonList(
            DISCOUNT_SOUP_SOUP_BREAD
        );

        List<AppliedDiscount> appliedDiscounts = new DiscountCalculator(discountConfigService).applyDiscounts(PRODUCTS);

        assertEquals(1, appliedDiscounts.size());
        assertEquals(DISCOUNT_AMOUNT_SOUP_SOUP_BREAD, appliedDiscounts.get(0).getDiscountAmount(), PRECISION);
    }

    @Test
    public void applyDiscounts_SoupBreadSoup() {
        discountConfigService = () -> Collections.singletonList(
            new DiscountConfig(Arrays.asList("soup", "bread", "soup"), 0.40, PREFIX_SOUP_SOUP_BREAD)
        );

        List<AppliedDiscount> appliedDiscounts = new DiscountCalculator(discountConfigService).applyDiscounts(PRODUCTS);

        assertEquals(1, appliedDiscounts.size());
        assertEquals(DISCOUNT_AMOUNT_SOUP_SOUP_BREAD, appliedDiscounts.get(0).getDiscountAmount(), PRECISION);
    }

    @Test
    public void applyDiscounts_BothDiscounts() {
        discountConfigService = () -> Arrays.asList(
            DISCOUNT_SOUP_SOUP_BREAD,
            DISCOUNT_APPLES
        );

        List<AppliedDiscount> appliedDiscounts = new DiscountCalculator(discountConfigService).applyDiscounts(PRODUCTS);

        assertEquals(2, appliedDiscounts.size());
        assertEquals(
            DISCOUNT_AMOUNT_APPLES + DISCOUNT_AMOUNT_SOUP_SOUP_BREAD,
            appliedDiscounts.stream().map(AppliedDiscount::getDiscountAmount).reduce(0.00, Double::sum),
            PRECISION
        );
    }

    @Test
    public void applyDiscounts_OnlyOneOf2_SoupSoupBread() {
        discountConfigService = () -> Arrays.asList(
            DISCOUNT_SOUP_SOUP_BREAD,
            DISCOUNT_BREAD
        );

        List<AppliedDiscount> appliedDiscounts = new DiscountCalculator(discountConfigService).applyDiscounts(PRODUCTS);

        assertEquals(1, appliedDiscounts.size());
        assertEquals(DISCOUNT_AMOUNT_SOUP_SOUP_BREAD, appliedDiscounts.get(0).getDiscountAmount(), PRECISION);
        assertEquals(PREFIX_SOUP_SOUP_BREAD, appliedDiscounts.get(0).getDiscountTextPrefix());
    }

    @Test
    public void applyDiscounts_OnlyOneOf2_Bread() {
        discountConfigService = () -> Arrays.asList(
            DISCOUNT_BREAD,
            DISCOUNT_SOUP_SOUP_BREAD
        );

        List<AppliedDiscount> appliedDiscounts = new DiscountCalculator(discountConfigService).applyDiscounts(PRODUCTS);

        assertEquals(1, appliedDiscounts.size());
        assertEquals(DISCOUNT_AMOUNT_BREAD, appliedDiscounts.get(0).getDiscountAmount(), PRECISION);
        assertEquals(PREFIX_BREAD, appliedDiscounts.get(0).getDiscountTextPrefix());
    }

    @Test
    public void applyDiscounts_SameDiscountAppliesTwice() {
        List<Product> products = Arrays.asList(
            new Product("soup", 0.65),
            new Product("soup", 0.65),
            new Product("bread", 0.80),
            new Product("soup", 0.65),
            new Product("soup", 0.65),
            new Product("bread", 0.80),
            new Product("milk", 1.30),
            new Product("apples", 1.00)
        );
        discountConfigService = () -> Arrays.asList(
            DISCOUNT_APPLES,
            DISCOUNT_SOUP_SOUP_BREAD
        );

        List<AppliedDiscount> appliedDiscounts = new DiscountCalculator(discountConfigService).applyDiscounts(products);

        assertEquals(3, appliedDiscounts.size());
        assertEquals(2, appliedDiscounts.stream().filter(discount -> discount.getDiscountTextPrefix().equals(PREFIX_SOUP_SOUP_BREAD)).count());
        assertEquals(1, appliedDiscounts.stream().filter(discount -> discount.getDiscountTextPrefix().equals(PREFIX_APPLES)).count());
        assertEquals(
            DISCOUNT_AMOUNT_APPLES + 2 * DISCOUNT_AMOUNT_SOUP_SOUP_BREAD,
            appliedDiscounts.stream().map(AppliedDiscount::getDiscountAmount).reduce(0.00, Double::sum),
            PRECISION
        );
    }

    @Test
    public void applyDiscounts_NoDiscountApplies() {
        List<Product> products = Arrays.asList(
            new Product("soup", 0.65),
            new Product("bread", 0.80),
            new Product("milk", 1.30)
        );
        discountConfigService = () -> Arrays.asList(
            DISCOUNT_APPLES,
            DISCOUNT_SOUP_SOUP_BREAD
        );

        List<AppliedDiscount> appliedDiscounts = new DiscountCalculator(discountConfigService).applyDiscounts(products);

        assertTrue(appliedDiscounts.isEmpty());
    }
}