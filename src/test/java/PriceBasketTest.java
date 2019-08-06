import org.junit.Test;
import products.Product;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PriceBasketTest {

    @Test
    public void getProducts() {

        String[] items = {
            "apples",
            "soup",
            "bread"
        };

        List<Product> products = PriceBasket.findProducts(items);

        assertEquals(3, products.size());
        assertTrue(products.contains(Product.APPLES));
        assertTrue(products.contains(Product.SOUP));
        assertTrue(products.contains(Product.BREAD));
    }

    @Test
    public void getProducts_unknown() {

        String[] items = {
            "apples2",
            "soup1",
            "bread"
        };

        List<Product> products = PriceBasket.findProducts(items);

        assertEquals(1, products.size());
        assertTrue(products.contains(Product.BREAD));
    }

}