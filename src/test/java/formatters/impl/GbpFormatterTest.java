package formatters.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GbpFormatterTest {

    private GbpFormatter gbpFormatter = new GbpFormatter();

    @Test
    public void format_pence() {

        assertEquals("0p", gbpFormatter.format(0.00));
        assertEquals("1p", gbpFormatter.format(0.01));
        assertEquals("10p", gbpFormatter.format(0.10));
        assertEquals("99p", gbpFormatter.format(0.99));
        assertEquals("99p", gbpFormatter.format(0.994));
        assertEquals("£1.00", gbpFormatter.format(0.995));
        assertEquals("£1.00", gbpFormatter.format(0.999));
    }

    @Test
    public void format_pounds() {
        assertEquals("£2.00", gbpFormatter.format(2.00));
        assertEquals("£2,000.00", gbpFormatter.format(2000.00));
        assertEquals("£2,000,000.00", gbpFormatter.format(2000000.00));
    }

    @Test
    public void round() {
        assertEquals(1.00, gbpFormatter.round(0.995), 0.001);
    }
}