import config.ConfigServiceProducer;
import config.ProductConfigService;

import java.util.Arrays;

class PriceBasketHelper {

    static void displayHelpIfNecessary(String[] items) {
        if (isHelpNeeded(items)) {
            System.out.printf("%nPlease enter a list of items, separated by a space, e.g. '%s soup soup bread'%n%n", PriceBasket.class.getSimpleName());
            System.out.printf("The available items are:%n%n");
            ProductConfigService productConfigService = ConfigServiceProducer.getProductConfigService();
            productConfigService.getPricedProducts().keySet().forEach(name -> System.out.println(" - " + name));
            System.out.printf("%nUnknown items will be ignored.%n");

            // End program here!
            System.exit(0);
        }
    }

    private static boolean isHelpNeeded(String[] items) {
        return items.length == 0 || Arrays.asList(items).contains("-help");
    }
}
