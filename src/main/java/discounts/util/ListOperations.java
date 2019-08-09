package discounts.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListOperations {

    /**
     * If this method returns true, then we can trust that we won't receive Optional.empty()
     * if we call {@link ListOperations#removeAllElements(List, List)} with the exact same 2 lists as arguments
     *
     * @return {@code true}, if all of the {@code itemsToCheck} are contained inside {@code allItems} (preserving duplicates),
     * and {@code false}, if not all of {@code itemsToCheck} are contained inside {@code allItems}.
     */
    public static boolean containsAllElements(final List<String> allItems, final List<String> itemsToCheck) {
        final ArrayList<String> allItemsCopy = new ArrayList<>(allItems);
        for (final String itemToCheck : itemsToCheck) {
            if (!allItemsCopy.remove(itemToCheck)) {
                return false;
            }
        }
        return true;
    }

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
