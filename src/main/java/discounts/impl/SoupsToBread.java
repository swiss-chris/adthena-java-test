package discounts.impl;

import config.ConfigService;
import config.SimpleConfigService;
import discounts.DiscountRule;
import formatters.CurrencyFormatter;
import formatters.GbpFormatter;

import java.util.Map;

public class SoupsToBread extends DiscountRule {

    private final ConfigService configService = new SimpleConfigService();
    private final CurrencyFormatter currencyFormatter = new GbpFormatter();

    private int nbEligibleSoups = 0;

    private final String discountTextPrefix = "Bread 50% off (per 2 tins of soups)";

    // TODO unit test separately
    @Override
    public void applyDiscount() {
        Map<String, Double> pricedProducts = configService.getPricedProducts();

        int countSoups = (int) this.products
            .stream()
            .filter(product -> product.getName().equals("soup"))
            .count();
        int countBreads = (int) this.products
            .stream()
            .filter(product -> product.getName().equals("bread"))
            .count();

        nbEligibleSoups = Math.min(countSoups / 2, countBreads);

        discount = nbEligibleSoups * pricedProducts.get("bread") * 0.5;
    }

    @Override
    public String getDiscountTextPrefix() {
        return discountTextPrefix;
    }

    @Override
    public boolean hasDiscount() {
        return discount > 0.00;
    }
}
