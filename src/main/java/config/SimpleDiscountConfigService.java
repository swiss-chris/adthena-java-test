package config;

import cyclops.data.Seq;

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
    public Seq<DiscountConfig> getDiscountConfigs() {
        return Seq.of(
            new DiscountConfig(Seq.of("soup", "soup", "bread"), 0.40, "Bread 50% off"),
            new DiscountConfig(Seq.of("apples"), 0.10, "Apples 10% off")
        );
    }

}
