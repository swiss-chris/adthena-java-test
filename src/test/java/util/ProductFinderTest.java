package util;

import org.junit.Test;
import products.Product;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductFinderTest {

    @Test
    public void findProducts() {

        String[] items = {
            "Apples",
            "soup",
            "Bread"
        };

        List<Product> products = new ProductResolver().getPricedProducts(items);

        assertEquals(3, products.size());
        assertTrue(products.stream().anyMatch(product -> product.getName().equals("apples")));
        assertTrue(products.stream().anyMatch(product -> product.getName().equals("soup")));
        assertTrue(products.stream().anyMatch(product -> product.getName().equals("bread")));
    }

    @Test
    public void findProducts_unknown() {

        String[] items = {
            "apples2",
            "soup1",
            "bread"
        };

        List<Product> products = new ProductResolver().getPricedProducts(items);

        assertEquals(1, products.size());
        assertTrue(products.stream().anyMatch(product -> product.getName().equals("bread")));
    }

}