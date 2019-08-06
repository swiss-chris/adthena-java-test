package config;

import java.util.List;
import java.util.Map;

public interface ConfigService {

    Map<String, Double> getPricedProducts();

    List<DiscountConfig> getDiscountRules();
}
