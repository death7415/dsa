package javaInterview;

// Java NavigableMap Deep Dive - 30 Detailed Examples

import java.util.*;

public class NavigableMapExamples {
    public static void main(String[] args) {

        // 1. Basic NavigableMap using TreeMap
        NavigableMap<Integer, String> navMap = new TreeMap<>();
        navMap.put(10, "Ten");
        navMap.put(20, "Twenty");
        navMap.put(30, "Thirty");
        navMap.put(40, "Forty");
        System.out.println("1: " + navMap);
        /*
         * NavigableMap is an extension of SortedMap that provides navigation methods.
         * Implemented by TreeMap, it allows access to entries near a given key.
         * Maintains sorted order of keys using Red-Black tree.
         * Navigation methods include lowerKey, higherKey, floorKey, ceilingKey, etc.
         */

        // 2. lowerKey - key strictly less than given
        System.out.println("2: lowerKey(25) = " + navMap.lowerKey(25));
        /*
         * lowerKey(K key) returns the greatest key strictly less than the given key.
         * If no such key exists, returns null.
         * Useful when you want the closest lesser key.
         * Works in O(log n) using tree traversal.
         */

        // 3. floorKey - key less than or equal to given
        System.out.println("3: floorKey(30) = " + navMap.floorKey(30));
        /*
         * floorKey(K key) returns the greatest key less than or equal to the given key.
         * It includes the key if it exists.
         * Handy when doing <= comparisons in range queries.
         * Like lowerKey but includes the exact match if present.
         */

        // 4. ceilingKey - key greater than or equal to given
        System.out.println("4: ceilingKey(25) = " + navMap.ceilingKey(25));
        /*
         * ceilingKey(K key) returns the smallest key >= given key.
         * Great for implementing >= lookups, e.g. scheduling.
         * O(log n) due to tree search.
         * Returns null if no such key exists.
         */

        // 5. higherKey - key strictly greater than given
        System.out.println("5: higherKey(30) = " + navMap.higherKey(30));
        /*
         * higherKey(K key) gives smallest key strictly greater than given.
         * Used to advance to next slot in a sequence.
         * Often used for upper bound logic in range scans.
         * Null if no key > input exists.
         */

        // 6. firstEntry and lastEntry
        System.out.println("6a: firstEntry = " + navMap.firstEntry());
        System.out.println("6b: lastEntry = " + navMap.lastEntry());
        /*
         * firstEntry returns Map.Entry for the smallest key.
         * lastEntry returns entry with the largest key.
         * These are boundary operations, efficient and quick.
         * Constant time since root/min/max nodes are tracked.
         */

        // 7. pollFirstEntry and pollLastEntry
        System.out.println("7a: pollFirstEntry = " + navMap.pollFirstEntry());
        System.out.println("7b: pollLastEntry = " + navMap.pollLastEntry());
        System.out.println("7c: After polling = " + navMap);
        /*
         * pollFirstEntry removes and returns lowest entry.
         * pollLastEntry does the same for highest.
         * Useful for ordered eviction or sliding window.
         * TreeMap restructures itself after polling.
         */

        // 8. descendingKeySet
        System.out.println("8: descendingKeySet = " + navMap.descendingKeySet());
        /*
         * descendingKeySet() returns a NavigableSet in reverse key order.
         * Backed by the map, changes reflect on both sides.
         * Useful for reverse iteration.
         * Supports O(log n) performance.
         */

        // 9. descendingMap
        NavigableMap<Integer, String> descMap = navMap.descendingMap();
        System.out.println("9: descendingMap = " + descMap);
        /*
         * descendingMap() gives a view of the map with reverse ordering.
         * Useful when reverse traversal is needed without changing actual map.
         * Also backed by original map.
         * Live changes apply in both views.
         */

        // 10. subMap() with inclusive flags
        navMap.put(10, "Ten"); navMap.put(20, "Twenty"); navMap.put(30, "Thirty");
        NavigableMap<Integer, String> sub = navMap.subMap(10, true, 30, true);
        System.out.println("10: subMap(10,true,30,true) = " + sub);
        /*
         * subMap(fromKey, fromInclusive, toKey, toInclusive) gives a range view.
         * Very flexible for slicing maps.
         * Changes affect the original map.
         * Inclusive flags give fine control over bounds.
         */

        // 11. headMap() with inclusive flag
        System.out.println("11: headMap(20,true) = " + navMap.headMap(20, true));
        /*
         * headMap(toKey, inclusive) returns keys <= toKey if inclusive.
         * Useful for grouping data up to a threshold.
         * Allows tuning of bounds during scans.
         * Modifying view also modifies base map.
         */

        // 12. tailMap() with inclusive flag
        System.out.println("12: tailMap(20,false) = " + navMap.tailMap(20, false));
        /*
         * tailMap(fromKey, inclusive) returns keys >= fromKey.
         * Setting inclusive = false skips the boundary.
         * Often used in scheduling, time ranges.
         * Efficient subset retrieval.
         */

        // 13. NavigableSet from keySet()
        NavigableSet<Integer> keySet = navMap.navigableKeySet();
        System.out.println("13: navigableKeySet = " + keySet);
        /*
         * navigableKeySet() returns NavigableSet backed by map.
         * Maintains sort order.
         * Can be navigated using lower, floor, ceiling methods.
         * Supports element removal.
         */

        // 14. remove from subMap view
        sub.remove(30);
        System.out.println("14: removed from subMap, original = " + navMap);
        /*
         * Views like subMap are backed by original map.
         * Removing from view updates original.
         * Same applies for put and clear.
         * Efficient partitioning.
         */

        // 15. Try null key
        try {
            navMap.put(null, "Null");
        } catch (NullPointerException e) {
            System.out.println("15: TreeMap throws NPE for null keys");
        }
        /*
         * TreeMap (and thus NavigableMap) does not allow null keys.
         * Keys must be comparable.
         * Inserting null throws NullPointerException.
         * Use caution when migrating from HashMap.
         */

        // 16. allow null values
        navMap.put(50, null);
        System.out.println("16: added null value = " + navMap);
        /*
         * TreeMap supports null values.
         * Only keys must be non-null.
         * Can be used for optional values or pending updates.
         * Safe as long as key comparison is not based on value.
         */

        // 17. replace and putIfAbsent
        navMap.replace(20, "Updated Twenty");
        navMap.putIfAbsent(60, "Sixty");
        System.out.println("17: replace & putIfAbsent = " + navMap);
        /*
         * replace updates only if key is present.
         * putIfAbsent skips overwrite if key exists.
         * Good for conditional logic.
         * Avoids unintended overwrites.
         */

        // 18. compute example
        navMap.compute(30, (k, v) -> v + " [Recomputed]");
        System.out.println("18: compute = " + navMap);
        /*
         * compute recalculates value based on key.
         * Useful for accumulations, adjustments.
         * Thread-safe in concurrent maps.
         * Avoids redundant null checks.
         */

        // 19. custom comparator
        NavigableMap<String, Integer> alphaMap = new TreeMap<>(Comparator.reverseOrder());
        alphaMap.put("b", 2);
        alphaMap.put("a", 1);
        alphaMap.put("c", 3);
        System.out.println("19: custom comparator = " + alphaMap);
        /*
         * NavigableMap allows comparator-based ordering.
         * reverseOrder() sorts in descending order.
         * Helps in reverse sorting use cases.
         * Comparator must be consistent with equals.
         */

        // 20. getOrDefault()
        System.out.println("20: getOrDefault(99, 'NA') = " + navMap.getOrDefault(99, "NA"));
        /*
         * getOrDefault returns a fallback if key missing.
         * Prevents null pointer exceptions.
         * Great for lookups with defaults.
         * Useful in maps holding sparse data.
         */

        // 21. using clone()
        TreeMap<Integer, String> cloneMap = (TreeMap<Integer, String>) ((TreeMap<Integer, String>) navMap).clone();
        System.out.println("21: cloned map = " + cloneMap);
        /*
         * TreeMap supports shallow clone.
         * Creates new map with same structure.
         * Underlying values not deep copied.
         * Safe for snapshot-style logic.
         */

        // 22. use map entry iteration
        for (Map.Entry<Integer, String> entry : navMap.entrySet()) {
            System.out.println("22: entry = " + entry);
        }
        /*
         * Standard entrySet iteration gives ordered traversal.
         * Entries follow sorted key order.
         * Very efficient and clean for processing.
         * Common loop structure in Java.
         */

        // 23. floorEntry, ceilingEntry
        System.out.println("23a: floorEntry(25) = " + navMap.floorEntry(25));
        System.out.println("23b: ceilingEntry(25) = " + navMap.ceilingEntry(25));
        /*
         * floorEntry gives entry <= key.
         * ceilingEntry gives entry >= key.
         * Useful for binary search-like retrieval.
         * More informative than just the key.
         */

        // 24. lowerEntry, higherEntry
        System.out.println("24a: lowerEntry(25) = " + navMap.lowerEntry(25));
        System.out.println("24b: higherEntry(25) = " + navMap.higherEntry(25));
        /*
         * lowerEntry: < key; higherEntry: > key.
         * Same logic as floor/ceiling without equals.
         * Helps when skipping current index.
         * Reduces post-checks for boundaries.
         */

        // 25. entrySet on descendingMap
        for (Map.Entry<Integer, String> entry : navMap.descendingMap().entrySet()) {
            System.out.println("25: descending entry = " + entry);
        }
        /*
         * descendingMap().entrySet() allows reversed iteration.
         * Helpful for LIFO-like access.
         * Great for UI ordering, priority reversing.
         * Saves time on sorting externally.
         */

        // 26. equals & hashCode
        System.out.println("26a: equals? " + navMap.equals(cloneMap));
        System.out.println("26b: hashCode = " + navMap.hashCode());
        /*
         * equals checks structural value equality.
         * hashCode returns consistent value if equal.
         * Required for collection membership.
         * Important in testing and deduping logic.
         */

        // 27. map size and clear
        System.out.println("27a: size = " + navMap.size());
        navMap.clear();
        System.out.println("27b: after clear = " + navMap);
        /*
         * size() returns entry count.
         * clear() wipes out all contents.
         * Useful for batch reset operations.
         * Leaves internal structure intact for reuse.
         */

        // 28. inserting duplicate keys
        alphaMap.put("a", 100);
        System.out.println("28: updated key = " + alphaMap);
        /*
         * Keys in maps are unique.
         * Putting the same key replaces value.
         * Safe way to update without re-checking.
         * No duplication of keys ever.
         */

        // 29. concurrent modification in loop (fails)
        try {
            for (String k : alphaMap.keySet()) {
                if (k.equals("a")) alphaMap.put("x", 200);
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("29: Concurrent modification exception caught");
        }
        /*
         * Modifying map while iterating causes ConcurrentModificationException.
         * Must use Iterator and remove() or ConcurrentSkipListMap.
         * Safe iteration needed for consistency.
         * Avoids subtle bugs in concurrent logic.
         */

        // 30. TreeMap as NavigableMap for scheduling
        TreeMap<Integer, String> scheduler = new TreeMap<>();
        scheduler.put(9, "Standup");
        scheduler.put(13, "Lunch");
        scheduler.put(18, "Wrap up");
        System.out.println("30: next after 10 = " + scheduler.ceilingEntry(10));
        /*
         * NavigableMap is excellent for time-based scheduling.
         * ceilingEntry gives next event >= time.
         * Can model cron-like systems.
         * Very efficient for future event lookup.
         */
    }
}

