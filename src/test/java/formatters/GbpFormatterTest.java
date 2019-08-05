package formatters;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GbpFormatterTest {

    @Test
    public void format_pence() {
        Double price = 0.10;

        String result = GbpFormatter.format(price);

        assertEquals("10p", result);
    }
}