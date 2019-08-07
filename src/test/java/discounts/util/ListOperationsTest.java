package discounts.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ListOperationsTest {

    @Test
    public void containsAllElements() {
        assertTrue(ListOperations.containsAllElements(Arrays.asList("apple", "milk", "bread"), Arrays.asList("bread", "milk")));
        assertFalse(ListOperations.containsAllElements(Arrays.asList("apple", "milk", "bread"), Arrays.asList("bread", "soup")));
    }

    @Test
    public void removeAllElements() {
        List<String> allItems = new ArrayList<>();
        allItems.add("apple");
        allItems.add("milk");
        allItems.add("bread");
        List<String> itemsToRemove = Arrays.asList("bread", "milk");

        ListOperations.removeAllElements(allItems, itemsToRemove);

        assertEquals(Collections.singletonList("apple"), allItems);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeAllElements_null() {
        List<String> allItems = new ArrayList<>();
        allItems.add("apple");
        allItems.add("milk");
        allItems.add("bread");
        List<String> itemsToRemove = Arrays.asList("bread", "soup");

        ListOperations.removeAllElements(allItems, itemsToRemove);
    }
}