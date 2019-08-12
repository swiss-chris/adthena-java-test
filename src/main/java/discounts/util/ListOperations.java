package discounts.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListOperations {

    /**
     * @return an {@link Optional} of the items remaining after removing all {@code itemsToRemove},
     * or {@link Optional#empty()} if at least one of the {@code itemsToRemove} could not be removed from {@code allItems}. <br />
     * If {@code itemsToRemove} is empty, returns a copy of {@code allItems}
     */
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
