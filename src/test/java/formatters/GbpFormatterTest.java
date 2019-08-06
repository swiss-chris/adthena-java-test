package formatters;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GbpFormatterTest {

    @Test
    public void format_pence() {

        assertEquals("0p", GbpFormatter.format(0.00));
        assertEquals("1p", GbpFormatter.format(0.01));
        assertEquals("10p", GbpFormatter.format(0.10));
        assertEquals("99p", GbpFormatter.format(0.99));
        assertEquals("99p", GbpFormatter.format(0.994));
        assertEquals("£1.00", GbpFormatter.format(0.995));
        assertEquals("£1.00", GbpFormatter.format(0.999));
    }

    @Test
    public void format_pounds() {
        assertEquals("£2.00", GbpFormatter.format(2.00));
        assertEquals("£2,000.00", GbpFormatter.format(2000.00));
        assertEquals("£2,000,000.00", GbpFormatter.format(2000000.00));
    }

    @Test
    public void round() {
        assertEquals(1.00, GbpFormatter.round(0.995), 0.001);
    }
}