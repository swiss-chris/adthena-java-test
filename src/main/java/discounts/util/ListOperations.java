package discounts.util;

import java.util.ArrayList;
import java.util.List;

public class ListOperations {

    public static boolean containsAllElements(final List<String> allItems, final List<String> itemsToCheck) {
        ArrayList<String> allItemsCopy = new ArrayList<>(allItems);
        for (String itemToCheck : itemsToCheck) {
            if (!allItemsCopy.remove(itemToCheck)) {
                return false;
            }
        }
        return true;
    }

    // TODO consider returning Optional.of(newList) instead of throwing an exeption 
    // - preserving referential integrity and functional purity rather than modifying 
    // the input, throwing an exception, and requiring explanation / preconditions.
    /**
     * PRECONDITION: make sure all elements are contained inside allItems by calling {@link ListOperations#containsAllElements} <br />
     *
     * @param allItems      the list from which itemsToRemove shall be removed
     * @param itemsToRemove the items to remove from allItems
     * @throws IllegalArgumentException if not all of {@code itemsToRemove} were contained inside {@code allItems}}
     */
    public static void removeAllElements(final List<String> allItems, final List<String> itemsToRemove) {
        for (String itemToRemove : itemsToRemove) {
            if (!allItems.remove(itemToRemove)) {
                throw new IllegalArgumentException("didn't find '" + itemToRemove + "' inside List: " + allItems);
            }
        }
    }
}
