package discounts.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListOperations {

    public static Optional<List<String>> removeAllElements(final List<String> allItems, final List<String> itemsToRemove) {
        final ArrayList<String> allItemsCopy = new ArrayList<>(allItems);
        for (final String itemToRemove : itemsToRemove) {
            if (!allItemsCopy.remove(itemToRemove)) {
                return Optional.empty();
            }
        }
        return Optional.of(allItemsCopy);
    }
}
