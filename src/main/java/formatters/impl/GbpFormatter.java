package formatters.impl;

import formatters.CurrencyFormatter;

import java.text.DecimalFormat;

public class GbpFormatter implements CurrencyFormatter {

    @Override
    public String format(Double price) {

        double rounded = round(price);

        if (rounded < 1) {
            return new DecimalFormat("#0p").format(rounded * 100);
        } else {
            return new DecimalFormat("£#,###,##0.00").format(rounded);
        }
    }

    double round(Double price) {
        return (double) Math.round(price * 100) / 100.0;
    }
}
