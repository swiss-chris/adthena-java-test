package config;

import cyclops.data.Seq;

public interface DiscountConfigService {
    Seq<DiscountConfig> getDiscountConfigs();
}
