package discounts.util;

import cyclops.data.Seq;
import cyclops.data.Vector;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ListOperationsTest {

    @Test
    public void removeAllElements() {
        Vector<String> allItems = Vector.of(
            "apples",
            "milk",
            "bread"
        );
        Seq<String> itemsToRemove = Seq.of(
            "bread",
            "milk"
        );

        //noinspection OptionalGetWithoutIsPresent
        assertEquals(Vector.of("apples"), ListOperations.removeAllElements(allItems, itemsToRemove).get());
    }

    @Test
    public void removeAllElements_absent() {
        Vector<String> allItems = Vector.of(
            "apples",
            "milk",
            "bread"
        );
        Seq<String> itemsToRemove = Seq.of("bread", "soup");

        Optional<Vector<String>> result = ListOperations.removeAllElements(allItems, itemsToRemove);

        assertFalse(result.isPresent());
    }

    @Test
    public void removeAllElements_emptyRemoveInput() {
        Vector<String> allItems = Vector.of(
            "apples",
            "milk",
            "bread"
        );
        Seq<String> itemsToRemove = Seq.empty();

        Optional<Vector<String>> result = ListOperations.removeAllElements(allItems, itemsToRemove);

        //noinspection OptionalGetWithoutIsPresent
        assertEquals(allItems, result.get());
    }

    @Test
    public void removeAllElements_emptyItemsInput() {
        Vector<String> allItems = Vector.empty();
        Seq<String> itemsToRemove = Seq.of("bread", "soup");

        Optional<Vector<String>> result = ListOperations.removeAllElements(allItems, itemsToRemove);

        assertFalse(result.isPresent());
    }
}