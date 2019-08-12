package discounts.util;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ListOperationsTest {

    @Test
    public void removeAllElements() {
        List<String> allItems = new ArrayList<>();
        allItems.add("apples");
        allItems.add("milk");
        allItems.add("bread");
        List<String> itemsToRemove = Arrays.asList("bread", "milk");

        Optional<List<String>> result = ListOperations.removeAllElements(allItems, itemsToRemove);

        //noinspection OptionalGetWithoutIsPresent
        assertEquals(Collections.singletonList("apples"), result.get());
    }

    @Test
    public void removeAllElements_absent() {
        List<String> allItems = new ArrayList<>();
        allItems.add("apples");
        allItems.add("milk");
        allItems.add("bread");
        List<String> itemsToRemove = Arrays.asList("bread", "soup");

        Optional<List<String>> result = ListOperations.removeAllElements(allItems, itemsToRemove);

        assertFalse(result.isPresent());
    }

    @Test
    public void removeAllElements_emptyRemoveInput() {
        List<String> allItems = new ArrayList<>();
        allItems.add("apples");
        allItems.add("milk");
        allItems.add("bread");
        List<String> itemsToRemove = Collections.emptyList();

        Optional<List<String>> result = ListOperations.removeAllElements(allItems, itemsToRemove);

        //noinspection OptionalGetWithoutIsPresent
        assertEquals(allItems, result.get());
    }

    @Test
    public void removeAllElements_emptyItemsInput() {
        List<String> allItems = Collections.emptyList();
        List<String> itemsToRemove = Arrays.asList("bread", "soup");

        Optional<List<String>> result = ListOperations.removeAllElements(allItems, itemsToRemove);

        assertFalse(result.isPresent());
    }
}