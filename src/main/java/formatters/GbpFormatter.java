package formatters;

import java.text.NumberFormat;
import java.util.Locale;

public class GbpFormatter {

    // TODO implement for pence
    public static String format(Double price) {
        NumberFormat GBP = NumberFormat.getCurrencyInstance(Locale.UK);
        return GBP.format(price);
    }
}
