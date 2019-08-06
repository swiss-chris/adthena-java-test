package formatters;

public class CurrencyFormatterProducer {
    public static CurrencyFormatter getCurrencyFormatter() {
        return new GbpFormatter();
    }
}
