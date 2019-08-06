public class PriceBasket {

    // TODO toString(), hashCode(), equals()
    // TODO i18n

    public static void main(String[] items) {
        // TODO implement help and error messages for wrong input arguments

        // calculate results
        Output output = new PriceBasketImpl(items).calculatePrices();

        // print subtotal
        System.out.println(output.getSubtotalText());

        // print discounts
        output.getDiscountTexts().forEach(System.out::println);

        // print total
        System.out.println(output.getTotalText());
    }
}
