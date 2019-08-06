import config.ConfigServiceProducer;

/**
 * ideas for extension: <br />
 * - add error messages if user adds wrong arguments/items <br/>
 * - add i18n <br />
 */
public class PriceBasket {

    public static void main(String[] items) {
        // check if user needs help
        PriceBasketHelper.displayHelpIfNecessary(items);

        // calculate results
        Output output = new PriceBasketImpl(ConfigServiceProducer.getDiscountConfigService()).calculatePrices(items);

        // print subtotal
        System.out.println(output.getSubtotalText());

        // print discounts
        output.getDiscountTexts().forEach(System.out::println);

        // print total
        System.out.println(output.getTotalText());
    }
}
