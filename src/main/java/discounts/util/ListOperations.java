package discounts.util;

import cyclops.data.Seq;
import cyclops.data.Vector;

import java.util.Optional;

public class ListOperations {

    /**
     * @return an {@link Optional} of the items remaining after removing all {@code itemsToRemove},
     * or {@link Optional#empty()} if at least one of the {@code itemsToRemove} could not be removed from {@code allItems}. <br />
     * If {@code itemsToRemove} is empty, returns {@code allItems}
     */
    public static Optional<Vector<String>> removeAllElements(Vector<String> allItems, final Seq<String> itemsToRemove) {
        for (final String itemToRemove : itemsToRemove) {
            if (allItems.containsValue(itemToRemove)) {
                allItems = allItems.removeValue(itemToRemove);
            } else {
                return Optional.empty();
            }
        }
        return Optional.of(allItems);
    }
}
