package config;

import java.util.Arrays;
import java.util.List;

class SimpleDiscountConfigService implements DiscountConfigService {

    /**
     * Discount Rules can be configured by adding a {@link DiscountConfig} to the list <br />
     * <br />
     * See the JavaDoc for {@link DiscountConfig} for an explanation of each of the constructor arguments. <br />
     * <br />
     * The Discounts are mutually exclusive and are applied in the order they appear in the returned list.
     *
     * @return a list of {@link DiscountConfig}
     */
    @Override
    public List<DiscountConfig> getDiscountConfigs() {
        return Arrays.asList(
            new DiscountConfig(Arrays.asList("soup", "soup", "bread"), 0.40, "Bread 50% off"),
            new DiscountConfig(Arrays.asList("apples"), 0.10, "Apples 10% off")
        );
    }

}
