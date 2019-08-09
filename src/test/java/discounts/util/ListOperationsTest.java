package discounts.util;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ListOperationsTest {

    @Test
    public void containsAllElements() {
        assertTrue(ListOperations.containsAllElements(Arrays.asList("apples", "milk", "bread"), Arrays.asList("bread", "milk")));
        assertFalse(ListOperations.containsAllElements(Arrays.asList("apples", "milk", "bread"), Arrays.asList("bread", "soup")));
    }

    @Test
    public void removeAllElements() {
        List<String> allItems = new ArrayList<>();
        allItems.add("apples");
        allItems.add("milk");
        allItems.add("bread");
        List<String> itemsToRemove = Arrays.asList("bread", "milk");

        Optional<List<String>> result = ListOperations.removeAllElements(allItems, itemsToRemove);

        assertEquals(Collections.singletonList("apples"), result.get());
    }

    @Test
    public void removeAllElements_null() {
        List<String> allItems = new ArrayList<>();
        allItems.add("apples");
        allItems.add("milk");
        allItems.add("bread");
        List<String> itemsToRemove = Arrays.asList("bread", "soup");

        Optional<List<String>> result = ListOperations.removeAllElements(allItems, itemsToRemove);

        assertFalse(result.isPresent());
    }
}