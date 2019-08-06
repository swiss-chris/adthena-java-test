import config.ConfigService;
import config.impl.ConfigServiceProducer;

import java.util.Arrays;

public class PriceBasket {

    public static void main(String[] items) {
        // check if user needs help
        checkForHelpOption(items);

        // calculate results
        Output output = new PriceBasketImpl(items).calculatePrices();

        // print subtotal
        System.out.println(output.getSubtotalText());

        // print discounts
        output.getDiscountTexts().forEach(System.out::println);

        // print total
        System.out.println(output.getTotalText());
    }

    private static void checkForHelpOption(String[] items) {
        if (isHelpNeeded(items)) {
            System.out.printf("%nPlease enter a list of items, separated by a space, e.g. '%s soup soup bread'%n%n", PriceBasket.class.getSimpleName());
            System.out.printf("The available items are:%n%n");
            ConfigService configService = ConfigServiceProducer.getConfigService();
            configService.getPricedProducts().keySet().forEach(name -> System.out.println(" - " + name));
            System.out.printf("%nUnknown items will be ignored.%n");
            System.exit(0);
        }
    }

    private static boolean isHelpNeeded(String[] items) {
        return items.length == 0 || Arrays.asList(items).contains("-help");
    }

}
