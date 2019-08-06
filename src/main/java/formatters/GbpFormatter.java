package formatters;

import java.text.DecimalFormat;

public class GbpFormatter {

    public static String format(Double price) {

        double rounded = round(price);

        if (rounded < 1) {
            return new DecimalFormat("#0p").format(rounded * 100);
        } else {
            return new DecimalFormat("£#,###,##0.00").format(rounded);
        }
    }

    static double round(Double price) {
        return (double) Math.round(price * 100) / 100.0;
    }
}
